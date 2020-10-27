using CodeChallenge1.Utils;
using System;
using System.Collections.Generic;
using System.Text;

namespace CodeChallenge1.Products
{
    interface IProduct
    {
        void csvToProductList(string path);
        float convertPrice(float price, float currency);
        void writeToCSVProductsWithPriceAbove(float minPrice);
    }
}
