using System;
using System.Net;
using System.Net.Http;
using Moq;
using Xunit;
using Tw.Ing.Challenge.Services;
using System.Threading.Tasks;
using Tw.Ing.Challenge.Commands;
using System.Linq;
using System.Collections.Generic;
using System.IO;

namespace Tw.Ing.Challenge.Tests
{
    public class CsvServiceSaveTests
    {
        [Fact]
        public void Save_Success()
        {
            // ARRANGE
            var p1 = new Product()
            {
                Id = 1,
                Name = "hmm",
                Description = "Desc",
                Price = new ProductPrice { Value = 1, Currency = Currency.USD },
                Category = ProductCategory.Pants
            };
            var productList = new List<Product>()
            {
                p1
            };

            var textWriterMock = new Mock<TextWriter>();
            ICsvService srv = new CsvService(new HttpClient());

            // ACT
            srv.SaveCsv(productList, textWriterMock.Object);

            // ASSERT
            textWriterMock.Verify(mock => mock.Write(It.Is<string>(arg => arg == "USD")), Times.Once());
        }
    }

}
