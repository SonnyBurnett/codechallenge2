using System;
using System.Collections.Generic;
using System.Text;
using Tw.Ing.Challenge.Commands;

namespace Tw.Ing.Challenge.Services
{
    public interface ICurrencyConverterService
    {
        ProductPrice ConvertTo(ProductPrice price, Currency toCurrency);
        List<Product> ConvertTo(List<Product>priceList, Currency toCurrency);
    }
}
