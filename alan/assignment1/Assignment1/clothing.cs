// Copyright (c) DEVCOP. All Rights Reserved.

namespace Assignment1.Services
{
    public class Clothing
    {
        public Clothing()
        {
        }

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

        public override string ToString() =>
        $"\"{this.ProductId}\"," +
        $"\"{this.Name}\"," +
        $"\"{this.Description}\"," +
        $"\"{this.EuroPrice}\"," +
        $"\"{this.Category}\"";

        public double GetDollarPrice() => this.DollarPrice;
    }
}
