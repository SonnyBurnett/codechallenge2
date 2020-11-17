using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Model;
using Tw.Ing.Challenge3.Service.Order;

namespace Tw.Ing.Challenge3.Extensions
{
    public static class CustomerOrderExtensions
    {
        public static ShippingAssignment OrderShippingAssignment(this CustomerOrder order)
        {
            ICountryAdapter countryAdapter = new CountryAdapter();
            var shipping = new ShippingAssignment()
            {
                Order = order
            };
            shipping.Shipper = countryAdapter.GetShippingPartner(order);

            return shipping;
        }
    }
}
