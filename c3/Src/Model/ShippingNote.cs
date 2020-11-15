using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tw.Ing.Challenge3.Model
{
    public class ShippingNote : ShippingAssignment
    {
        public double ShippingCost { get; set; }
        public int Duration { get; set; }
    }
}
