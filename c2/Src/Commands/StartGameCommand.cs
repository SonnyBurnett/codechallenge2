using System;
using System.Windows.Input;
using Tw.Ing.Challenge2.Services;

namespace Tw.Ing.Challenge2.Commands
{
    internal class StartGameCommand : GameCommandBase
    {
        private readonly GameContext _game;
        public StartGameCommand(IGameService gameService, GameContext game):base(gameService) => _game = game;

        public override char Key { get => 's'; }
        public override string Title { get => "Press 's' to start playing."; }
        public override void Execute(char key)
        {
            var board = new BoardContext(GameService);
            board.Initialize();
            var p1 = new PlayerContext(GameService, board);
            p1.Register("I", Cell.Marker.Circle);
            var p2 = new PlayerContext(GameService, board);
            p2.Register("II", Cell.Marker.Cross);

            p1.GiveTurn();

            _game.Start(p1, p2, board);
        }
    }
}
