using System;
using System.Windows.Input;
using Tw.Ing.Challenge2.Services;

namespace Tw.Ing.Challenge2.Commands
{
    internal class PlayRowCommand : GameCommandBase
    {
        private readonly PlayerContext _player;
        public PlayRowCommand(IGameService gameService, PlayerContext player) : base(gameService) 
        { 
            _player = player; 
        }

        public override char[] Key { get; } = { '1', '2', '3' };
        public override string Title { get => "Press '1', '2', '3' to select row."; }
        public override void Execute(char key)
        {
            _player.SelectRow(key - 48);
            _player.MakeMove();
        }
    }
}
