using GEP.CodeChallenge.Assignment3.Classes;
using GEP.CodeChallenge.Assignment3.Enums;
using GEP.CodeChallenge.Assignment3.Interfaces;
using GEP.CodeChallenge.Assignment3.Model;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Text;

namespace GEP.CodingChallenge.Assignment3.Tests
{
    [TestClass]
    public class ShippingStrategyTests
    {
        [TestMethod]
        public void NLShippingWeightLessThanTenTests()
        {
            CountryShippingModel model = new CountryShippingModel { Country = Country.Netherlands, Weight = 8 };
            IShippingStrategy NLShipping = new NetherlandsShippingStrategy();
            var expectedData = NLShipping.ShippingInformation(model);

            Assert.AreEqual(expectedData.Shipper, Shipper.PostNL);
            Assert.AreEqual(expectedData.Cost, 6.95);
        }

        [TestMethod]
        public void NLShippingWeightMoreThanTenTests()
        {
            CountryShippingModel model = new CountryShippingModel { Country = Country.Netherlands, Weight = 12 };
            IShippingStrategy NLShipping = new NetherlandsShippingStrategy();
            var expectedData = NLShipping.ShippingInformation(model);

            Assert.AreEqual(expectedData.Shipper, Shipper.DHL);
            Assert.AreEqual(expectedData.Cost, 30.95);
        }

        [TestMethod]
        public void BelgiumShippingTests()
        {
            CountryShippingModel model = new CountryShippingModel { Country = Country.Belgium, Weight = 10 };
            IShippingStrategy NLShipping = new BelgiumShippingStrategy();
            var expectedData = NLShipping.ShippingInformation(model);

            Assert.AreEqual(expectedData.Shipper, Shipper.BelgioPosto);
            Assert.AreEqual(expectedData.Cost, 11.95);
        }

        [TestMethod]
        public void PostNLDurationTests()
        {
            CountryShippingModel model = new CountryShippingModel { Country = Country.Belgium, Weight = 8 };
            IShippingStrategy NLShipping = new NetherlandsShippingStrategy();
            var expectedData = NLShipping.ShippingInformation(model);

            Assert.AreEqual(expectedData.Duration, 1);
        }

        [TestMethod]
        public void DHLDurationTests()
        {
            CountryShippingModel model = new CountryShippingModel { Country = Country.Other, Weight = 8 };
            IShippingStrategy NLShipping = new OtherCountryShippingStrategy();
            var expectedData = NLShipping.ShippingInformation(model);

            Assert.AreEqual(expectedData.Duration, 4);

            model.Weight = 12;
            expectedData = NLShipping.ShippingInformation(model);

            Assert.AreEqual(expectedData.Duration, 8);
        }

        [TestMethod]
        public void BelgioPostoDurationTests()
        {
            CountryShippingModel model = new CountryShippingModel { Country = Country.Belgium, Weight = 8 };
            IShippingStrategy NLShipping = new BelgiumShippingStrategy();
            var actualData = NLShipping.ShippingInformation(model);

            Assert.AreEqual(2, actualData.Duration);
          
        }
    }
}
