using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Model;

namespace Tw.Ing.Challenge3.Service.Order
{
    public partial class ShippingAdapter
    {
        private class ShippingDhlAdapter : IShippingAdapter
        {
            double IShippingAdapter.GetQuote(ShippingAssignment assignment)
            {
                return 12.95 + 1.5 * assignment.Order.Weight;
            }
            int IShippingAdapter.GetDuration(ShippingAssignment assignment)
            {
                if (assignment.Order.Weight < 10)
                {
                    return 4;
                }
                else
                {
                    return 8;
                }
            }

        }
    }
}
