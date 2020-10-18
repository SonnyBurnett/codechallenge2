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
            bool myresult = false;  
            Pants pants = new Pants(1234, "jeans", "skinny jeans", 12);
            if (pants.GetType().IsSubclassOf(typeof(Clothing)))
            {
                myresult = true;
            }
            Assert.IsTrue(myresult, "Pants of type Pants and Clothing");
        }
        [TestMethod]
        public void Assignment1_TestBaseClass_ReturnTrue()
        {
            bool myresult = false;
            Clothing clothing = new Clothing(1234, "hats", "trendy hat", 6969, "hat");
            if(clothing.GetType() == typeof(Clothing)) 
            { 
                myresult = true;
            }
            Assert.IsTrue(myresult, "My hat is of type clothing.");
        }
        [TestMethod]
        public void Assignment1_TestDerivedClassPants_ReturnTrue()
        {
            bool myresult = false;
            Pants pants = new Pants(92321344, "pants", "short pants", 1234);
            if(pants.GetType() == typeof(Pants)) 
            { 
                myresult = true;
            }
            Assert.IsTrue(myresult, "My short pants is of type pants.");
        }
        [TestMethod]
        public void Assignment1_TestDerivedClassShirts_ReturnTrue()
        {
            bool myresult = false;
            Shirts shirt = new Shirts(92321344, "shirts", "Shirt", 1234);
            if(shirt.GetType() == typeof(Shirts)) 
            { 
                myresult = true;
            }
            Assert.IsTrue(myresult, "My shirt is of type shirt.");
        }
        [TestMethod]
        public void Assignment1_TestShirtFactory_ReturnTrue()
        {
            bool myresult = false;
            string[] inputline = "848488, blue shirt, shirt,  88, shirts".Split(",");
            Clothing shirt = Assignment1.Services.Assignment1.ClothingFactory(inputline);
            if(shirt.GetType() == typeof(Shirts)) 
            { 
                myresult = true;
            }
            Assert.IsTrue(myresult, "My shirt is of type shirt.");
        }
        [TestMethod]
        
        public void Assignment1_TestHatFactory_ReturnTrue()
        {
            bool myresult = false;
            string[] inputline = "9000, blue hat, hat,  88, hats".Split(",");
            Clothing hat = Assignment1.Services.Assignment1.ClothingFactory(inputline);
            if(hat.GetType() == typeof(Clothing)) 
            { 
                myresult = true;
            }
            Assert.IsTrue(myresult, "My hat is of type clothing.");
        }
    }
}
