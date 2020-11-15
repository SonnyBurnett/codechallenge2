using CsvHelper.Configuration;
using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge3.Service
{
    public class CsvOrderFileMap : ClassMap<OrderLine>
    {
        public CsvOrderFileMap()
        {
            Map(m => m.CustomerId).Name("CustomerId");
            Map(m => m.Name).Name("Name");
            Map(m => m.Product).Name("Product");
            Map(m => m.Price).Name("Price").TypeConverter<CsvPriceConverter>();
            Map(m => m.Weight).Name("Weight").TypeConverter<CsvWeightConverter>();
            Map(m => m.Country).Name("Country");
        }
    }
}
