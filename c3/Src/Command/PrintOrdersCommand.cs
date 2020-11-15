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

namespace Tw.Ing.Challenge3.Command
{
    public class PrintOrdersCommand : ICommandAsync
    {
        private ICsvFileService _fileService;
        private IOrderProcessingService _orderProcessingService;

        public PrintOrdersCommand(ICsvFileService fileService, IOrderProcessingService orderProcessor)
        {
            _fileService = fileService;
            _orderProcessingService = orderProcessor;
        }

        bool ICommandAsync.CanExecute()
        {
            return true;
        }

        async Task ICommandAsync.Execute()
        {
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
                .Select(o => _orderProcessingService.OrderToShipping(o))
                .Subscribe();
        }
    }
}
