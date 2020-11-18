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
        private class ShippingPostNlAdapter : IShippingAdapter
        {
            double IShippingAdapter.GetQuote(ShippingAssignment assignment)
            {
                return 6.95;
            }
            int IShippingAdapter.GetDuration(ShippingAssignment assignment)
            {
                return 1;
            }
        }
    }
}
