using GEP.CodeChallenge.Interfaces;
using GEP.CodeChallenge.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace GEP.CodeChallenge.Classes
{
    public class PriceFilter<T> : IDataFilter<T> where T : ProductEntity
    {
        private int PriceThreshold { get; set; }

        public PriceFilter(int priceThreshold)
        {
            PriceThreshold = priceThreshold;
        }

        public IList<T> Operation(IList<T> productList)
        {
            if (productList != null && productList.Count > 0)
            {
                return productList.ToList().Where(i => i.price > PriceThreshold).ToList();

            }

            return null;
        }
    }
}
