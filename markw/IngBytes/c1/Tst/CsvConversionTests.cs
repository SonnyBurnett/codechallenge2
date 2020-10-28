using System;
using System.Net;
using System.Net.Http;
using Moq;
using Xunit;
using Tw.Ing.Challenge.Services;
using System.Threading.Tasks;
using Tw.Ing.Challenge.Commands;
using System.Linq;
using System.Collections.Generic;

namespace Tw.Ing.Challenge.Tests
{
    public class CsvConversionTests
    {
        [Fact]
        public void ConvertFromTo_Price_UsdToEur()
        {
            // ARRANGE
            ICurrencyConverterService service = new CurrencyConverterService();
            var price = new ProductPrice() { Value = 1, Currency = Currency.USD};

            // ACT
            var newPrice = service.ConvertTo(price, Currency.EUR);

            // ASSESS
            Assert.Equal(Currency.EUR, newPrice.Currency);
            Assert.Equal(0.85, newPrice.Value);
        }

        [Fact]
        public void ConvertFromTo_Price_SameCurrency()
        {
            // ARRANGE
            ICurrencyConverterService service = new CurrencyConverterService();
            var price = new ProductPrice() { Value = 1, Currency = Currency.USD };

            // ACT
            var newPrice = service.ConvertTo(price, Currency.USD);

            // ASSESS
            Assert.Equal(Currency.USD, newPrice.Currency);
            Assert.Equal(1, newPrice.Value);
        }

        [Fact]
        public void ConvertFromTo_ProductList_SameCurrency()
        {
            // ARRANGE
            ICurrencyConverterService service = new CurrencyConverterService();
            var p1 = new Product()
            {
                Id = 1,
                Name = "hmm",
                Description = "Desc",
                Price = new ProductPrice { Value = 1, Currency = Currency.USD },
                Category = ProductCategory.Pants
            };
            var productList = new List<Product>()
            {
                p1
            };

            // ACT
            var newProductList = service.ConvertTo(productList, Currency.EUR);

            // ASSESS
            Assert.Single(newProductList);
            var newProduct = newProductList.Single();
            Assert.NotEqual(p1, newProduct);
            Assert.NotEqual(p1.Price, newProduct.Price);
            Assert.Equal(Currency.EUR, newProduct.Price.Currency);
        }
    }
}
