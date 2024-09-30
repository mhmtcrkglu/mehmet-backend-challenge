package org.example.com.invoiceapi.controller

import org.example.com.invoiceapi.model.dto.InvoiceRequest
import org.example.com.invoiceapi.model.dto.InvoiceResponse
import org.example.com.invoiceapi.model.entity.Invoice
import org.example.com.invoiceapi.service.InvoiceService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/invoice")
class InvoiceController(private val invoiceService: InvoiceService) {
    @RequestMapping("/{id}", method = [RequestMethod.GET])
    fun getById(@PathVariable id: UUID): InvoiceResponse? {
        return invoiceService.getById(id)
    }

    @RequestMapping("/customer/summary/{customerId}", method = [RequestMethod.GET])
    fun getCustomerSummaryByCustomerId(@PathVariable customerId: UUID): Float {
        return invoiceService.getCustomerMonthlySummaryByCustomerId(customerId)
    }

    @RequestMapping("", method = [RequestMethod.POST])
    fun create(@RequestBody invoice: InvoiceRequest): InvoiceResponse? {
        return invoiceService.create(invoice)
    }
}