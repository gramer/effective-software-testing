package com.gramer.effectivesoftwaretesting.chapter06

import java.time.LocalDate
import java.time.format.DateTimeFormatter

interface SAP {
    fun send(invoice: Invoice)
    fun sendFor(invoice: SapInvoice)
}

class SAPInvoiceSender(private val filter: InvoiceFilter, private val sap: SAP) {

    fun sendLowValuedInvoices() {
        filter.lowValueInvoices().forEach(sap::send)
    }

    fun sendLowValuesSpaInvoices() {
        val lowValuedInvoices = filter.lowValueInvoices()
        lowValuedInvoices.forEach { invoice ->
            val customer = invoice.customer
            val value = invoice.value
            val sapId = generateId(invoice)

            sap.sendFor(SapInvoice(customer, value, sapId))
        }
    }

    private fun generateId(invoice: Invoice): String {
        val date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"))
        val customer = invoice.customer

        return date + (if (customer.length >= 2) customer.substring(0, 2) else "X")
    }
}
