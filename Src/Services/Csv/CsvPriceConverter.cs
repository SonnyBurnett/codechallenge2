using CsvHelper;
using CsvHelper.Configuration;
using CsvHelper.Expressions;
using CsvHelper.TypeConversion;
using System;
using System.Collections.Generic;
using System.Globalization;
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
            if (row is null) throw new ArgumentNullException(nameof(row));
            if (memberMapData is null) throw new ArgumentNullException(nameof(memberMapData));

            var priceProperty = new ProductPrice() { Value = 0, Currency = Currency.USD};

            var priceString = row.GetField<string>($"price");
            if (double.TryParse(priceString, out double priceValue))
            {
                priceProperty.Value = priceValue;
            }
            else
            {
                priceProperty.Value = 0;
                TraceExtensions.DoWarn($"Row {memberMapData.Index}: Not a valid price  {priceString}");
            }
            if (row.TryGetField<string>("currency", out string currencyString))
            {
                priceProperty.Currency = Enum.Parse<Currency>(currencyString);
            }

            return priceProperty;
        }

        public override string ConvertToString(object value, IWriterRow row, MemberMapData memberMapData)
        {
            ProductPrice price = value as ProductPrice;

            if (price == null) throw new ArgumentOutOfRangeException(nameof(value));
            if (memberMapData is null) throw new ArgumentNullException(nameof(memberMapData));

            var fieldName = memberMapData.Names.Single();
            if (fieldName == "currency") 
            {
                return price.Currency.ToString();
            }

            if (fieldName == "price")
            {
                return price.Value.ToString(CultureInfo.InvariantCulture);
            }
            
            return base.ConvertToString(value, row, memberMapData);
        }
    }
}
