package org.example.com.invoiceapi.service

import org.example.com.invoiceapi.model.dto.*
import org.example.com.invoiceapi.model.entity.Customer
import org.example.com.invoiceapi.model.entity.Invoice
import org.example.com.invoiceapi.model.entity.InvoicePosition
import org.example.com.invoiceapi.repository.InvoiceRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*
import kotlin.collections.HashSet

@Service
class InvoiceServiceImp(private val invoiceRepository: InvoiceRepository) : InvoiceService {
    override fun getById(id: UUID): InvoiceResponse? {
        val invoice = invoiceRepository.getById(id) ?: return null
        return convertInvoiceToInvoiceResponse(invoice)
    }

    override fun getCustomerMonthlySummaryByCustomerId(customerId: UUID): Float {
        val currentDate = LocalDate.now()
        val customerMonthlyInvoices = invoiceRepository.getMonthlyTotalAmountByCustomerId(customerId, currentDate)
        val customerMonthlyTotalAmount = customerMonthlyInvoices.sumOf { ci -> ci.totalAmount.toDouble()  }
        return customerMonthlyTotalAmount.toFloat()
    }

    override fun create(invoice: InvoiceRequest): InvoiceResponse? {
        val invoiceEntity = convertInvoiceRequestToInvoiceEntity(invoice)
        val createdInvoice = invoiceRepository.create(invoiceEntity)
        return convertInvoiceToInvoiceResponse(createdInvoice)
    }

    private fun convertInvoiceRequestToInvoiceEntity(invoice: InvoiceRequest) : Invoice {
        val invoicePositions = convertInvoicePositionsRequestToInvoicePositionsEntity(invoice.positions)
        val invoiceCustomer = convertInvoiceCustomerRequestToInvoiceCustomerEntity(invoice.customer)
        return Invoice(invoice.id, invoice.code, invoice.title, invoice.description,invoice.issuedAt, invoiceCustomer, invoicePositions,invoice.totalAmount)
    }

    private fun convertInvoiceCustomerRequestToInvoiceCustomerEntity(customer: CustomerRequest) : Customer {
        return Customer(customer.id,customer.name,customer.address)
    }

    private fun convertInvoicePositionsRequestToInvoicePositionsEntity(invoicePositionsRequest: List<InvoicePositionRequest>) : List<InvoicePosition> {
        val positions = HashSet<InvoicePosition>()
        invoicePositionsRequest.forEach { p -> positions.add(InvoicePosition(p.description, p.amount)) }
        return positions.toList()
    }

    private fun convertInvoiceToInvoiceResponse(invoice: Invoice) : InvoiceResponse {
        val invoicePositions = convertInvoicePositionsToInvoicePositionsResponse(invoice.positions)
        val invoiceCustomer = convertInvoiceCustomerToInvoiceCustomerResponse(invoice.customer)
        return InvoiceResponse(invoice.id, invoice.code, invoice.title, invoice.description,invoice.issuedAt, invoiceCustomer, invoicePositions,invoice.totalAmount)
    }

    private fun convertInvoiceCustomerToInvoiceCustomerResponse(customer: Customer) : CustomerResponse {
        return CustomerResponse(customer.id,customer.name,customer.address)
    }

    private fun convertInvoicePositionsToInvoicePositionsResponse(invoicePositionsRequest: List<InvoicePosition>) : List<InvoicePositionResponse> {
        val positions = HashSet<InvoicePositionResponse>()
        invoicePositionsRequest.forEach { p -> positions.add(InvoicePositionResponse(p.description, p.amount)) }
        return positions.toList()
    }
}