﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Model;

namespace Tw.Ing.Challenge3.Service.Order
{
    public partial class CountryAdapter
    {
        private class CountryNetherlandsAdapter : ICountryAdapter
        {
            string ICountryAdapter.GetShippingPartner(CustomerOrder order)
            {
                if (order.Weight <10)
                {
                    return "PostNL";
                }
                else
                {
                    return "DHL";
                }
            }
        }
    }
}
