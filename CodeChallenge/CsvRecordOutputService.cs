using CsvHelper;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;

namespace CodeChallenge
{
    public class CsvRecordOutputService : IRecordOutputService
    {
        private readonly string filePath;

        public CsvRecordOutputService(string filePath)
        {
            if (string.IsNullOrEmpty(filePath))
            {
                throw new ArgumentException($"'{nameof(filePath)}' cannot be null or empty", nameof(filePath));
            }

            this.filePath = filePath;
        }

        public void SetRecords(IEnumerable<Record> records)
        {
            using (var writer = new StreamWriter(filePath))
            using (var csv = new CsvWriter(writer, CultureInfo.InvariantCulture))
            {
                csv.WriteRecords(records);
            }
        }
    }
}
