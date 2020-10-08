using System;
using System.Net;
using System.Net.Http;
using Moq;
using Xunit;
using Tw.Ing.Challenge.Services;

namespace Tw.Ing.Challenge.Tests
{
    public class CsvServiceLoading_Tests
    {
        [Fact]
        public void Load_Success()
        {
            var requestMock = new Mock<HttpMessageHandler>(MockBehavior.Strict);
            requestMock.SetupAddOAuth(HttpStatusCode.OK, "", "CsvString");
            
            var httpClient = new HttpClient(requestMock.Object);
            ICsvService srv = new CsvService(httpClient);

            var result = srv.Load(new Uri(""));
        }
    }
}
