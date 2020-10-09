using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge.Services;

namespace Tw.Ing.Challenge.Commands
{
    public sealed class ChallengeCommand : ICommandAsync
    {
        private readonly ICsvService _csvService ;
        private readonly ICurrencyConverterService _currencyService;

        public ChallengeCommand(ICsvService csvService, ICurrencyConverterService currencyService)
        {
            _csvService = csvService;
            _currencyService = currencyService;
        }

        async Task ICommandAsync.Execute()
        {
            var productList = await _csvService.DownloadCsv(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv")).ConfigureAwait(false);

            var convertedProductList = _currencyService.ConvertTo(productList, Currency.EUR);
            string path = Directory.GetCurrentDirectory();

            using (var textWriter = new StreamWriter(path + "/ProductListInEuros.csv"))
            {
                _csvService.SaveCsv(convertedProductList, textWriter);
            }
        }
    }
}
