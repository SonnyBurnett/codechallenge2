using System.Net;
using System.Net.Http;
using System.Threading;
using System.Threading.Tasks;
using Moq;
using Moq.Protected;

namespace Tw.Ing.Challenge.Tests
{
    public static class HttpMockExtensions
    {
        public static void SetupAddOAuth(this Mock<HttpMessageHandler> handlerMock, HttpStatusCode status, string path, string responseContentString )
        {
            handlerMock.Protected()
                .Setup<Task<HttpResponseMessage>>(
                    "SendAsync", 
                    ItExpr.Is<HttpRequestMessage>(rm => rm.RequestUri.PathAndQuery.Contains(path)), 
                    ItExpr.IsAny<CancellationToken>())
                .ReturnsAsync(new HttpResponseMessage {
                    StatusCode = status,
                    Content = new StringContent(responseContentString)
                });
        }
    }
}