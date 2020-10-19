using CommandLine;
using MM.CurrencyConverter;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CodeChallenge
{
    public static class Program
    {
        private const int ErrorBadArguments = 0xA0;

        /// <summary>
        /// Entry point of the command-line utility.
        /// </summary>
        /// <param name="args">Command line arguments.</param>
        /// <returns>A <see cref="Task"/> representing the result of the asynchronous operation.</returns>
        public static async Task Main(string[] args)

        {
            using var parser = new Parser(with =>
            {
                with.CaseSensitive = false;
                with.CaseInsensitiveEnumValues = true;
                with.EnableDashDash = true;
                with.HelpWriter = Console.Out;
            });

            Options options = null;

            parser.ParseArguments<Options>(args)
                .WithParsed(opts => options = opts)
                .WithNotParsed(errors => Environment.Exit(ErrorBadArguments));

            Dictionary<Currency, decimal> exchangeRates = new Dictionary<Currency, decimal>
                {
                    { Currency.EUR, (decimal)options.ExchangeRate },
                    { Currency.USD, 1M }
                };

            CurrencyConverter currencyConverter = new CurrencyConverter(exchangeRates);

            IRecordInputService inputService = new CsvRecordInputService(options.InputFilePath);
            IRecordOutputService outputService = new CsvRecordOutputService(options.OutpuFilePath);

            List<Record> records = inputService.GetRecords().Where(r => r.Price > (decimal)options.PricePoint).ToList();

            records.ForEach(r => r.Price = currencyConverter.Exchange(r.Price, Currency.EUR));

            await outputService.SetRecords(records);
        }
    }
}
