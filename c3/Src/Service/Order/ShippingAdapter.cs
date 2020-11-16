using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Model;

namespace Tw.Ing.Challenge3.Service.Order
{
    public partial class ShippingAdapter : IShippingAdapter
    {
        double IShippingAdapter.GetQuote(ShippingAssignment assignment)
        {
            var adapter = GetShippingAdapter(assignment);
            return adapter.GetQuote(assignment);
        }
        int IShippingAdapter.GetDuration(ShippingAssignment assignment)
        {
            var adapter = GetShippingAdapter(assignment);
            return adapter.GetDuration(assignment);
        }

        private static IShippingAdapter GetShippingAdapter(ShippingAssignment assignment)
        {
            return (assignment.Shipper.ToUpperInvariant()) switch
            {
                "POSTNL" => new ShippingPostNlAdapter(),
                "DHL" => new ShippingDhlAdapter(),
                "BELGIOPOSTO" => new ShippingBelgioPostoAdapter(),
            };
        }

    }

}
