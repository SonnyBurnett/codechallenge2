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
        private class ShippingBelgioPostoAdapter : IShippingAdapter
        {
            double IShippingAdapter.GetQuote(ShippingAssignment assignment)
            {
                return 1.95 + 1 * assignment.Order.Weight;
            }
            int IShippingAdapter.GetDuration(ShippingAssignment assignment)
            {
                return 2;
            }

        }
    }
}
