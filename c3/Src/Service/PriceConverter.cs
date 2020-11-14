using CsvHelper;
using CsvHelper.Configuration;
using CsvHelper.TypeConversion;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using Tw.Ing.Challenge3.Extensions;

namespace Tw.Ing.Challenge3.Service
{
    public class CsvPriceConverter : DefaultTypeConverter
    {
        public override object ConvertFromString(string text, IReaderRow row, MemberMapData memberMapData)
        {
            if (row is null) throw new ArgumentNullException(nameof(row));
            if (memberMapData is null) throw new ArgumentNullException(nameof(memberMapData));

            var priceString = row.GetField<string>($"Price");
            if (!double.TryParse(priceString, NumberStyles.Float, CultureInfo.InvariantCulture, out double priceValue))
            {
                priceValue = 0;
                TraceExtensions.DoWarn($"Row {memberMapData.Index}: Not a valid price  {priceString}");
            }

            return priceValue;
        }

        public override string ConvertToString(object value, IWriterRow row, MemberMapData memberMapData)
        {
            var price = (double)value;

            if (memberMapData is null) throw new ArgumentNullException(nameof(memberMapData));

            var fieldName = memberMapData.Names.Single();
            if (fieldName == "price")
            {
                return price.ToString(CultureInfo.InvariantCulture);
            }

            return base.ConvertToString(value, row, memberMapData);
        }
    }
}
