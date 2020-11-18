using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tw.Ing.Challenge3.Model
{
    public class ShippingAssignment
    {
        public CustomerOrder Order { get; set; }
        public string Shipper { get; set; }
    }
}
