using System;
using System.Windows.Input;
using Tw.Ing.Challenge2.Services;

namespace Tw.Ing.Challenge2.Commands
{
    internal class EndGameCommand : GameCommandBase
    {
        private readonly GameContext _game;
        public EndGameCommand(IGameService gameService, GameContext game):base(gameService) => _game = game;

        public override char[] Key { get; } = Array.Empty<char>();
        public override string Title { get => ""; }
        public override void Execute(char key)
        {
            _game.End();
            _game.Board.End();
        }
    }
}
