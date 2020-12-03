using GEP.CodeChallenge.Assignment3.Interfaces;
using System.IO;
using System.Collections.Generic;
using GEP.CodeChallenge.Assignment3.Model;
using System.Globalization;
using CsvHelper;
using System.Linq;

namespace GEP.CodeChallenge.Assignment2.Classes
{
    public class FileInputProcessor<T> : IFileInputProcessor<T> where T : InputModel
    {
        public IList<T> ProcessFile(StreamReader reader)
        {
            var csv = new CsvReader(reader, CultureInfo.InvariantCulture);
            var records = csv.GetRecords<T>();
            return records != null
                           ? records.ToList()
                           : null;
        }
    }
}
