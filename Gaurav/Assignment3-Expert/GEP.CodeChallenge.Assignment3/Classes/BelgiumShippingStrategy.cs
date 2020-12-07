using GEP.CodeChallenge.Assignment3.Enums;
using GEP.CodeChallenge.Assignment3.Interfaces;
using GEP.CodeChallenge.Assignment3.Model;
using System;
using System.Collections.Generic;
using System.Text;

namespace GEP.CodeChallenge.Assignment3.Classes
{
    public class BelgiumShippingStrategy : IShippingStrategy
    {
        public CountryShippingModel ShippingInformation(CountryShippingModel model)
        {
            if (model != null)
            {

                model.Shipper = Shipper.BelgioPosto;
                model.Cost = 1.95 + (1 * model.Weight);

            }

            model.Duration = ShipperDuration.GetDuraton(model);

            return model;
        }
    }
}
