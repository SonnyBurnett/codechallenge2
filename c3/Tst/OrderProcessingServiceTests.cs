using Moq;
using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Model;
using Tw.Ing.Challenge3.Service;
using Tw.Ing.Challenge3.Tests.Plumbing;
using Xunit;

namespace Tw.Ing.Challenge3.Tests
{
    public class OrderProcessingServiceTests
    {
        [Fact]
        public void LineToOrder_Success()
        {
            // ARRANGE

            IOrderProcessingService srv = new OrderProcessingService();

            var line = new OrderLine() { CustomerId = 1, Weight = 1.1 };
            
            // ACT
            var order = srv.LineToOrder(null, line);

            // ASSESS
            Assert.Equal(1, order.CustomerId);
            Assert.Equal(1.1, order.Weight);
            Assert.Single(order.OrderLines);
        }

        [Fact]
        public void OrderToShippingAssignment_Success()
        {
            // ARRANGE

            IOrderProcessingService srv = new OrderProcessingService();

            var line = new OrderLine() { CustomerId = 1, Weight = 1.1 };
            var order = new CustomerOrder() { CustomerId = 1, Weight = 1.1 };
            order.OrderLines.Add(line);

            // ACT
            var shipping = srv.OrderShippingAssignment(order);

            // ASSESS
            Assert.Equal(1, shipping.Order.CustomerId);
        }

        [Fact]
        public void ShippingAssignmentToConfirmation_Success()
        {
            // ARRANGE

            IOrderProcessingService srv = new OrderProcessingService();

            var line = new OrderLine() { CustomerId = 1, Weight = 1.1 };
            var order = new CustomerOrder() { CustomerId = 1, Weight = 1.1 };
            order.OrderLines.Add(line);
            var assignment = new ShippingAssignment() { Order = order };
            // ACT
            var sc = srv.ShippingConfirmation(assignment);

            // ASSESS
            Assert.Equal(1, sc.Order.CustomerId);
        }
    }
}
