using CsvHelper.Configuration.Attributes;
using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge.Commands
{
    public class Product
    {
        [Name("productId")]
        public int ProductId { get; set; }

        [Name("name")]
        public string Name { get; set; }

        [Name("description")]
        public string Description { get; set; }

        [Name("price")]
        public double Price { get; set; }

        [Name("category")]
        public ProductCategory Category { get; set; }
    }
}
