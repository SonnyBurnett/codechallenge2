using CsvHelper.Configuration;
using System;
using System.Collections.Generic;
using System.Text;
using Tw.Ing.Challenge.Commands;

namespace Tw.Ing.Challenge.Services
{
    public sealed class CsvProductMap : ClassMap<Product>
    {
        public CsvProductMap()
        {
            Map(m => m.Id).Name("productId");
            Map(m => m.Name).Name("name");
            Map(m => m.Description).Name("description");
            Map(m => m.Price, false).Name("price").TypeConverter<CsvPriceConverter>();
            Map(m => m.Price, false).Name("currency").TypeConverter<CsvPriceConverter>().Optional();
            Map(m => m.Category).Name("category");
        }
    }
}
