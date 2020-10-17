using GEP.CodeChallenge.Classes;
using GEP.CodeChallenge.Interfaces;
using GEP.CodeChallenge.Model;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace GEP.CodeChallenge.Test
{
    [TestClass]
    public class CsvTests
    {
        [ClassInitialize]
        public static void ClassInitialize(TestContext context)
        {
            File.Delete(Constants.OUTPUT_FILE_PATH);
        }

        [TestMethod]
        public void CsvInputTest()
        {
            using (var reader = new StreamReader(Constants.CSV_INPUT_PATH))
            {
                IFileProcessor<ProductEntity, List<ProductEntity>> csvHelper = new ProcessCsv<ProductEntity, List<ProductEntity>>();
                var actual = csvHelper.ProcessFile(reader);

                var expectedProductEntity = new ProductEntity { productId = 45848, name = "shorts", description = "short pants", price = 8, category = "pants" };
                var expected = new List<ProductEntity>();
                expected.Add(expectedProductEntity);

                Assert.IsNotNull(actual);
                Assert.AreEqual(actual.Count, expected.Count);
                Assert.AreEqual(actual[0].productId, expected[0].productId);
                Assert.AreEqual(actual[0].name, expected[0].name);
                Assert.AreEqual(actual[0].description, expected[0].description);
                Assert.AreEqual(actual[0].price, expected[0].price);
                Assert.AreEqual(actual[0].category, expected[0].category);
            }
        }

        [TestMethod]
        public void CsvOutputTest()
        {
            IFileProcessor<ProductEntity, List<ProductEntity>> csvHelper = new ProcessCsv<ProductEntity, List<ProductEntity>>();
            var products = new List<ProductEntity>();

            var productEntity1 = new ProductEntity { productId = 45848, name = "shorts", description = "short pants", price = 8, category = "pants" };
            var productEntity2 = new ProductEntity { productId = 4184688, name = "trousers", description = "trousers", price = 12, category = "pants" };

            products.Add(productEntity1);
            products.Add(productEntity2);

            csvHelper.OutputFile(products, Constants.OUTPUT_FILE_PATH);

            if (!File.Exists(Constants.OUTPUT_FILE_PATH))
                Assert.Fail(Constants.CSV_OUTPUT_FAILED);

        }
    }
}
