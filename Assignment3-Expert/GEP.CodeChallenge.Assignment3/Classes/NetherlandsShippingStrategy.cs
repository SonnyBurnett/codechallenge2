using GEP.CodeChallenge.Assignment3.Enums;
using GEP.CodeChallenge.Assignment3.Interfaces;
using GEP.CodeChallenge.Assignment3.Model;
using System;
using System.Collections.Generic;
using System.Text;

namespace GEP.CodeChallenge.Assignment3.Classes
{
    public class NetherlandsShippingStrategy : IShippingStrategy
    {
        public CountryShippingModel ShippingInformation(CountryShippingModel model)
        {
            if (model != null)
            {
                if (model.Weight < 10)
                {
                    model.Shipper = Shipper.PostNL;
                    model.Cost = 6.95;
                }

                else
                {
                    model.Shipper = Shipper.DHL;
                    model.Cost = 12.95 + (1.5 * model.Weight);
                }
            }

            model.Duration = ShipperDuration.GetDuraton(model);

            return model;
        }
    }
}
