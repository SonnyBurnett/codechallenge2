using System;
using System.Net;
using System.Net.Http;
using Moq;
using Xunit;
using Tw.Ing.Challenge.Services;
using System.Threading.Tasks;
using Tw.Ing.Challenge.Commands;
using System.Linq;

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
            var product = new Product() 
            { 
                Id = 1, 
                Name = "shorts", 
                Description = "short pants", 
                Category = ProductCategory.Pants, 
                Price = new ProductPrice() { Value = 1, Currency= Currency.USD}
            };

            var converterMock = new Mock<ICurrencyConverterService>();
            //ICsvService srv = new CsvService(converterMock.Object, httpClient);

            //// ACT
            //var productList = await srv.DownloadCsv(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv"));

            //// ASSERT
            //Assert.Single<Product>(productList);
        }
    }

}
