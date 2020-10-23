using System;
using System.Windows.Input;

namespace Tw.Ing.Challenge2.Commands
{
    internal class StartGameCommand : IGameCommand
    {
        private readonly GameContext _game;
        public StartGameCommand(GameContext game) => _game = game;

        public char Key { get => 'S'; }

        public void Execute()
        {
            var board = new BoardContext();
            board.Initialize();
            var p1 = new PlayerContext(board);
            p1.Register("I", Cell.Marker.Circle);
            var p2 = new PlayerContext(board);
            p2.Register("II", Cell.Marker.Cross);

            p1.GiveTurn();

            _game.Start(p1, p2, board);
        }
    }
}
