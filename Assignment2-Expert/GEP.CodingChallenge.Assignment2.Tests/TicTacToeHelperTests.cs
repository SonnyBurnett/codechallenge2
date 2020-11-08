using GEP.CodeChallenge.Assignment2.AbstractClasses;
using GEP.CodeChallenge.Assignment2.Classes;
using GEP.CodeChallenge.Assignment2.Enums;
using GEP.CodeChallenge.Assignment2.Helper;
using GEP.CodeChallenge.Assignment2.Interfaces;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace GEP.CodingChallenge.Assignment2.Tests
{
    [TestClass]
    public class TicTacToeHelperTests
    {
        private static Game _game;
        private static IGameFactory _gameFactory;

        public static void CreateInstance(string path)
        {
            _gameFactory = new GameFactory(GameType.TIC_TAC_TOE, path);
            _game = _gameFactory.GetGameInstance();
        }

        [TestMethod]
        public void CheckRowTest()
        {
            CreateInstance(Constants.FILE_INPUT_PATH_CHECKROW);

            var expectedWinner = Player.X;
            var actualWinner = TicTacToeHelper.CheckRow(_game, 0);

            Assert.AreEqual(expectedWinner, actualWinner);
        }

        [TestMethod]
        public void CheckColumnTest()
        {
            CreateInstance(Constants.FILE_INPUT_PATH_CHECKCOLUMN);

            var expectedWinner = Player.O;
            var actualWinner = TicTacToeHelper.CheckColumn(_game, 0);

            Assert.AreEqual(expectedWinner, actualWinner);
        }

        [TestMethod]
        public void CheckDiagonalTest()
        {
            CreateInstance(Constants.FILE_INPUT_PATH_CHECKDIAGONAL);

            var expectedWinner = Player.X;
            var actualWinner = TicTacToeHelper.CheckDiagonal(_game);

            Assert.AreEqual(expectedWinner, actualWinner);
        }
    }
}
