using CsvHelper;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;

namespace CodeChallenge
{
    public class CsvRecordInputService : IRecordInputService
    {
        private readonly string filePath;

        public CsvRecordInputService(string filePath)
        {
            if (string.IsNullOrEmpty(filePath))
            {
                throw new ArgumentException($"'{nameof(filePath)}' cannot be null or empty", nameof(filePath));
            }

            this.filePath = filePath;
        }

        public IEnumerable<Record> GetRecords()
        {
            using (var reader = new StreamReader(filePath))
            using (var csv = new CsvReader(reader, CultureInfo.InvariantCulture))
            {
                return csv.GetRecords<Record>();
            }
        }
    }
}
