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

namespace Tw.Ing.Challenge.Tests
{
    public class CsvServiceLoading_Tests
    {
        [Fact]
        public async Task Load_Success()
        {
            var requestMock = new Mock<HttpMessageHandler>(MockBehavior.Default);
            string csvString =
@"productId,name,description,price,category
45848,shorts, short pants,8,pants
";

            requestMock.SetupGetMethod(HttpStatusCode.OK, "001-experts-inputs.csv", csvString);
            
            var httpClient = new HttpClient(requestMock.Object);
            ICsvService srv = new CsvService(httpClient);

            var productList = await srv.Load(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv"));

            Assert.Single<Product>(productList);
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
            Func<Task> act = () => srv.Load(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv"));
            await Assert.ThrowsAsync<HttpRequestException>(act);

            // ASSERT
            

        }
    }
}
