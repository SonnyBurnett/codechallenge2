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
    public class CsvCategoryConverter: DefaultTypeConverter
    {
        public override object ConvertFromString(string text, IReaderRow row, MemberMapData memberMapData)
        {
            if (row is null) throw new ArgumentNullException(nameof(row));
            if (memberMapData is null) throw new ArgumentNullException(nameof(memberMapData));

            ProductCategory categoryProperty;

            if (Enum.TryParse<ProductCategory>(text, out categoryProperty))
            {
                return categoryProperty;
            }
            else
            {
                return ProductCategory.Unknown;
            }
        }

        public override string ConvertToString(object value, IWriterRow row, MemberMapData memberMapData)
        {
            var category = (ProductCategory)value;
            switch (category)
            {
                case ProductCategory.Unknown:
                    return "";
                default:
                    return category.ToString();
            }
        }
    }
}
