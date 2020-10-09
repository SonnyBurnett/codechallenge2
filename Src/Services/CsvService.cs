using CsvHelper;
using CsvHelper.Configuration;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Threading;
using System.Threading.Tasks;
using Tw.Ing.Challenge.Commands;

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
                var response = await _client.SendAsync(req).ConfigureAwait(false);
                response.EnsureSuccessStatusCode();

                var responseStream = await response.Content.ReadAsStreamAsync().ConfigureAwait(false);
                var textReader = new StreamReader(responseStream);

                using (var csvRdr = new CsvReader(textReader, CultureInfo.InvariantCulture))
                {
                    csvRdr.Configuration.RegisterClassMap<CsvProductMap>();
                    csvRdr.Configuration.Delimiter = ",";
                    csvRdr.Configuration.TrimOptions = TrimOptions.Trim;

                    var productList = csvRdr.GetRecords<Product>();
                    return productList.ToList<Product>();
                }
            }
        }
    }
}