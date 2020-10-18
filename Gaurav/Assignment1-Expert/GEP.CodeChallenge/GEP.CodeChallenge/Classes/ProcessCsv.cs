using CsvHelper;
using GEP.CodeChallenge.Interfaces;
using GEP.CodeChallenge.Model;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Text;

namespace GEP.CodeChallenge.Classes
{
    public class ProcessCsv<T, K> : IFileProcessor<T, K> where T: ProductEntity where K : IList<ProductEntity>
    {
       
        IList<T> IFileProcessor<T, K>.ProcessFile(StreamReader reader)
        {
            var csv = new CsvReader(reader, CultureInfo.InvariantCulture);
            var records = csv.GetRecords<T>();
            return records != null 
                           ? records.ToList() 
                           : null;
        }

        void IFileProcessor<T, K>.OutputFile(K product, string filePath)
        {
            using (var writer = new StreamWriter(filePath))
            using (var csv = new CsvWriter(writer, CultureInfo.InvariantCulture))
            {
                csv.WriteRecords(product);
            }
        }

    }
}
