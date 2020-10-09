using CsvHelper.Configuration.Attributes;
using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge.Commands
{
    public class Product
    {
        [CsvFieldName("productId")]
        public int ProductId { get; set; }

        [CsvFieldName("name")]
        public string Name { get; set; }

        [CsvFieldName("description")]
        public string Description { get; set; }

        [CsvFieldName("price")]
        public double Price { get; set; }

        [CsvFieldName("category")]
        public ProductCategory Category { get; set; }

        public static Product ParseFromString(string productString, IEnumerable<string> headerList)
        {

        }

        public static IEnumerable<string>GetHeaderList()
        {

        }
    }
}
