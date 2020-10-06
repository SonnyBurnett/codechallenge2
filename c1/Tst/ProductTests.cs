using System;
using Xunit;
using Tw.Ing.C1;

namespace Tw.Ing.C1.Tests
{
    public class ProductTests
    {
        [Fact]
        public void Load()
        {
            var srv = new CsvService();
            srv.Load(null);
        }
    }
}
