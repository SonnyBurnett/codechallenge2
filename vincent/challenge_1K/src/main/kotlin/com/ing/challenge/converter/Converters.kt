package com.ing.challenge.converter

import com.ing.challenge.model.Product

interface Converter<T> {
    fun filterPrice(obj: T, bound: Double): Boolean
    fun convertCurrency(obj: T, rate: Double): T
}

internal interface CurrencyConverter {
    val exchange: Double
    fun conversionTo(amount: Double): Double
    fun conversionFrom(amount: Double): Double
}

internal class EuroCurrencyConverter(override val exchange: Double) : CurrencyConverter {
    override fun conversionTo(amount: Double): Double {
        return exchange * amount
    }

    override fun conversionFrom(amount: Double): Double {
        return amount / exchange
    }
}

class ProductConverter : Converter<Product> {
    override fun filterPrice(obj: Product, bound: Double): Boolean = obj.price >= bound

    //With is an expression so it can be used to return a value, in this cast the last thing in the expression body
    override fun convertCurrency(obj: Product, rate: Double): Product = with(obj) {
        val updatedPrice = EuroCurrencyConverter(rate).conversionTo(price)
        Product(productId, name, description, updatedPrice, category)
    }
}