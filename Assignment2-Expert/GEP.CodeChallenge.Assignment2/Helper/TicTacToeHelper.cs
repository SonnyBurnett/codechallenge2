using GEP.CodeChallenge.Assignment2.AbstractClasses;
using GEP.CodeChallenge.Assignment2.Enums;

namespace GEP.CodeChallenge.Assignment2.Helper
{
    public static class TicTacToeHelper
    {

        public static Player CheckRow(Game game, int row)
        {
            if (game.Board[row, 0] == game.Board[row, 1] &&
                game.Board[row, 1] == game.Board[row, 2])
            {
                return ReturnPlayer(game, row, 0);
            }

            return Player.None;
        }

        public static Player CheckColumn(Game game, int col)
        {
            if (game.Board[0, col] == game.Board[1, col] &&
                game.Board[1, col] == game.Board[2, col])
            {
                return ReturnPlayer(game, 0, col);
            }

            return Player.None;
        }

        public static Player CheckDiagonal(Game game)
        {
            if (game.Board[0, 0] != ' ')
            {
                if (game.Board[0, 0] == game.Board[1, 1] &&
                   game.Board[1, 1] == game.Board[2, 2])
                {
                    return ReturnPlayer(game, 0, 0);
                }
            }

            else if (game.Board[0, 2] != ' ')
            {
                if (game.Board[0, 2] == game.Board[1, 1] &&
                   game.Board[1, 1] == game.Board[2, 0])
                {
                    return ReturnPlayer(game, 0, 2);
                }
            }

            return Player.None;
        }

        public static Player NextMove(Game game)
        {
            int countX = 0;
            int countO = 0;

            for (int row = 0; row < game.Dimensions; row++)
                for (int col = 0; col < game.Dimensions; col++)
                {
                    if (game.Board[row, col] == (char)Player.X)
                    {
                        countX++;
                    }

                    else if (game.Board[row, col] == (char)Player.O)
                    {
                        countO++;
                    }
                }

            if (countX > countO)
                return Player.O;
            else
                return Player.X;
        }

        private static Player ReturnPlayer(Game game, int row, int col)
        {
            return game.Board[row, col] == (char)Player.X ? Player.X : Player.O;
        }
    }
}
