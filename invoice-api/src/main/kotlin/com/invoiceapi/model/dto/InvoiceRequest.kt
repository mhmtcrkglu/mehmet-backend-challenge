package org.example.com.invoiceapi.model.dto

import java.time.LocalDateTime
import java.util.*

data class InvoiceRequest(
    val id: UUID,
    val code: String,
    val title: String,
    val description: String?,
    val issuedAt: LocalDateTime, // ISO date time
    val customer: CustomerRequest,
    val positions: List<InvoicePositionRequest>,
    val totalAmount: Float
)

data class CustomerRequest(
    val id: UUID,
    val name: String,
    val address: String
)

data class InvoicePositionRequest(
    val description: String,
    val amount: Float
)
