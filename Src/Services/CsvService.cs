using CsvHelper;
using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Threading;
using System.Threading.Tasks;
using Tw.Ing.Challenge.Commands;

namespace Tw.Ing.Challenge.Services
{
    internal class CsvService<T> : ICsvService<T>
    {
        private readonly HttpClient _client;

        public CsvService(HttpClient client)
        {
            _client = client;
        }

        async Task<IEnumerable<T>> ICsvService<T>.Load(Uri csvFileUri)
        {
            var tokenSource = new CancellationTokenSource();
            using (var req = new HttpRequestMessage(HttpMethod.Get, csvFileUri))
            {
                var response = await _client.SendAsync(req, tokenSource.Token).ConfigureAwait(false);
                response.EnsureSuccessStatusCode();

                var responseStream = await response.Content.ReadAsStreamAsync().ConfigureAwait(false);

                var fieldList = GetFieldList(typeof(T));

            }

        }

        private IEnumerable<string>GetFieldList(Type targetObjectType)
        {
            var fieldList = new List<string>();

            var type = typeof(T);
            var classProperties = type.GetProperties();
            foreach (var prop in classProperties)
            {
                var csvField = prop.GetCustomAttributes(typeof(CsvFieldNameAttribute), false).FirstOrDefault();
                if (csvField != null)
                {
                    var csvFieldNameAttribute = (CsvFieldNameAttribute)csvField;
                    fieldList.Add(csvFieldNameAttribute.FieldName);
                }
            }

            return fieldList;
        }
    }


}