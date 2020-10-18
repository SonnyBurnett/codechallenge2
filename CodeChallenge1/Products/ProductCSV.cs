using CodeChallenge1.Utils;
using System;
using System.Collections.Generic;
using System.Text;

namespace CodeChallenge1.Products
{
    //class Product
    //{
    //    public string productId;
    //    public string name;
    //    public string description;
    //    public float price;
    //    public string category;
    //}

    class ProductCSV : IProduct
    {
        List<Dictionary<string,string>> productList;
        const float EURO = 0.85F;
        string path;

        public float convertPrice(float price, float currency = EURO)
        {
            return price * currency;
        }

        public void csvToProductList(string path)
        {
            this.path = path;
            productList = Files.csvFileToProductList(path);
        }

        public void writeToCSVProductsWithPriceAbove(float minPrice)
        {
            Files.listToCSVFile(getProductsWithPriceAbove(minPrice), path);
        }

        private List<Dictionary<string, string>> getProductsWithPriceAbove(float minPrice)
        {
            List<Dictionary<string, string>> listOfProductsWithPriceAbove = new List<Dictionary<string, string>>();
            foreach (Dictionary<string, string> product in productList)
            {
                var price = float.Parse(product["price"]);
                if (price > minPrice)
                {
                    product["price"] = convertPrice(price).ToString();
                    listOfProductsWithPriceAbove.Add(product);
                }
            }
            return listOfProductsWithPriceAbove;
        }
    }
}
