using CsvHelper;
using CsvHelper.Configuration;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Threading;
using System.Threading.Tasks;
using Tw.Ing.Challenge.Commands;
using Tw.Ing.Challenge.Extensions;

namespace Tw.Ing.Challenge.Services
{
    internal class CsvService : ICsvService
    {
        private readonly HttpClient _client;

        public CsvService(HttpClient client)
        {
            _client = client;
        }

        async Task<IEnumerable<Product>> ICsvService.DownloadCsv(Uri csvFileUri)
        {
            TraceExtensions.DoMessage("Loading Product List in Dollars.");
            using (var req = new HttpRequestMessage(HttpMethod.Get, csvFileUri))
            {
                try
                {
                    var response = await _client.SendAsync(req).ConfigureAwait(false);
                    response.EnsureSuccessStatusCode();

                    var responseStream = await response.Content.ReadAsStreamAsync().ConfigureAwait(false);
                    var textReader = new StreamReader(responseStream);

                    using (CsvReader csvRdr = new CsvReader(textReader, CultureInfo.InvariantCulture))
                    {
                        csvRdr.Configuration.RegisterClassMap<CsvProductMap>();
                        csvRdr.Configuration.Delimiter = ",";
                        csvRdr.Configuration.TrimOptions = TrimOptions.Trim;
                        int rowCount = 0;

                        var productList = new List<Product>();
                        while (csvRdr.Read())
                        {
                            rowCount++;
                            var product = csvRdr.GetRecord<Product>();
                            productList.Add(product);
                            TraceExtensions.DoMessage($"    row {rowCount}: {product.Name}, {product.Price.Value}");
                        }
                        return productList;
                    }
                }
                catch(Exception ex) when(
                    ex is HttpRequestException
                    ||  ex is HeaderValidationException
                )
                {
                    throw new CsvServiceException($"Cannot Read and Parse the CSV File. Exception: {ex.Message}", ex);
                }
            }
        }

        void ICsvService.SaveCsv(IEnumerable<Product> productList, TextWriter textWriter)
        {
            using (var csvWrtr = new CsvWriter(textWriter, CultureInfo.InvariantCulture))
            {
                TraceExtensions.DoMessage("Exporting Product List in Euros.");
                csvWrtr.Configuration.RegisterClassMap<CsvProductMap>();
                csvWrtr.Configuration.Delimiter = ",";
                csvWrtr.Configuration.TrimOptions = TrimOptions.Trim;

                csvWrtr.WriteHeader(typeof(Product));
                csvWrtr.NextRecord();
                int rowNumber = 0;

                foreach (var prod in productList)
                {
                    rowNumber++;
                    csvWrtr.WriteRecord(prod);
                    csvWrtr.NextRecord();
                    TraceExtensions.DoMessage($"    Row {rowNumber}: {prod.Name}, {prod.Price.Value.ToString(CultureInfo.InvariantCulture)}");
                }
            }
        }
    }
}