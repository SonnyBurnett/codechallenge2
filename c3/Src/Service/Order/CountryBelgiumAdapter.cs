using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Model;

namespace Tw.Ing.Challenge3.Service.Order
{
    public partial class CountryAdapter
    {
        private class CountryBelgiumAdapter : ICountryAdapter
        {
            string ICountryAdapter.GetShippingPartner(CustomerOrder order)
            {
                return "BelgioPosto";
            }
        }
    }
}
