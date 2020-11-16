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
            switch (order.Country.ToUpperInvariant())
            {
                case "NETHERLANDS":
                    return new CountryNetherlandsAdapter();
                case "BELGIUM":
                    return new CountryBelgiumAdapter();
                default:
                    return new CountryOtherAdapter();
            }
        }

    }

}
