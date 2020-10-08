using System;
using System.Net;
using System.Net.Http;
using Moq;
using Xunit;
using Tw.Ing.Challenge;

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
            var srv = new CsvService(httpClient);
        }
    }
}
