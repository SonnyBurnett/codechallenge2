using System;
using System.Windows.Input;
using Tw.Ing.Challenge2.Services;

namespace Tw.Ing.Challenge2.Commands
{
    internal class PlayColumnCommand : GameCommandBase
    {
        private readonly PlayerContext _player;
        public PlayColumnCommand(ITwgeService gameService, PlayerContext player) : base(gameService) 
        { 
            _player = player; 
        }

        public override char[] Key { get; } = { 'a', 'b', 'c' };
        public override string Title { get => "Press 'a,b,c' to select a column."; }
        public override void Execute(char key)
        {
            _player.SelectColumn(key);
        }
    }
}
