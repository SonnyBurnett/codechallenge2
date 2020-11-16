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

namespace Tw.Ing.Challenge3.Command
{
    public class PrintOrdersCommand : ICommandAsync
    {
        private readonly ICsvFileService _fileService;
        private readonly IOrderProcessingService _orderProcessingService;

        public PrintOrdersCommand(ICsvFileService fileService, IOrderProcessingService orderProcessor)
        {
            _fileService = fileService;
            _orderProcessingService = orderProcessor;
        }

        async Task<int> ICommandAsync.Execute()
        {
            var returnCode = 1;
            var csvUri = new Uri("https://henrybeen.nl/wp-content/uploads/2020/11/003-experts-inputs.csv");
            var orderList = await _fileService.DownloadCsv(csvUri).ConfigureAwait(false);
            var csvObservable = orderList.ToObservable<OrderLine>()
                .GroupBy(ol => ol.CustomerId)
                .SelectMany(result =>
                {
                    return result.Aggregate<OrderLine, CustomerOrder>(null, (order, orderLine) =>
                    {
                        return _orderProcessingService.LineToOrder(order, orderLine);
                    });
                })
                .Select(o => _orderProcessingService.OrderShippingAssignment(o))
                .Select(sa => _orderProcessingService.ShippingConfirmation(sa))
                .Subscribe(
                    sc =>
                        Console.WriteLine($"Order {sc.Order.CustomerId} - {sc.Order.Name}: {sc.Shipper}, {sc.ShippingCost}, {sc.Duration}"),
                    ex =>
                    {
                        Console.WriteLine($"Something fishy happened, {ex.Message}");
                        returnCode = 1;
                    },
                    () =>
                    {
                        Console.WriteLine("Done processing orders");
                        returnCode = 0;
                    }
                ); 
            return returnCode;
        }
    }
}
