using GEP.CodeChallenge.Assignment3.Enums;
using GEP.CodeChallenge.Assignment3.Model;
using System;
using System.Collections.Generic;
using System.Text;

namespace GEP.CodeChallenge.Assignment3.Classes
{
    public class ShipperDuration 
    {
        public static int GetDuraton(CountryShippingModel model)
        {
            int duration = 0;
            switch(model.Shipper)
            {
                case Shipper.PostNL:
                    duration = 1;
                    break;

                case Shipper.BelgioPosto:
                    duration = 2;
                    break;

                case Shipper.DHL:
                    if (model.Weight < 10)
                        duration = 4;
                    else
                        duration = 8;

                    break;
            }

            return duration;
        }
    }
}
