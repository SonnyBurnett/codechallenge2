// Copyright (c) DEVCOP. All Rights Reserved.

namespace Assignment1.Services
{
    public class Shirts : Clothing
    {
        // specific properties for arms
        private readonly string category = "shirts";

        public Shirts(int productid, string name, string desc, int price, int arms)
        {
            this.ProductId = productid;
            this.Name = name;
            this.Category = this.category;
            this.Description = desc;
            this.DollarPrice = price;
            this.Arms = arms;
        }

        public Shirts(int id, string name, string desc, int price)
        {
            this.ProductId = id;
            this.Name = name;
            this.Category = this.category;
            this.Description = desc;
            this.DollarPrice = price;
            this.Arms = 1;
        }

        protected int Arms { get; set; }
    }
}
