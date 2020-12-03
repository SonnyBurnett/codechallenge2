using CsvHelper;
using GEP.CodeChallenge.Assignment3.Interfaces;
using GEP.CodeChallenge.Assignment3.Model;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Text;

namespace GEP.CodeChallenge.Assignment3.Classes
{
    public class GenerateOutput<K> : IFileOutputProcessor<K> where K : IList<CountryShippingModel>
    {
        public void OutputFile(K output, string filePath)
        {
            using (var writer = new StreamWriter(filePath))
            using (var csv = new CsvWriter(writer, CultureInfo.InvariantCulture))
            {
                csv.WriteRecords(output);
            }
        }
    }
}
