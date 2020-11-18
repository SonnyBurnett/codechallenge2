using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Model;

namespace Tw.Ing.Challenge3.Service.Order
{
    public partial class CountryAdapter : ICountryAdapter
    {
        string ICountryAdapter.GetShippingPartner(CustomerOrder order)
        {
            var adapter = GetCountryAdapter(order);
            return adapter.GetShippingPartner(order);
        }

        private static ICountryAdapter GetCountryAdapter(CustomerOrder order)
        {
            return (order.Country.ToUpperInvariant()) switch
            {
                "NETHERLANDS" => new CountryNetherlandsAdapter(),
                "BELGIUM" => new CountryBelgiumAdapter(),
                _ => new CountryOtherAdapter(),
            };
        }

    }

}
