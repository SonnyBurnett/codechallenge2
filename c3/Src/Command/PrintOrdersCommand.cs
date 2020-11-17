using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;
using Tw.Ing.Challenge3.Service;
using System.Reactive;
using System.Reactive.Linq;
using Tw.Ing.Challenge3.Model;
using System.Reflection.Metadata.Ecma335;
using Tw.Ing.Challenge3.Extensions;
using System.IO;
using System.Diagnostics;

namespace Tw.Ing.Challenge3.Command
{
    public class PrintOrdersCommand : ICommandAsync
    {
        private readonly ICsvFileService _fileService;

        public PrintOrdersCommand(ICsvFileService fileService)
        {
            _fileService = fileService;
        }

        async Task<int> ICommandAsync.Execute()
        {
            var returnCode = 1;
            var csvUri = new Uri("https://henrybeen.nl/wp-content/uploads/2020/11/003-experts-inputs.csv");
            var orderList = await _fileService.DownloadCsv(csvUri).ConfigureAwait(false);

            const string CSVFILENAME = "ShippingNotes.csv";
            string path = $@"{Directory.GetCurrentDirectory()}/{CSVFILENAME}";
            using var textWriter = new StreamWriter(path);

            var csvObservable = orderList.ToObservable<CsvOrderLine>()
                .GroupBy(ol => ol.CustomerId)
                .SelectMany(result =>
                {
                    return result.Aggregate<CsvOrderLine, CustomerOrder>(null, (order, csvLine) =>
                    {
                        return csvLine.ToOrder(order);
                    });
                })
                .Select(o => o.ToShippingAssignment())
                .Select(sa => sa.ToShippingConfirmation())
                .Subscribe(
                    sc =>
                    {
                        _fileService.SaveCsv(sc, textWriter);
                        TraceExtensions.DoMessage($"Order {sc.Order.CustomerId} - {sc.Order.Name}: {sc.Shipper}, {sc.ShippingCost}, {sc.Duration}");
                    },
                    ex =>
                    {
                        TraceExtensions.DoError($"Something fishy happened, {ex.Message}");
                        returnCode = 1;
                    },
                    () =>
                    {
                        TraceExtensions.DoMessage("Done processing orders");
                        returnCode = 0;
                    }
                ) ;

            Process.Start("notepad.exe", path);

            return returnCode;
        }
    }
}
