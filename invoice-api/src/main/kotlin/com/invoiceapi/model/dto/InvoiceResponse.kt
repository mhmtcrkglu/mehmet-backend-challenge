package org.example.com.invoiceapi.model.dto

import java.time.LocalDateTime
import java.util.*

data class InvoiceResponse(
    val id: UUID,
    val code: String,
    val title: String,
    val description: String?,
    val issuedAt: LocalDateTime, // ISO date time
    val customer: CustomerResponse,
    val positions: List<InvoicePositionResponse>,
    val totalAmount: Float
)

data class CustomerResponse(
    val id: UUID,
    val name: String,
    val address: String
)

data class InvoicePositionResponse(
    val description: String,
    val amount: Float
)

