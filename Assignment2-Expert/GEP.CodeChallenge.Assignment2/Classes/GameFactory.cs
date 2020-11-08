using GEP.CodeChallenge.Assignment2.AbstractClasses;
using GEP.CodeChallenge.Assignment2.Enums;
using GEP.CodeChallenge.Assignment2.Interfaces;
using GEP.CodeChallenge.Constants;

namespace GEP.CodeChallenge.Assignment2.Classes
{
    public class GameFactory : IGameFactory
    {
        private GameType _gameType;
        private string _filePath;

        public GameFactory(GameType gameType, string filePath)
        {
            _gameType = gameType;
            _filePath = filePath;
        }
        public Game GetGameInstance()
        {
            Game gameInstance = null;
            switch (_gameType)
            {
                case GameType.TIC_TAC_TOE:
                    gameInstance = new TicTacToe(AppConstants.TIC_TAC_TOE_NAME, _filePath);
                    break;
            }

            return gameInstance;
        }
    }
}
