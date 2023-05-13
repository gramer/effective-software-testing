package com.gramer.effectivesoftwaretesting.chapter06

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.mockk.every
import io.mockk.mockk

class InvoiceFilterTest : FunSpec({

    test("filter invoice") {
        val issuedService = mockk<IssuedInvoices>()
        val mauricio = Invoice("Mauricio", 20)
        val steve = Invoice("Steve", 99)
        val frank = Invoice("Frank", 100)

        val listOfInvoices = listOf(mauricio, steve, frank)

        every { issuedService.all() } returns listOfInvoices

        InvoiceFilter(issuedService).lowValueInvoices().shouldContainExactly(
            mauricio,
            steve,
        )
    }
})
