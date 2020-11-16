using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Model;

namespace Tw.Ing.Challenge3.Service
{
    public class OrderProcessingService : IOrderProcessingService
    {
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

            return shipping;
        }

        ShippingNote IOrderProcessingService.ShippingConfirmation(ShippingAssignment shipping)
        {
            return new ShippingNote() {
                Country = shipping.Country,
                Order = shipping.Order,
                Shipper = shipping.Shipper
            };
        }
    }
}
