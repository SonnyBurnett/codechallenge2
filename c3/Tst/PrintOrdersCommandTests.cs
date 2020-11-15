using Moq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Command;
using Tw.Ing.Challenge3.Service;
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
            var orderProcessingMock = new Mock<IOrderProcessingService>(MockBehavior.Default);
            ICommandAsync cmd = new PrintOrdersCommand(fileServiceMock.Object, orderProcessingMock.Object);

            // ACT 
            await cmd.Execute();

            // ASSERT
            fileServiceMock.Verify(m => m.DownloadCsv(It.IsAny<Uri>()), Times.Once());
        }
    }
}
