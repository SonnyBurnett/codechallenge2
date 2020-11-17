using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge3.Model
{
    public class CsvOrderLine
    {
        public int CustomerId { get; set; }
        public string Name { get; set; }
        public string Product { get; set; }
        public double Price { get; set; }
        public double Weight { get; set; }
        public string Country { get; set; }
    }
}
