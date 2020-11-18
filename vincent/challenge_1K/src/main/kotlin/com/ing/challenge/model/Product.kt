package com.ing.challenge.model

import com.ing.challenge.parser.Parsable
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVRecord
import java.io.File
import java.nio.charset.StandardCharsets

/*
Default visibility is public, this is Kotlin's default and for data/record classes should be the visibility set unless
there is a good reason why not. Due to immutability of a data class this can't hurt the class or it's objects
*/
/**
 * The object representation of a column in the example CSV file.
 * @param productId The product ID
 * @param name The name of the product
 * @param description a detailed description of the product
 * @param price The price of this product
 * @param category A category for this product
 * @author Vincent Free
 * @since 1.0.0
 */
data class Product(
        val productId: Int,
        val name: String,
        val description: String,
        val price: Double,
        val category: String,
) : Parsable {
    /**
     * Write the current record to a file on the file system using the incoming [File] object.
     * It uses a [org.apache.commons.csv.CSVPrinter] to write the product in a csv format
     * @param file A location th write the Product to
     * @see org.apache.commons.csv.CSVFormat
     * @see org.apache.commons.csv.CSVPrinter
     */
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

    /*
    Static like functions, they are not actually static but they work the same way. example: "Product.fromCsv(rec)"
    No need for an instance of Product to call this function.
    */
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