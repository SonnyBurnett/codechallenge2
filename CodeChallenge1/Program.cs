using CodeChallenge1.Products;
using CodeChallenge1.Utils;
using System;

namespace CodeChallenge1
{
    class Program
    {
        static void Main(string[] args)
        {
            ProductCSV productCSV = new ProductCSV();
            productCSV.csvToProductList(@"C:\Users\YK47BU\source\repos\CodeChallenge1\CodeChallenge1\resources\001-experts-inputs.csv");
            productCSV.writeToCSVProductsWithPriceAbove(10);
        }
    }
}
