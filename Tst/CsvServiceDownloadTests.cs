using System;
using System.Net;
using System.Net.Http;
using Moq;
using Xunit;
using Tw.Ing.Challenge.Services;
using System.Threading.Tasks;
using Tw.Ing.Challenge.Commands;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using CsvHelper;

namespace Tw.Ing.Challenge.Tests
{
    public class CsvServiceSaveTests
    {
        [Fact]
        public async Task Load_Success()
        {
            // ARRANGE
            var requestMock = new Mock<HttpMessageHandler>(MockBehavior.Default);
            string csvString =
@"productId, name,  description, price, currency,category
45848, shorts, short pants,  8, USD, pants
";

            requestMock.SetupGetMethod(HttpStatusCode.OK, "001-experts-inputs.csv", csvString);  
            var httpClient = new HttpClient(requestMock.Object);

            var converterMock = new Mock<ICurrencyConverterService>();
            ICsvService srv = new CsvService(converterMock.Object, httpClient);

            // ACT
            var productList = await srv.DownloadCsv(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv"));

            // ASSERT
            Assert.Single<Product>(productList);
            var product = productList.Single();
            Assert.Equal(45848, product.Id);
            Assert.Equal("shorts", product.Name);
            Assert.Equal("short pants", product.Description);
            Assert.Equal(8, product.Price.Value);
            Assert.Equal(Currency.USD, product.Price.Currency);
            Assert.Equal(ProductCategory.Pants, product.Category);
        }
    }
}
