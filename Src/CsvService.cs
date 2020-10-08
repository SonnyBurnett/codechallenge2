using System;
using System.Net.Http;
using System.Threading.Tasks;

namespace Tw.Ing.Challenge
{
    internal class CsvService
    {
        private HttpClient _client;

        public CsvService(HttpClient client)
        {
            _client = client;
        }

        public async Task<Object> Load(Uri fileToLoad)
        {
            var req = new HttpRequestMessage(HttpMethod.Get, "https://henrybeen.nl/wp-content/uploads/2020/10");
            await _client.SendAsync(req);
            return null;
        }
    }


}