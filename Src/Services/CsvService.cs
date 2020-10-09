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
            using (var req = new HttpRequestMessage(HttpMethod.Get, csvFileUri))
            {
                try
                {
                    var response = await _client.SendAsync(req).ConfigureAwait(false);
                    response.EnsureSuccessStatusCode();

                    var responseStream = await response.Content.ReadAsStreamAsync().ConfigureAwait(false);
                    var textReader = new StreamReader(responseStream);

                    using (var csvRdr = new CsvReader(textReader, CultureInfo.InvariantCulture))
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
                            TraceExtensions.DoMessage($"row {rowCount}: {product.Name}, {product.Price.Value}");
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
    }
}