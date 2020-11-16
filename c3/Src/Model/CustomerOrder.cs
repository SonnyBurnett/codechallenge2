using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Service;

namespace Tw.Ing.Challenge3.Model
{
    public class CustomerOrder
    {
        public int CustomerId { get; set; }
        public string Name { get; set; }
        public double Weight { get; set; }
        public string Country { get; set; }
        public List<OrderLine> OrderLines { get; private set; } = new List<OrderLine>();
    }
}
