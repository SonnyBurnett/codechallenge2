using GEP.CodeChallenge.Assignment3.Enums;
using GEP.CodeChallenge.Assignment3.Interfaces;
using GEP.CodeChallenge.Assignment3.Model;
using System;
using System.Collections.Generic;
using System.Text;

namespace GEP.CodeChallenge.Assignment3.Classes
{
    public class StrategySelector
    {
        private IShippingStrategy shippingStrategy;

        public IShippingStrategy ShippingStrategy { get; set; }

        public StrategySelector(CountryShippingModel model)
        {
            switch(model.Country)
            {
                case Country.Netherlands:
                    shippingStrategy = new NetherlandsShippingStrategy();
                    break;

                case Country.Belgium:
                    shippingStrategy = new BelgiumShippingStrategy();
                    break;

                case Country.Other:
                    shippingStrategy = new OtherCountryShippingStrategy();
                    break;

            }

            ShippingStrategy = shippingStrategy;

        }
    }
}
