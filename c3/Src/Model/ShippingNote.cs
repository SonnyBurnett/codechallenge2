using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tw.Ing.Challenge3.Model
{
    public class ShippingNote
    {
        public int CustomerId { get; set; }
        public string Name { get; set; }
        public string Shipper { get; set; }
        public int ItemCount { get; set; }
        public double ShippingCost { get; set; }
    }
}
