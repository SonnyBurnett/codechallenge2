using GEP.CodeChallenge.Assignment2;
using GEP.CodeChallenge.Assignment2.Enums;
using GEP.CodeChallenge.Assignment2.Helper;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Text;

namespace GEP.CodingChallenge.Assignment2.Tests
{
    [TestClass]
    public class OutputHelperTests
    {
        [TestMethod]
        public void GenerateWinnerOutputTest()
        {
            var expectedOutput = "WINNER X";
            GameModel gameModel = new GameModel { CurrentStatus = CurrentStatus.Win, Player = Player.X };
            var actualOutput = OutputHelper.GenerateOutput(gameModel);

            Assert.AreEqual(expectedOutput, actualOutput);
        }

        [TestMethod]
        public void GenerateNextMoveOutputTest()
        {
            var expectedOutput = "NEXTMOVE O";
            GameModel gameModel = new GameModel { CurrentStatus = CurrentStatus.NextMove, Player = Player.O };
            var actualOutput = OutputHelper.GenerateOutput(gameModel);

            Assert.AreEqual(expectedOutput, actualOutput);
        }
    }
}
