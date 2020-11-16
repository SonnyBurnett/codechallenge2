using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Model;
using Tw.Ing.Challenge3.Service.Order;

namespace Tw.Ing.Challenge3.Service
{
    public class OrderProcessingService : IOrderProcessingService
    {
        private readonly ICountryAdapter _countryAdapter;
        private readonly IShippingAdapter _shippingAdapter;

        public OrderProcessingService(ICountryAdapter countryAdapter, IShippingAdapter shippingAdapter)
        {
            _countryAdapter = countryAdapter;
            _shippingAdapter = shippingAdapter;
        }

        CustomerOrder IOrderProcessingService.LineToOrder(CustomerOrder order, OrderLine orderLine)
        {
            if (order == null)
            {
                order = new CustomerOrder()
                {
                    CustomerId = orderLine.CustomerId,
                    Name = orderLine.Name,
                    Country = orderLine.Country
                };
            }

            order.OrderLines.Add(orderLine);
            order.Weight += orderLine.Weight;
            
            return order;
        }

        ShippingAssignment IOrderProcessingService.OrderShippingAssignment(CustomerOrder order)
        {
            var shipping = new ShippingAssignment()
            {
                Order = order
            };
            shipping.Shipper = _countryAdapter.GetShippingPartner(order);
            
            return shipping;
        }

        ShippingNote IOrderProcessingService.ShippingConfirmation(ShippingAssignment shipping)
        {
            return new ShippingNote() {
                Order = shipping.Order,
                Shipper = shipping.Shipper,
                Duration = _shippingAdapter.GetDuration(shipping),
                ShippingCost = _shippingAdapter.GetQuote(shipping)
            };
        }
    }
}
