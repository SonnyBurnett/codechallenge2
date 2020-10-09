using System;
using System.Net;
using System.Net.Http;
using Moq;
using Xunit;
using Tw.Ing.Challenge.Services;
using System.Threading.Tasks;
using Tw.Ing.Challenge.Commands;
using System.Linq;

namespace Tw.Ing.Challenge.Tests
{
    public class CsvConversionTests
    {
        [Fact]
        public void ConvertFromTo_UsdToEur()
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
        public void ConvertFromTo_SameCurrency()
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
    }
}
