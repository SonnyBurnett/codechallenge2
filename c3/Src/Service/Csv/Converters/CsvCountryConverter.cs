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
    public class CsvCountryConverter : DefaultTypeConverter
    {
        public override object ConvertFromString(string text, IReaderRow row, MemberMapData memberMapData)
        {
            if (row is null) throw new ArgumentNullException(nameof(row));
            if (memberMapData is null) throw new ArgumentNullException(nameof(memberMapData));

            var countryString = row.GetField<string>($"Country");

            return (countryString.ToUpperInvariant()) switch
            {
                "NETHERLANDS" => "Netherlands",
                "BELGIUM" => "Belgium",
                "FRANCE" => "France",
                "FANCE" => "France",
                _ => throw new InvalidOperationException($"Invalid Country {countryString}")
            };
        }

        public override string ConvertToString(object value, IWriterRow row, MemberMapData memberMapData)
        {
            var country = (string)value;

            if (memberMapData is null) throw new ArgumentNullException(nameof(memberMapData));

            var fieldName = memberMapData.Names.Single();
            if (fieldName == "Country")
            {
                return country;
            }

            return base.ConvertToString(value, row, memberMapData);
        }
    }
}
