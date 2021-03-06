﻿using CsvHelper.Configuration;
using CsvHelper.Configuration.Attributes;
using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge.Commands
{
    public class Product: Base
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public ProductPrice Price { get; set; }
        public ProductCategory Category { get; set; }
    
        public override Base Clone()
        {
            return new Product()
            {
                Id = Id,
                Name = Name,
                Description = Description,
                Price = (Price == null)? null : Price.Clone() as ProductPrice,
                Category = Category
            };
        }
    }
}
