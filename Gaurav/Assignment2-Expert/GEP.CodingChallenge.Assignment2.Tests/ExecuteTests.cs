using GEP.CodeChallenge.Assignment2;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.IO;

namespace GEP.CodingChallenge.Assignment2.Tests
{
    [TestClass]
    public class ExecuteTests
    {
        [TestMethod]
        public void ExecuteTest()
        {
            EntryPoint.Execute(Constants.FILE_INPUT_PATH, Constants.OUTPUT_FILE_PATH);

            if (!File.Exists(Constants.OUTPUT_FILE_PATH))
                Assert.Fail(Constants.OUTPUT_FAILED);
        }
    }
}
