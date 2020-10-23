package com.ing.challenge.model

import com.ing.challenge.parser.Parsable
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVRecord
import java.io.File
import java.nio.charset.StandardCharsets

data class Product(
        val productId: Int,
        val name: String,
        val description: String,
        val price: Double,
        val category: String,
): Parsable {
    override fun toCsv(file: File) {
        CSVFormat.DEFAULT
                .withHeader("productId", "name", "description", "price", "category")
                .print(file, StandardCharsets.UTF_8).use { printer ->
                    //The this reference can be used without a this. in Kotlin ;)
                    kotlin.runCatching {
                        printer.printRecord(productId, name, description, price, category)
                        printer.flush()
                    }.onFailure {
                        println("Conversion ot CSV Failed with message: ${it.message}")
                    }
                }
    }

    companion object {
        private const val convertError = "value could not be parsed to an Integer"
        private const val nullError = "value cant be null"

        fun fromCsv(record: CSVRecord): Product = kotlin.runCatching {
            containsValues(record, "productId", "name", "description", "price", "category")
        }.fold({
            val productId = record.get("productId").toIntOrNull() ?: throw IllegalArgumentException(convertError)
            val price = record.get("price").toDoubleOrNull() ?: throw IllegalArgumentException(convertError)
            val name = record.get("name") ?: throw NullPointerException(nullError)
            val description = record.get("description") ?: throw NullPointerException(nullError)
            val category = record.get("category") ?: throw NullPointerException(nullError)
            return Product(productId, name, description, price, category)
        }, {
            println("failed to produce CSV from product: $this")
            throw it
        })

        @Throws(IllegalArgumentException::class)
        private fun containsValues(record: CSVRecord, vararg fields: String) {
            fields.filterNot { record.isMapped(it) }.let { absent ->
                if (absent.isNotEmpty()) throw IllegalArgumentException("Not all records could be found. Missing: $absent")
            }
        }
    }
}