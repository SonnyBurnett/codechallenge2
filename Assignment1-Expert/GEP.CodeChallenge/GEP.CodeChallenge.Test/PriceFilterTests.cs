using GEP.CodeChallenge.Classes;
using GEP.CodeChallenge.Constants;
using GEP.CodeChallenge.Model;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Text;

namespace GEP.CodeChallenge.Test
{
    [TestClass]
    public class DataFilterTests
    {
        [TestMethod]
        public void PriceFilterTest()
        {
            var expectedProductCount = 2;
            var productEntity1 = new ProductEntity { productId = 45848, name = "shorts", description = "short pants", price = 8, category = "pants" };
            var productEntity2 = new ProductEntity { productId = 4184688, name = "trousers", description = "trousers", price = 12, category = "pants" };
            var productEntity3 = new ProductEntity { productId = 848488, name = "blue shirt", description = "shirt", price = 88, category = "shirts" };
            var productEntityList = new List<ProductEntity>();
            productEntityList.Add(productEntity1);
            productEntityList.Add(productEntity2);
            productEntityList.Add(productEntity3);

            PriceFilter<ProductEntity> priceFilter = new PriceFilter<ProductEntity>(AppConstants.PRICE_THRESHOLD);
            var actualProductCount = priceFilter.Operation(productEntityList).Count;
            Assert.AreEqual(actualProductCount, expectedProductCount);

        }
    }
}
