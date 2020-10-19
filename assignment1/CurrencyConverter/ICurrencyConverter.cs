namespace MM.CurrencyConverter
{
    public interface ICurrencyConverter
    {
        Currency BaseCurrency { get; set; }

        decimal Exchange(decimal amount, Currency to);
        decimal Exchange(decimal amount, Currency from, Currency to);
    }
}