using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Assignment1.Services;

namespace Assignment1.Tests
{
    [TestClass]
    public class UnitTest1
    {
        [TestMethod]
        public void Assignment1_IsPolyMorphic_ReturnTrue()
        {
            var myresult = false;  
            Pants pants = new Pants(1234, "jeans", "skinny jeans", 12);
            if (pants.GetType().IsSubclassOf(typeof(Clothing)))
            {
                myresult = true;
            }
            Assert.IsTrue(myresult, "Pants of type Pants and Clothing");
        }
    }
}
