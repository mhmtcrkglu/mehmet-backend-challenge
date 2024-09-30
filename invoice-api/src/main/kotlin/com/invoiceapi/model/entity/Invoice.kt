package org.example.com.invoiceapi.model.entity

import java.time.LocalDateTime
import java.util.*

data class Invoice(
    val id: UUID,
    val code: String,
    val title: String,
    val description: String?,
    val issuedAt: LocalDateTime, // ISO date time
    val customer: Customer,
    val positions: List<InvoicePosition>,
    val totalAmount: Float
)

data class Customer(
    val id: UUID,
    val name: String,
    val address: String
)

data class InvoicePosition(
    val description: String,
    val amount: Float
)
