using System;
namespace Assignment1.Services
{
    public class Clothing
    {
        protected int ProductId { get; set; }

        // protected Boolean Sex {get; set; } - No longer allowed.
        protected string Category { get; set; }
        protected string Description { get; set; }
        protected double DollarPrice { get; set; }
        protected double EuroPrice { get { return DollarPrice * 0.85; } }
        protected string Name { get; set; }
        public Clothing() { }
        public Clothing(int productid, string category, string desc, int price, string name)
        {
            ProductId = productid;
            Category = category;
            Description = desc;
            DollarPrice = price;
            Name = name;
        }
        public override string ToString() =>
        $"\"{this.ProductId}\",\"{this.Name}\",\"{this.Description}\",\"{this.EuroPrice}\",\"{this.Category}\"";

        public double GetDollarPrice() => DollarPrice;
    }
    public class Pants : Clothing
    {
        // specific properties for pants
        protected int Legs;
        protected string category = "pants";
        public Pants(int productid, string name, string desc, int price, int legs)
        {
            this.ProductId = productid;
            this.Name = name;
            this.Category = category;
            this.Description = desc;
            this.DollarPrice = price;
            this.Legs = legs;
        }

        // Assume we have 1 leg if no legs are specified. Yarr we all be peg legged pirates.
        public Pants(int id, string name, string desc, int price)
        {
            this.ProductId = id;
            this.Name = name;
            this.Category = category;
            this.Description = desc;
            this.DollarPrice = price;
            this.Legs = 1;
        }
    }
    public class Shirts : Clothing
    {
        // specific properties for pants
        protected int Arms;
        protected string category = "shirts";
        public Shirts(int productid, string name, string desc, int price, int arms)
        {
            this.ProductId = productid;
            this.Name = name;
            this.Category = category;
            this.Description = desc;
            this.DollarPrice = price;
            this.Arms = arms;
        }

        public Shirts(int id, string name, string desc, int price)
        {
            this.ProductId = id;
            this.Name = name;
            this.Category = category;
            this.Description = desc;
            this.DollarPrice = price;
            this.Arms = 1;
        }
    }
}

