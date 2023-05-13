package com.gramer.effectivesoftwaretesting.chapter06

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldMatch
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify

class SAPInvoiceSenderTest : BehaviorSpec({

    Given("invoices") {
        val mauricio = Invoice("Mauricio", 20)
        val frank = Invoice("Frank", 99)

        When("2 invoice sent by sap") {
            val filter = mockk<InvoiceFilter>()
            val sap = mockk<SAP>(relaxed = true)
            val sapInvoiceSender = SAPInvoiceSender(filter, sap)

            every { filter.lowValueInvoices() } returns listOf(mauricio, frank)
            sapInvoiceSender.sendLowValuedInvoices()

            Then("success") {
                verify(exactly = 1) { sap.send(mauricio) }
                verify(exactly = 1) { sap.send(frank) }
            }
        }

        When("empty invoice") {
            val filter = mockk<InvoiceFilter>()
            val sap = mockk<SAP>(relaxed = true)
            val sapInvoiceSender = SAPInvoiceSender(filter, sap)

            every { filter.lowValueInvoices() } returns emptyList()
            sapInvoiceSender.sendLowValuedInvoices()

            Then("2 invoice sent") {
                verify(exactly = 0) { sap.send(any()) }
            }
        }

        forAll(
            row(mauricio, "Ma"),
            row(Invoice("a", 20), "X"),
        ) { invoice, generatedId ->
            When("SapInvoice sent by sap: $invoice") {
                val filter = mockk<InvoiceFilter>()
                val sap = mockk<SAP>(relaxed = true)
                val sapInvoiceSender = SAPInvoiceSender(filter, sap)
                val sapInvoiceSlot = slot<SapInvoice>()

                every { filter.lowValueInvoices() } returns listOf(invoice)
                every { sap.sendFor(capture(sapInvoiceSlot)) } returns Unit

                sapInvoiceSender.sendLowValuesSpaInvoices()

                Then("success") {
                    sapInvoiceSlot.captured.let {
                        it.customer shouldBe invoice.customer
                        it.value shouldBe invoice.value
                        it.id shouldMatch "\\d{8}$generatedId"
                    }
                }
            }
        }
    }
})
