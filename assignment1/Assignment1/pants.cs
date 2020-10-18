// Copyright (c) DEVCOP. All Rights Reserved.
namespace Assignment1.Services
{
    public class Pants : Clothing
    {
        // specific properties for pants
        private readonly string category = "pants";

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
            this.Legs = legs;
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
            this.Legs = 1;
        }

        protected int Legs
        {
            get;
            set;
        }
    }
}
