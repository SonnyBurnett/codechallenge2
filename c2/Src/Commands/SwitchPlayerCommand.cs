using System;
using System.Windows.Input;
using Tw.Ing.Challenge2.Services;

namespace Tw.Ing.Challenge2.Commands
{
    internal class SwitchPlayerCommand : GameCommandBase
    {
        private readonly PlayerContext _player;
        public SwitchPlayerCommand(IGameService gameService, PlayerContext player) : base(gameService) 
        { 
            _player = player; 
        }

        public override char[] Key { get; } = Array.Empty<char>();
        public override string Title { get => ""; }
        public override void Execute(char key)
        {
            _player.GiveTurn();
        }
    }
}
