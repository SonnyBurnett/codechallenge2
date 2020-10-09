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
    public class CsvServiceLoading_Tests
    {
        [Fact]
        public async Task Load_Success()
        {
            var requestMock = new Mock<HttpMessageHandler>(MockBehavior.Default);
            string csvString =
@"productId, name,  description, price, currency,category
45848, shorts, short pants,  8, USD, pants
";

            requestMock.SetupGetMethod(HttpStatusCode.OK, "001-experts-inputs.csv", csvString);
            
            var httpClient = new HttpClient(requestMock.Object);
            ICsvService srv = new CsvService(httpClient);

            var productList = await srv.DownloadCsv(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv"));

            Assert.Single<Product>(productList);
        }

        [Fact]
        public async Task Load_Success_WithOutCurrency()
        {
            var requestMock = new Mock<HttpMessageHandler>(MockBehavior.Default);
            string csvString =
@"productId, name,  description, price, category
45848, shorts, short pants,  8, pants
";

            requestMock.SetupGetMethod(HttpStatusCode.OK, "001-experts-inputs.csv", csvString);

            var httpClient = new HttpClient(requestMock.Object);
            ICsvService srv = new CsvService(httpClient);

            var productList = await srv.DownloadCsv(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv"));

            Assert.Single<Product>(productList);
        }

        [Fact]
        public async Task Load_Success_UnknownCategory()
        {
            var requestMock = new Mock<HttpMessageHandler>(MockBehavior.Default);
            string csvString =
@"productId, name,  description, price, category
45848, shorts, short pants,  8, trousers
";

            requestMock.SetupGetMethod(HttpStatusCode.OK, "001-experts-inputs.csv", csvString);

            var httpClient = new HttpClient(requestMock.Object);
            ICsvService srv = new CsvService(httpClient);

            var productList = await srv.DownloadCsv(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv"));

            Assert.Single<Product>(productList);
            Assert.Equal(ProductCategory.Unknown, productList.Single().Category);
        }

        [Fact]
        public async Task Load_Success_InvalidPrice()
        {
            var requestMock = new Mock<HttpMessageHandler>(MockBehavior.Default);
            string csvString =
@"productId, name,  description, price, category
45848, shorts, short pants,  8m, pants
";

            requestMock.SetupGetMethod(HttpStatusCode.OK, "001-experts-inputs.csv", csvString);

            var httpClient = new HttpClient(requestMock.Object);
            ICsvService srv = new CsvService(httpClient);

            var productList = await srv.DownloadCsv(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv"));

            Assert.Single<Product>(productList);
            Assert.Equal(0, productList.Single().Price.Price);
        }

        [Fact]
        public async Task Load_NotFound()
        {
            var requestMock = new Mock<HttpMessageHandler>(MockBehavior.Strict);
            string csvString = @"";
            requestMock.SetupGetMethod(HttpStatusCode.NotFound, "001-experts-inputs.csv", csvString);


            var httpClient = new HttpClient(requestMock.Object);
            ICsvService srv = new CsvService(httpClient);

            // ACT
            Func<Task> act = () => srv.DownloadCsv(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv"));

            // ASSERT
             await Assert.ThrowsAsync<HttpRequestException>(act);
         }

        [Fact]
        public async Task Load_InvalidFile()
        {
            var requestMock = new Mock<HttpMessageHandler>(MockBehavior.Strict);
            string csvString = @"blah";
            requestMock.SetupGetMethod(HttpStatusCode.OK, "001-experts-inputs.csv", csvString);


            var httpClient = new HttpClient(requestMock.Object);
            ICsvService srv = new CsvService(httpClient);

            // ACT
            Func<Task> act = () => srv.DownloadCsv(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv"));

            // ASSERT
             await Assert.ThrowsAsync<HeaderValidationException>(act);
         }
    }
}
