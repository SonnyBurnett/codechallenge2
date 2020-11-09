using GEP.CodeChallenge.Assignment2.Classes;
using GEP.CodeChallenge.Assignment2.Interfaces;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace GEP.CodingChallenge.Assignment2.Tests
{
    [TestClass]
    public class FileOutputProcessorTests
    {
        [TestMethod]
        public void OutputFileTest()
        {
            IFileOutputProcessor outputProcessor = new FileOutputProcessor();
            outputProcessor.OutputFile(Constants.OUTPUT_FILE_PATH, "WINNER X");

            if (!File.Exists(Constants.OUTPUT_FILE_PATH))
                Assert.Fail(Constants.OUTPUT_FAILED);
        }
    }
}
