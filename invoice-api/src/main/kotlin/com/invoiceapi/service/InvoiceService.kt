package org.example.com.invoiceapi.service

import org.example.com.invoiceapi.model.dto.InvoiceRequest
import org.example.com.invoiceapi.model.dto.InvoiceResponse
import java.util.*

interface InvoiceService {
    fun getById(id: UUID): InvoiceResponse?
    fun getCustomerMonthlySummaryByCustomerId(customerId: UUID): Float
    fun create(invoice: InvoiceRequest): InvoiceResponse?
}