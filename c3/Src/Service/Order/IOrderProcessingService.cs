using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Model;

namespace Tw.Ing.Challenge3.Service
{
    public interface IOrderProcessingService
    {
        public CustomerOrder LineToOrder(CustomerOrder order, OrderLine orderLine);
        public ShippingNote OrderToShipping(CustomerOrder order);
    }
}
