using System;
using System.Windows.Input;
using Tw.Ing.Challenge2.Services;

namespace Tw.Ing.Challenge2.Commands
{
    internal class ResetGameCommand : GameCommandBase
    {
        private IGameContext _game;
        public ResetGameCommand(ITwgeService gameService, IGameContext game) : base(gameService) {_game = game;}

        public override char[] Key { get; } = { ' ' };
        public override string Title { get => "Press <space> to continue"; }
        public override void Execute(char key)
        {
            if (_game != null)
            {
                GameService.UnRegisterGameObject((ITweContext)_game);
                GameService.UnRegisterGameObject(_game.Board);
                GameService.UnRegisterGameObject(_game.PlayerCross);
                GameService.UnRegisterGameObject(_game.PlayerCircle);
            }
            _game = new GameContext(GameService);
            _game.Board = new BoardContext(GameService);
            _game.PlayerCross = new PlayerContext(GameService, _game.Board);
            _game.PlayerCircle = new PlayerContext(GameService, _game.Board);

        }
    }
}
