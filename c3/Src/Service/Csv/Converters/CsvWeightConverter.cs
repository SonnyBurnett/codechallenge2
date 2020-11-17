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
    public class CsvWeightConverter : DefaultTypeConverter
    {
        public override object ConvertFromString(string text, IReaderRow row, MemberMapData memberMapData)
        {
            if (row is null) throw new ArgumentNullException(nameof(row));
            if (memberMapData is null) throw new ArgumentNullException(nameof(memberMapData));

            var weightString = row.GetField<string>($"Weight");
            if (!double.TryParse(weightString, NumberStyles.Float, CultureInfo.InvariantCulture, out double weightValue))
            {
                weightValue = 0;
                TraceExtensions.DoWarn($"Row {memberMapData.Index}: Not a valid number  {weightString}");
            }

            return weightValue;
        }

        public override string ConvertToString(object value, IWriterRow row, MemberMapData memberMapData)
        {
            var weight = (double)value;

            if (memberMapData is null) throw new ArgumentNullException(nameof(memberMapData));

            var fieldName = memberMapData.Names.Single();
            if (fieldName == "Weight")
            {
                return weight.ToString(CultureInfo.InvariantCulture);
            }

            return base.ConvertToString(value, row, memberMapData);
        }
    }
}
