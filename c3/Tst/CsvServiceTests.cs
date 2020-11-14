using Moq;
using System;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Service;
using Tw.Ing.Challenge3.Tests.Plumbing;
using Xunit;

namespace Tw.Ing.Challenge3.Tests
{
    public class CsvServiceTests
    {
        [Fact]
        public async Task DownloadOrderCsv()
        {
            // ARRANGE
            var requestMock = new Mock<HttpMessageHandler>(MockBehavior.Default);
            string csvString =
@"CustomerId,Name,Product,Price,Weight,Country
16,Henry Been,Pepernoten,3.23,0.5,Netherlands
";

            requestMock.SetupGetMethod(HttpStatusCode.OK, "001-experts-inputs.csv", csvString);

            var httpClient = new HttpClient(requestMock.Object);
            ICsvFileService srv = new CsvFileService(httpClient);

            // ACT
            var orderList = await srv.DownloadCsv(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv"));

            // ASSESS
            Assert.Single<OrderLineCsv>(orderList);
            var product = orderList.Single();
            Assert.Equal(16, product.CustomerId);
            Assert.Equal("Henry Been", product.Name);
            Assert.Equal("Pepernoten", product.Product);
            Assert.Equal(3.23d, product.Price);
            Assert.Equal(0.5d, product.Weight);
            Assert.Equal("Netherlands", product.Country);
        }
    }
}
