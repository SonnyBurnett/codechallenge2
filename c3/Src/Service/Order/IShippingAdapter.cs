using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Model;

namespace Tw.Ing.Challenge3.Service.Order
{
    public interface IShippingAdapter
    {
        public double GetQuote(ShippingAssignment assignment);
        public int GetDuration(ShippingAssignment assignment);
    }
}
