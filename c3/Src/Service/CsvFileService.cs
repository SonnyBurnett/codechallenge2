using CsvHelper;
using CsvHelper.Configuration;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Extensions;

namespace Tw.Ing.Challenge3.Service
{
    public class CsvFileService : ICsvFileService
    {
        private readonly HttpClient _client;

        public CsvFileService(HttpClient client)
        {
            _client = client;
        }

        async Task<IEnumerable<OrderLineCsv>> ICsvFileService.DownloadCsv(Uri csvFile)
        {
            TraceExtensions.DoMessage("Loading Order List.");
            using var req = new HttpRequestMessage(HttpMethod.Get, csvFile);
            try
            {
                var response = await _client.SendAsync(req).ConfigureAwait(false);
                response.EnsureSuccessStatusCode();

                var responseStream = await response.Content.ReadAsStreamAsync().ConfigureAwait(false);
                var textReader = new StreamReader(responseStream);

                using CsvReader csvRdr = new CsvReader(textReader, CultureInfo.InvariantCulture);
                csvRdr.Configuration.RegisterClassMap<CsvOrderFileMap>();
                csvRdr.Configuration.Delimiter = ",";
                csvRdr.Configuration.TrimOptions = TrimOptions.Trim;
                int rowCount = 0;

                var orderList = new List<OrderLineCsv>();
                while (csvRdr.Read())
                {
                    try
                    {
                        rowCount++;
                        var order = csvRdr.GetRecord<OrderLineCsv>();
                        orderList.Add(order);
                        TraceExtensions.DoMessage($"    row {rowCount}: {order.Name}, {order.Price}");
                    }
                    catch (ReaderException ex)
                    {
                        TraceExtensions.DoWarn($"    Row {rowCount}: Unable to parse: exception - {ex.Message}");
                    }
                }
                return orderList;
            }
            catch (Exception ex) when (
                ex is HttpRequestException
                || ex is HeaderValidationException
            )
            {
                throw new InvalidOperationException($"Cannot Read and Parse the CSV File. Exception: {ex.Message}", ex);
            }
        }
    }
}
