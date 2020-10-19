using Microsoft.VisualStudio.TestTools.UnitTesting;
using CodeChallenge1.Products;
using System.Text;
using CodeChallenge1.Utils;
using System.Collections.Generic;

namespace CodeChallengeTests
{
    [TestClass]
    public class UnitTest1
    {
        [TestMethod]
        public void TestMethod1()
        {
            StringBuilder expected = new StringBuilder();
            expected.AppendLine("productId,name,description,price,category");
            expected.AppendLine("4184688, trousers, trousers,10,200001, pants");
            expected.AppendLine("848488, blue shirt, shirt,74,8, shirts");
            
            ProductCSV productCSV = new ProductCSV();
            productCSV.csvToProductList(@"C:\Users\YK47BU\source\repos\CodeChallenge1\CodeChallenge1\resources\001-experts-inputs.csv");
            var listOfProducts = productCSV.getProductsWithPriceAbove(10);

            StringBuilder result = new StringBuilder();
            var header = Files.getHeaderSplit(@"C:\Users\YK47BU\source\repos\CodeChallenge1\CodeChallenge1\resources\001-experts-inputs.csv");

            StringBuilder head = new StringBuilder();
            head.Append(header[0]);
            for (int i = 1; i < header.Length; i++)
            {
                head.Append(",");
                head.Append(header[i]);
            }
            result.AppendLine(head.ToString());

            foreach (Dictionary<string, string> product in listOfProducts)
            {
                StringBuilder line = new StringBuilder();
                line.Append(product[header[0]]);
                for (int i = 1; i < header.Length; i++)
                {
                    line.Append(",");
                    line.Append(product[header[i]]);
                }
                result.AppendLine(line.ToString());
            }

            Assert.AreEqual(expected.ToString(), result.ToString());
        }
    }
}
