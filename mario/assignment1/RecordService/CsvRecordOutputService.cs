using CsvHelper;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Threading.Tasks;

namespace RecordService
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

        public Task SetRecords(IEnumerable<Record> records)
        {
            using StreamWriter writer = new StreamWriter(filePath);
            using CsvWriter csv = new CsvWriter(writer, CultureInfo.InvariantCulture);

            return csv.WriteRecordsAsync(records);
        }
    }
}
