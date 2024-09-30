package org.example.com.invoiceapi.repository

import org.example.com.invoiceapi.model.dto.InvoiceRequest
import org.example.com.invoiceapi.model.entity.Invoice
import java.time.LocalDate
import java.util.*
import kotlin.collections.HashSet

interface InvoiceRepository {
    fun getById(id: UUID): Invoice?
    fun getMonthlyTotalAmountByCustomerId(customerId: UUID, currentDate: LocalDate) : HashSet<Invoice>
    fun create(invoice: Invoice): Invoice
}