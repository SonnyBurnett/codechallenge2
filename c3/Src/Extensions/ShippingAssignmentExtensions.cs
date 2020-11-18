using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Model;
using Tw.Ing.Challenge3.Service.Order;

namespace Tw.Ing.Challenge3.Extensions
{
    public static class ShippingAssignmentExtensions
    {
        public static ShippingNote ToShippingConfirmation(this ShippingAssignment shipping)
        {
            if (shipping == null)
                throw new ArgumentNullException(nameof(shipping));

            IShippingAdapter shippinAdapter = new ShippingAdapter();

            return new ShippingNote()
            {
                Order = shipping.Order,
                Shipper = shipping.Shipper,
                Duration = shippinAdapter.GetDuration(shipping),
                ShippingCost = shippinAdapter.GetQuote(shipping)
            };
        }
    }
}
