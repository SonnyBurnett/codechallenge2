using System;
using System.Net.Http;
using System.Threading.Tasks;

namespace Tw.Ing.Challenge.Services
{
    internal class CsvService:ICsvService
    {
        private readonly HttpClient _client;

        public CsvService(HttpClient client)
        {
            _client = client;
        }

        async Task<Object> ICsvService.Load(Uri csvFile)
        {
            using (var req = new HttpRequestMessage(HttpMethod.Get, csvFile))
            {
                await _client.SendAsync(req).ConfigureAwait(false);
            }

            return null;
        }
    }


}