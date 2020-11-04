using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge.Commands
{
    public class ProductPrice: Base
    {
        public double Value { get; set; }
        public Currency Currency {get;set;}

        public override Base Clone()
        {
            return new ProductPrice() 
            { 
                Value = Value, 
                Currency = Currency 
            };
        }
    }
}
