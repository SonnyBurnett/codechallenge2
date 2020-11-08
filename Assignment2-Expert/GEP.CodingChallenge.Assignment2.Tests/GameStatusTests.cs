using GEP.CodeChallenge.Assignment2;
using GEP.CodeChallenge.Assignment2.AbstractClasses;
using GEP.CodeChallenge.Assignment2.Classes;
using GEP.CodeChallenge.Assignment2.Enums;
using GEP.CodeChallenge.Assignment2.Interfaces;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace GEP.CodingChallenge.Assignment2.Tests
{
    [TestClass]

    public class GameStatusTests
    {
        private static Game _game;
        private static IGameFactory _gameFactory;

        public static void CreateInstance(string path)
        {
            _gameFactory = new GameFactory(GameType.TIC_TAC_TOE, path);
            _game = _gameFactory.GetGameInstance();
        }

        [TestMethod]
        public void EvaluateVictoryTest()
        {
            CreateInstance(Constants.FILE_INPUT_PATH);
            IGameStatus gameStatus = new GameStatus();
            var expectedWinner = new GameModel { CurrentStatus = CurrentStatus.Win, Player = Player.X };
            var actualWinner = gameStatus.Evaluate(_game);

            Assert.AreEqual(expectedWinner.CurrentStatus, actualWinner.CurrentStatus);
            Assert.AreEqual(expectedWinner.Player, actualWinner.Player);
        }

        [TestMethod]
        public void EvaluateNextMoveTest()
        {
            CreateInstance(Constants.FILE_INPUT_PATH_NEXTMOVE);
            IGameStatus gameStatus = new CodeChallenge.Assignment2.Classes.GameStatus();
            var expectedNext  = new GameModel { CurrentStatus = CurrentStatus.NextMove, Player = Player.O };
            var actualNext = gameStatus.Evaluate(_game);

            Assert.AreEqual(expectedNext.CurrentStatus, actualNext.CurrentStatus);
            Assert.AreEqual(expectedNext.Player, actualNext.Player);
        }
    }
}
