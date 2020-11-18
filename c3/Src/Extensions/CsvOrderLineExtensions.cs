using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Model;

namespace Tw.Ing.Challenge3.Extensions
{
    public static class CsvOrderLineExtensions
    {
        public static CustomerOrder ToOrder(this CsvOrderLine orderLine, CustomerOrder order)
        {
            if (orderLine == null)
                throw new ArgumentNullException(nameof(orderLine));

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
    }
}
