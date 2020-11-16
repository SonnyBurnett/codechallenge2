using Moq;
using System;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Model;
using Tw.Ing.Challenge3.Service;
using Tw.Ing.Challenge3.Service.Order;
using Tw.Ing.Challenge3.Tests.Plumbing;
using Xunit;

namespace Tw.Ing.Challenge3.Tests
{
    public class CountryAdapterTests
    {
        [Fact]
        public void OrderNetherlands_9kg_Success()
        {
            // ARRANGE
            ICountryAdapter adapter = new CountryAdapter();
            var order = new CustomerOrder()
            {
                CustomerId = 1,
                Weight = 9,
                Country = "Netherlands"
            };
            // ACT
            var assignment = adapter.GetShippingPartner(order);

            // ASSESS
            Assert.Equal("PostNL", assignment);
        }
        [Fact]
        public void OrderNetherlands_10kg_Success()
        {
            // ARRANGE
            ICountryAdapter adapter = new CountryAdapter();
            var order = new CustomerOrder()
            {
                CustomerId = 1,
                Weight = 10,
                Country = "Netherlands"
            };
            // ACT
            var assignment = adapter.GetShippingPartner(order);

            // ASSESS
            Assert.Equal("DHL", assignment);
        }
        [Fact]
        public void OrderNetherlands_11kg_Success()
        {
            // ARRANGE
            ICountryAdapter adapter = new CountryAdapter();
            var order = new CustomerOrder()
            {
                CustomerId = 1,
                Weight = 11,
                Country = "Netherlands"
            };
            // ACT
            var assignment = adapter.GetShippingPartner(order);

            // ASSESS
            Assert.Equal("DHL", assignment);
        }
        [Fact]
        public void OrderBelgium_Success()
        {
            // ARRANGE
            ICountryAdapter adapter = new CountryAdapter();
            var order = new CustomerOrder()
            {
                CustomerId = 1,
                Weight = 11,
                Country = "Belgium"
            };
            // ACT
            var assignment = adapter.GetShippingPartner(order);

            // ASSESS
            Assert.Equal("BelgioPosto", assignment);
        }
        [Fact]
        public void OrderOther_Success()
        {
            // ARRANGE
            ICountryAdapter adapter = new CountryAdapter();
            var order = new CustomerOrder()
            {
                CustomerId = 1,
                Weight = 11,
                Country = "Germany"
            };
            // ACT
            var assignment = adapter.GetShippingPartner(order);

            // ASSESS
            Assert.Equal("DHL", assignment);
        }

    }
}
