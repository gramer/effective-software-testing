package com.gramer.effectivesoftwaretesting.chapter06

class IssuedInvoices {
    fun all(): List<Invoice> = listOf(
        Invoice(customer = "sample1", 99),
        Invoice(customer = "sample2", 100),
    )
}
