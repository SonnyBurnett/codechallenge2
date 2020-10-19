using System;
using System.Collections.Generic;

namespace MM.CurrencyConverter
{
    public class CurrencyConverter
    {
        private Dictionary<Currency, decimal> exchangeRates = new Dictionary<Currency, decimal>();

        public Currency BaseCurrency { get; set; }

        public CurrencyConverter()
        {
            this.BaseCurrency = Currency.USD;
            this.exchangeRates.Add(this.BaseCurrency, 1M);
        }

        public CurrencyConverter(Dictionary<Currency, decimal> exchangeRates) : this()
        {
            this.exchangeRates = exchangeRates;
        }
         
        private void CheckCurrency(Currency currency)
        { 
            if (!this.exchangeRates.ContainsKey(currency))
            {
                throw new Exception("Unknown currency '" + currency + "', please use GetCurrencyList() to get list of available currencies!", new KeyNotFoundException());
            }
        }

        public decimal Exchange(decimal amount, Currency to, Currency from = Currency.USD)
        {
            if (from == to)
            {
                return amount;
            }

            CheckCurrency(from);
            CheckCurrency(to);

            return amount * this.exchangeRates[to] / this.exchangeRates[from];
        }
    }
}
