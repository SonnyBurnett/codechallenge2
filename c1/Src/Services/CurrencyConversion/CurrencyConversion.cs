using System;
using System.Collections.Generic;
using System.Text;
using Tw.Ing.Challenge.Commands;

namespace Tw.Ing.Challenge.Services
{
    public class CurrencyConversion
    {
        public Currency From { get; set; }
        public Currency To { get; set; }
        public double Ratio { get; set; }
    }
}
