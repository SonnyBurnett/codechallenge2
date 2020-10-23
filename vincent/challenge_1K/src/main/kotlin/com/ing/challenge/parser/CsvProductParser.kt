package com.ing.challenge.parser

import com.ing.challenge.model.Product
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVRecord
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.nio.charset.StandardCharsets

class CsvProductParser : Parser<Product> {

    override fun read(fileName: String): List<Product> {
        return try {
            FileReader(fileName).use { reader ->
                val records: Iterable<CSVRecord> = CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreSurroundingSpaces()
                        .parse(reader)
                records.map { record: CSVRecord -> Product.fromCsv(record) }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return emptyList()
        }
    }


    override fun read(file: File): List<Product> {
        return read(file.absolutePath)
    }

    override fun write(file: File, obj: Product): Boolean {
        return write(file, listOf(obj))
    }

    override fun write(file: File, objects: List<Product>): Boolean {
        val result: Result<Unit> = CSVFormat.DEFAULT
                .withHeader("productId", "name", "description", "price", "category")
                .print(file, StandardCharsets.UTF_8).use { csvPrinter ->
                    val productList = objects.fold(mutableListOf<Array<String>>()) { acc, product ->
                        val array = with(product) {
                            arrayOf(
                                    productId.toString(),
                                    name,
                                    description,
                                    price.toString(),
                                    category,
                            )
                        }
                        acc.apply { add(array) }
                    }
                    kotlin.runCatching {
                        csvPrinter.printRecords(productList)
                        csvPrinter.flush()
                    }
                }
        return result.isSuccess
    }
}
