// Contains Clothing classes used by Assignment 1

namespace Assignment1.Services
{
    using System;

    public class Clothing
    {
        protected int ProductId
        {
            get;
            set;
        }

        // protected Boolean Sex {get; set; } - No longer allowed.
        protected string Category
        {
            get;
            set;
        }

        protected string Description
        {
            get;
            set;
        }

        protected double DollarPrice
        {
            get;
            set;
        }

        protected double EuroPrice
        {
            get
            {
                return this.DollarPrice * 0.85;
            }
        }

        protected string Name
        {
            get;
            set;
        }

        public Clothing() { }

        public Clothing(
            int productid,
            string category,
            string desc,
            int price,
            string name)
        {
            this.ProductId = productid;
            this.Category = category;
            this.Description = desc;
            this.DollarPrice = price;
            this.Name = name;
        }

        public override string ToString() =>
        $"\"{this.ProductId}\"," +
        $"\"{this.Name}\"," +
        $"\"{this.Description}\"," +
        $"\"{this.EuroPrice}\"," +
        $"\"{this.Category}\"";

        public double GetDollarPrice() => this.DollarPrice;
    }

    public class Pants : Clothing
    {
        // specific properties for pants
        private int legs;
        private string category = "pants";

        public Pants(
            int productid,
            string name,
            string desc,
            int price,
            int legs)
        {
            this.ProductId = productid;
            this.Name = name;
            this.Category = this.category;
            this.Description = desc;
            this.DollarPrice = price;
            this.legs = legs;
        }

        // Assume we have 1 leg if no legs are specified. Yarr we all be peg legged pirates.
        public Pants(
            int id,
            string name,
            string desc,
            int price)
        {
            this.ProductId = id;
            this.Name = name;
            this.Category = this.category;
            this.Description = desc;
            this.DollarPrice = price;
            this.legs = 1;
        }
    }

    public class Shirts : Clothing
    {
        // specific properties for pants
        private int arms;

        private string category = "shirts";

        public Shirts(int productid, string name, string desc, int price, int arms)
        {
            this.ProductId = productid;
            this.Name = name;
            this.Category = this.category;
            this.Description = desc;
            this.DollarPrice = price;
            this.arms = arms;
        }

        public Shirts(int id, string name, string desc, int price)
        {
            this.ProductId = id;
            this.Name = name;
            this.Category = this.category;
            this.Description = desc;
            this.DollarPrice = price;
            this.arms = 1;
        }
    }
}
