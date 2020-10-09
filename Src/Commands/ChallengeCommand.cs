using Figgle;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge.Extensions;
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
            var banner = FiggleFonts.Big.Render("Challenge - 1");
            Console.WriteLine(banner);

            var productList = await _csvService.DownloadCsv(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv")).ConfigureAwait(false);

            var filteredProductList = productList.Where(product => !(product.Price.Value < 10));

            var convertedProductList = _currencyService.ConvertTo(filteredProductList, Currency.EUR);

            string path = Directory.GetCurrentDirectory() + "/ProductListInEuros.csv";
            using var textWriter = new StreamWriter(path );
            _csvService.SaveCsv(convertedProductList, textWriter);

            Console.WriteLine();
            string closingNotification = $"Opening csv with {convertedProductList.Count()} rows in Notepad...";
            Console.WriteLine(closingNotification);
            Process.Start("notepad.exe", path);
        }
    }
}
