using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Tw.Ing.Challenge.Commands;

namespace Tw.Ing.Challenge.Services
{
    public sealed class CurrencyConverterService : ICurrencyConverterService
    {
        readonly List<CurrencyConversion> _conversionList;
        public CurrencyConverterService()
        {
            _conversionList = new List<CurrencyConversion>
            {
                new CurrencyConversion() { From = Currency.EUR, To = Currency.USD, Ratio = 1.17d },
                new CurrencyConversion() { From = Currency.USD, To = Currency.EUR, Ratio = 0.85d }
            };
        }

        ProductPrice ICurrencyConverterService.ConvertTo(ProductPrice price, Currency toCurrency)
        {
            if (price.Currency == toCurrency)
            {
                return price;
            }

            var conversionRatio = _conversionList.Where(cc => cc.From == price.Currency && cc.To == toCurrency).Single().Ratio;
            return new ProductPrice() { Value = price.Value * conversionRatio, Currency = toCurrency};
        }
    }
}
