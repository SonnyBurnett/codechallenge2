// Assignment 1 for the code challenge 2

namespace Assignment1.Services
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    public static class Assignment1
    {
        public static void Main(string[] args)
        {
            int max_dollar_price = 10;
            string outputfile = "outputs.csv";
            string inputfile = "inputs.csv";
            string inputpath = $"{inputfile}";
            string outputpath = $"{outputfile}";
            string[] input_lines = System.IO.File.ReadAllLines(path: inputpath);

            // store headers for later
            string headers = input_lines[0];

            // remove headers from array.
            input_lines = input_lines.Skip(1).ToArray();
            List<Clothing> clothingRecords = (from string input_line in input_lines
                                              select ClothingFactory(input_line.Split(","))).ToList();
            using (System.IO.StreamWriter file =
            new System.IO.StreamWriter(path: outputpath))
            {
                file.WriteLine(headers);
                foreach (Clothing clothing in clothingRecords)
                {
                    if (clothing.GetDollarPrice() >= max_dollar_price)
                    {
                        file.WriteLine(clothing.ToString());
                    }
                }
            }
        }

        public static Clothing ClothingFactory(string[] clothingdescription)
        {
            var productId = int.Parse(clothingdescription[0]);
            string name = clothingdescription[1].Trim();
            string desc = clothingdescription[2].Trim();
            int price = int.Parse(clothingdescription[3]);
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
