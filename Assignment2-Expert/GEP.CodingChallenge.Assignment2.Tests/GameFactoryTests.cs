using GEP.CodeChallenge.Assignment2.AbstractClasses;
using GEP.CodeChallenge.Assignment2.Classes;
using GEP.CodeChallenge.Assignment2.Enums;
using GEP.CodeChallenge.Assignment2.Interfaces;
using GEP.CodeChallenge.Constants;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace GEP.CodingChallenge.Assignment2.Tests
{
    [TestClass]
    public class GameFactoryTests
    {
        [TestMethod]
        public void InitializeTest()
        {
            IGameFactory gameFactory = new GameFactory(GameType.TIC_TAC_TOE, Constants.FILE_INPUT_PATH);
            var ticTacToeGame = gameFactory.GetGameInstance();

            Assert.IsNotNull(ticTacToeGame);
            Assert.IsInstanceOfType(ticTacToeGame, typeof(Game));
            Assert.AreEqual(ticTacToeGame.Dimensions, AppConstants.TIC_TAC_TOE_DIMENSIONS);
        }
    }
}
