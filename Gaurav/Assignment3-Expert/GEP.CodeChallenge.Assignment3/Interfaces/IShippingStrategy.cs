using GEP.CodeChallenge.Assignment3.Model;
using System;
using System.Collections.Generic;
using System.Text;

namespace GEP.CodeChallenge.Assignment3.Interfaces
{
    public interface IShippingStrategy
    {
        CountryShippingModel ShippingInformation(CountryShippingModel model);
    }
}
