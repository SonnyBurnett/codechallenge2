using System;
using System.Collections.Generic;

namespace CurrencyConverter
{
    public class CurrencyConverter
    {
        private Dictionary<string, decimal> exchangeRates = new Dictionary<string, decimal>();

        public string BaseCurrency { get; set; }

        public CurrencyConverter()
        {
            this.BaseCurrency = "USD";
            this.exchangeRates.Add(this.BaseCurrency, 1M);
        }

        public CurrencyConverter(Dictionary<string, decimal> exchangeRates) : this()
        {
            this.exchangeRates = exchangeRates;
        }

        private void CheckCurrency(string currency)
        { 
            if (!this.exchangeRates.ContainsKey(currency))
            {
                throw new Exception("Unknown currency '" + currency + "', please use GetCurrencyList() to get list of available currencies!", new KeyNotFoundException());
            }
        }


    }
}
