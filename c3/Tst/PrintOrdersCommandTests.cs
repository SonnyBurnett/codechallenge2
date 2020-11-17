using Moq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Command;
using Tw.Ing.Challenge3.Model;
using Tw.Ing.Challenge3.Service;
using Tw.Ing.Challenge3.Tests.Plumbing;
using Xunit;

namespace Tw.Ing.Challenge3.Tests
{
    public class PrintOrdersCommandTests
    {
        [Fact]
        public async Task DownloadOrderCsv_NotFound()
        {
            // ARRANGE
            var fileServiceMock = new Mock<ICsvFileService>(MockBehavior.Default);
            fileServiceMock
                .Setup(m => m.DownloadCsv(It.IsAny<Uri>()))
                .ReturnsAsync(() => {
                    var list = new List<CsvOrderLine>();
                    list.Add(new CsvOrderLine()
                    {
                        Country = "",
                        CustomerId = 1,
                        Name = "",
                        Price = 1,
                        Product = "Jas",
                        Weight = 1
                    });
                    return list;
                    });
            ICommandAsync cmd = new PrintOrdersCommand(fileServiceMock.Object);

            // ACT 
            await cmd.Execute();

            // ASSERT
            fileServiceMock.Verify(m => m.DownloadCsv(It.IsAny<Uri>()), Times.Once());
        }
    }
}
