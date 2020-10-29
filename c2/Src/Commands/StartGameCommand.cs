using System;
using System.Windows.Input;
using Tw.Ing.Challenge2.Services;

namespace Tw.Ing.Challenge2.Commands
{
    internal class StartGameCommand : GameCommandBase
    {
        private readonly GameContext _game;
        public StartGameCommand(IGameService gameService, GameContext game):base(gameService) => _game = game;

        public override char[] Key { get; } = { 's' };
        public override string Title { get => "Press 's' to start playing."; }
        public override void Execute(char key)
        {
            _game.Board.Initialize();
            _game.PlayerCircle.Register("I", Cell.Marker.Circle);
            _game.PlayerCross.Register("II", Cell.Marker.Cross);

            _game.Start();
        }
    }
}
