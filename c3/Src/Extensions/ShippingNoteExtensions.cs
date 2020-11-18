using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Model;
using Tw.Ing.Challenge3.Service.Order;

namespace Tw.Ing.Challenge3.Extensions
{
    public static class ShippingNoteExtensions
    {
        public static CsvShippingNoteLine ToCsvLine(this ShippingNote shipping)
        {
            if (shipping == null)
                throw new ArgumentNullException(nameof(shipping));

            return new CsvShippingNoteLine()
            {
                CustomerId = shipping.Order.CustomerId,
                Name = shipping.Order.Name,
                Shipper = shipping.Shipper,
                Duration = shipping.Duration,
                ShippingCost = shipping.ShippingCost
            };
        }
    }
}
