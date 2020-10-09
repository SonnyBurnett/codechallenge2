using CsvHelper;
using CsvHelper.Configuration;
using CsvHelper.Expressions;
using CsvHelper.TypeConversion;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Tw.Ing.Challenge.Commands;
using Tw.Ing.Challenge.Extensions;

namespace Tw.Ing.Challenge.Services
{
    public class CsvPriceConverter: DefaultTypeConverter
    {
        public override object ConvertFromString(string text, IReaderRow row, MemberMapData memberMapData)
        {
            var priceProperty = new ProductPrice() { Price = 0, Currency = Currency.USD};

            var priceString = row.GetField<string>($"price");
            double priceValue;
            if (double.TryParse(priceString, out priceValue))
            {
                priceProperty.Price = priceValue;
                TraceExtensions.DoMessage($"Row {memberMapData.Index}: price {priceValue}");
            }
            else
            {
                priceProperty.Price = 0;
                TraceExtensions.DoWarn($"Row {memberMapData.Index}: Not a valid price  {priceString}");
            }
            string currencyString;
            if (row.TryGetField<string>("currency", out currencyString))
            {
                priceProperty.Currency = Enum.Parse<Currency>(currencyString);
            }

            return priceProperty;
        }

        public override string ConvertToString(object value, IWriterRow row, MemberMapData memberMapData)
        {
            return base.ConvertToString(value, row, memberMapData);
        }
    }
}
