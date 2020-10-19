package com.ing.challenge

import com.ing.challenge.converter.Converter
import com.ing.challenge.converter.ProductConverter
import com.ing.challenge.model.Product
import com.ing.challenge.parser.CsvProductParser
import com.ing.challenge.parser.Parser
import java.io.File
import java.io.IOException
import java.net.URISyntaxException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors

fun main() {
    val source: Path = ParseApp.pathToOutputFile("999-test.csv")

    val csvParser: Parser<Product> = CsvProductParser()
    val converter: Converter<Product> = ProductConverter()

    val products: List<Product> = csvParser.read(ParseApp.getFile("001-experts-inputs.csv"))

    println("\nCurrent products in US dollars: [")
    products.forEach { product -> println("        $product") }
    println("]\n")

    val filteredProducts = products
            .filter { converter.filterPrice(it, 10.0) }
            .map { p: Product -> converter.convertCurrency(p, 0.85) }

    println("Filtered products in Euros: [")
    filteredProducts.forEach { product -> println("        $product") }
    println("]")

    Files.createFile(source).toFile().run {
        csvParser.write(this, filteredProducts)
    }
}

object ParseApp {
    @Throws(IOException::class)
    fun pathToOutputFile(fileName: String): Path {
        val source = Paths.get(javaClass.getResource("/").path, fileName)
        if (Files.exists(source)) {
            Files.delete(source)
        }
        return source
    }

    @Throws(URISyntaxException::class)
    fun getFile(fileName: String): File {
        return Path.of(
                Objects.requireNonNull(ClassLoader
                        .getSystemClassLoader()
                        .getResource(fileName))
                        .toURI()
        ).toFile()
    }
}