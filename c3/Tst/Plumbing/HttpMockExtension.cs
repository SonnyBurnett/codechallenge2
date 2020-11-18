using Moq;
using Moq.Protected;
using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Tw.Ing.Challenge3.Tests.Plumbing
{
    public static class HttpMockExtension
    {
        public static void SetupGetMethod(this Mock<HttpMessageHandler> handlerMock, HttpStatusCode status, string path, string responseContentString)
        {
            handlerMock.Protected()
                .Setup<Task<HttpResponseMessage>>(
                    "SendAsync",
                    ItExpr.Is<HttpRequestMessage>(rm => rm.RequestUri.PathAndQuery.Contains(path)),
                    ItExpr.IsAny<CancellationToken>())
                .ReturnsAsync(
                    CreateHttpResponse(responseContentString, status)
                ) ;
        }

        private static HttpResponseMessage CreateHttpResponse(string responseContentString, HttpStatusCode status)
        {
            return new HttpResponseMessage
            {
                StatusCode = status,
                Content = new StringContent(responseContentString)
            };
        }
    }
}
