using System;
using System.Linq;
using System.Collections.Generic;
namespace Assignment1.Services
{
    public static class Assignment1
    {
        public static void Main(string[] args)
        {
            int max_price = 10;
            string outputfile = "outputs.csv";
            string inputfile = "inputs.csv";

            string[] lines = System.IO.File.ReadAllLines(@$"{ inputfile }");
            // store headers for later
            string headers = lines[0];
            // remove headers from array.
            lines = lines.Skip(1).ToArray();
            List<Clothing> ClothingRecords = new List<Clothing>();
            foreach (string line in lines)
            {
                ClothingRecords.Add(ClothingFactory(line.Split(",")));
            }
            using (System.IO.StreamWriter file =
            new System.IO.StreamWriter(@$"{ outputfile }"))
            {
                file.WriteLine(headers);
                foreach (Clothing clothing in ClothingRecords)
                {
                    if (clothing.GetDollarPrice() >= max_price)
                    {
                        file.WriteLine(clothing.ToString());
                    }
                }
            }
        }
        public static Clothing ClothingFactory(string[] clothingdescription)
        {
            int productId = Int32.Parse(clothingdescription[0]);
            string name = clothingdescription[1].Trim();
            string desc = clothingdescription[2].Trim();
            int price = Int32.Parse(clothingdescription[3]);
            string category = clothingdescription[4].Trim();

            if (category == "pants")
            {
                return new Pants(productId, name, desc, price);
            }
            else if (category == "shirts")
            {
                return new Shirts(productId, name, desc, price);
            }
            else
            {
                return new Clothing(productId, category, desc, price, name);
            }
        }
    }

}
