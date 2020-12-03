using GEP.CodeChallenge.Assignment3.Enums;
using GEP.CodeChallenge.Assignment3.Model;
using System;
using System.Collections.Generic;
using System.Text;

namespace GEP.CodeChallenge.Assignment3.Helper
{
    public class MapCountryShippingModel
    {
        public static IList<CountryShippingModel> Map(IList<InputModel> inputModel)
        {

            IList<CountryShippingModel> outputModel = new List<CountryShippingModel>();
            foreach (var model in inputModel)
            {
                CountryShippingModel eachModel = new CountryShippingModel();
                eachModel.CustomerId = model.CustomerId;
                eachModel.Name = model.Name;

                Enum.TryParse(model.Name, out Country country);
                eachModel.Country = country;
                eachModel.Weight = eachModel.Weight;
            }

            return outputModel;
        }
    }
}
