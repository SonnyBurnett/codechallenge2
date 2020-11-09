using GEP.CodeChallenge.Assignment2.AbstractClasses;
using GEP.CodeChallenge.Assignment2.Enums;
using GEP.CodeChallenge.Assignment2.Helper;
using GEP.CodeChallenge.Assignment2.Interfaces;

namespace GEP.CodeChallenge.Assignment2.Classes
{
    public class GameStatus : IGameStatus
    {
        public GameModel Evaluate(Game ticTacToe)
        {
            var gameModel = new GameModel();
            var player = CheckCurrentStatus(ticTacToe);

            if (player == Player.None)
            {
                player = CheckNextMove(ticTacToe);
                gameModel.Player = player;
                gameModel.CurrentStatus = CurrentStatus.NextMove;
            }

            else
            {
                gameModel.Player = player;
                gameModel.CurrentStatus = CurrentStatus.Win;
            }

            return gameModel;
        }

        private Player CheckCurrentStatus(Game ticTacToe)
        {
            Player winner = Player.None;
            for (int i = 0; i < ticTacToe.Dimensions; i++)
            {
                winner = TicTacToeHelper.CheckRow(ticTacToe, i);
                if (winner == Player.None)
                {
                    winner = TicTacToeHelper.CheckColumn(ticTacToe, i);
                    if (winner != Player.None)
                        return winner;

                }
                else
                    break;
            }

            if (winner == Player.None)
                winner = TicTacToeHelper.CheckDiagonal(ticTacToe);

            return winner;
        }

        private Player CheckNextMove(Game game)
        {
            return TicTacToeHelper.NextMove(game);
        }

    }
}
