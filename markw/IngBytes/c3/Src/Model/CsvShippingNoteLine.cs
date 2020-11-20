using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tw.Ing.Challenge3.Model
{
    public class CsvShippingNoteLine
    {
        public int CustomerId { get; set; }
        public string Name { get; set; }
        public string Shipper { get; set; }
        public int Duration { get; set; }
        public double ShippingCost { get; set; }
    }
}
