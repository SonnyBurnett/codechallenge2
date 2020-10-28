using Moq;
using System;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using Tw.Ing.Challenge.Commands;
using Tw.Ing.Challenge.Services;
using Xunit;

namespace Tw.Ing.Challenge.Tests
{
    public class CsvServiceDownloadTests
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
            ICsvService srv = new CsvService(httpClient);

            // ACT
            var productList = await srv.DownloadCsv(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv"));

            // ASSESS
            Assert.Single<Product>(productList);
            var product = productList.Single();
            Assert.Equal(45848, product.Id);
            Assert.Equal("shorts", product.Name);
            Assert.Equal("short pants", product.Description);
            Assert.Equal(8, product.Price.Value);
            Assert.Equal(Currency.USD, product.Price.Currency);
            Assert.Equal(ProductCategory.Pants, product.Category);
        }

        [Fact]
        public async Task Load_Success_WithOutCurrency()
        {
            // ARRANGE
            var requestMock = new Mock<HttpMessageHandler>(MockBehavior.Default);
            string csvString =
@"productId, name,  description, price, category
45848, shorts, short pants,  8, pants
";

            requestMock.SetupGetMethod(HttpStatusCode.OK, "001-experts-inputs.csv", csvString);

            var httpClient = new HttpClient(requestMock.Object);
            ICsvService srv = new CsvService(httpClient);

            // ACT
            var productList = await srv.DownloadCsv(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv"));

            Assert.Single<Product>(productList);
        }

        [Fact]
        public async Task Load_Success_UnknownCategory()
        {
            // ARRANGE
            var requestMock = new Mock<HttpMessageHandler>(MockBehavior.Default);
            string csvString =
@"productId, name,  description, price, category
45848, shorts, short pants,  8, trousers
";

            requestMock.SetupGetMethod(HttpStatusCode.OK, "001-experts-inputs.csv", csvString);

            var httpClient = new HttpClient(requestMock.Object);
            ICsvService srv = new CsvService(httpClient);

            // ACT
            var productList = await srv.DownloadCsv(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv"));

            Assert.Single<Product>(productList);
            Assert.Equal(ProductCategory.Unknown, productList.Single().Category);
        }

        [Fact]
        public async Task Load_Success_InvalidPrice()
        {
            // ARRANGE
            var requestMock = new Mock<HttpMessageHandler>(MockBehavior.Default);
            string csvString =
@"productId, name,  description, price, category
45848, shorts, short pants,  8m, pants
";

            requestMock.SetupGetMethod(HttpStatusCode.OK, "001-experts-inputs.csv", csvString);

            var httpClient = new HttpClient(requestMock.Object);
            ICsvService srv = new CsvService(httpClient);

            // ACT
            var productList = await srv.DownloadCsv(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv"));

            Assert.Single<Product>(productList);
            Assert.Equal(0, productList.Single().Price.Value);
        }

        [Fact]
        public async Task Load_Fail_NotFound()
        {
            // ARRANGE
            var requestMock = new Mock<HttpMessageHandler>(MockBehavior.Strict);
            string csvString = @"";
            requestMock.SetupGetMethod(HttpStatusCode.NotFound, "001-experts-inputs.csv", csvString);


            var httpClient = new HttpClient(requestMock.Object);
            ICsvService srv = new CsvService(httpClient);

            // ACT
            Func<Task> act = () => srv.DownloadCsv(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv"));

            // ASSERT
            await Assert.ThrowsAsync<CsvServiceException>(act);
        }

        [Fact]
        public async Task Load_Fail_InvalidFileContent()
        {
            // ARRANGE
            var requestMock = new Mock<HttpMessageHandler>(MockBehavior.Strict);
            string csvString = @"blah";
            requestMock.SetupGetMethod(HttpStatusCode.OK, "001-experts-inputs.csv", csvString);


            var httpClient = new HttpClient(requestMock.Object);
            ICsvService srv = new CsvService(httpClient);

            // ACT
            Func<Task> act = () => srv.DownloadCsv(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv"));

            // ASSERT
            await Assert.ThrowsAsync<CsvServiceException>(act);
        }

        [Fact]
        public async Task Load_Warning_InvalidRecord()
        {
            // ARRANGE
            var requestMock = new Mock<HttpMessageHandler>(MockBehavior.Default);
            string csvString =
@"productId, name,  description, price, category
1,a,aa,11,shirts
45848, 
2,b,bb,22,pants
45850,";

            requestMock.SetupGetMethod(HttpStatusCode.OK, "001-experts-inputs.csv", csvString);

            var httpClient = new HttpClient(requestMock.Object);
            ICsvService srv = new CsvService(httpClient);

            // ACT
            var productList = await srv.DownloadCsv(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv"));

            // ASSESS
            Assert.Equal(2, productList.Count());
            var product1 = productList.First();
            Assert.Equal(1, product1.Id);
            Assert.Equal("a", product1.Name);
            Assert.Equal("aa", product1.Description);
            Assert.Equal(11, product1.Price.Value);
            Assert.Equal(Currency.USD, product1.Price.Currency);
            Assert.Equal(ProductCategory.Shirts, product1.Category);

            var product2 = productList.ElementAt(1);
            Assert.Equal(2, product2.Id);
            Assert.Equal("b", product2.Name);
            Assert.Equal("bb", product2.Description);
            Assert.Equal(22, product2.Price.Value);
            Assert.Equal(Currency.USD, product2.Price.Currency);
            Assert.Equal(ProductCategory.Pants, product2.Category);
        }
    }
}
