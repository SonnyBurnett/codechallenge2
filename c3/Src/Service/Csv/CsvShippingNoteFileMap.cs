using CsvHelper.Configuration;
using System;
using System.Collections.Generic;
using System.Text;
using Tw.Ing.Challenge3.Model;

namespace Tw.Ing.Challenge3.Service
{
    public class CsvShippingNoteFileMap : ClassMap<CsvShippingNoteLine>
    {
        public CsvShippingNoteFileMap()
        {
            Map(m => m.CustomerId).Name("CustomerId");
            Map(m => m.Name).Name("Name");
            Map(m => m.Shipper).Name("Shipper");
            Map(m => m.Duration).Name("Duration");
            Map(m => m.ShippingCost).Name("Price").TypeConverter<CsvPriceConverter>();
        }
    }
}
