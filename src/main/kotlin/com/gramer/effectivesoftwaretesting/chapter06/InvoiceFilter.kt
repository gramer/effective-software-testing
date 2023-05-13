package com.gramer.effectivesoftwaretesting.chapter06

class InvoiceFilter(private val issuedInvoices: IssuedInvoices) {

    fun lowValueInvoices(): List<Invoice> {
        return issuedInvoices
            .all()
            .filter { invoice -> invoice.value < 100 }
    }
}
