using GEP.CodeChallenge.Assignment3.Enums;
using System;
using System.Collections.Generic;
using System.Text;

namespace GEP.CodeChallenge.Assignment3.Model
{
    public class CountryShippingModel
    {
        public int CustomerId { get; set; }

        public string Name { get; set; }

        public Country Country { get; set; }

        public Shipper Shipper { get; set; }

        public double Cost { get; set; }

        public double Weight { get; set; }

        public int Duration { get; set; }
    }
}
