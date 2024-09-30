package org.example.com.invoiceapi.repository

import org.example.com.invoiceapi.model.entity.Invoice
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*
import kotlin.collections.HashSet

@Repository
class InvoiceRepositoryImp : InvoiceRepository {
    val invoices = HashSet<Invoice>()

    override fun getById(id: UUID): Invoice? {
        return invoices.firstOrNull { i -> i.id == id }
    }

    override fun getMonthlyTotalAmountByCustomerId(customerId: UUID, currentDate: LocalDate): HashSet<Invoice> {
        val filteredInvoices = invoices.filter { i -> i.customer.id == customerId  && (i.issuedAt.month == currentDate.month && i.issuedAt.year == currentDate.year) }
        return filteredInvoices.toHashSet()
    }

    override fun create(invoice: Invoice) : Invoice {
        invoices.add(invoice)
        return invoice
    }

}