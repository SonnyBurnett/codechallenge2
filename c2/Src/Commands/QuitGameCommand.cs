using System;
using System.Windows.Input;
using Tw.Ing.Challenge2.Services;

namespace Tw.Ing.Challenge2.Commands
{
    internal class QuitGameCommand : GameCommandBase
    {
        public QuitGameCommand(IGameService gameService) : base(gameService) { }

        public override char[] Key { get; } = { 'q' };
        public override string Title { get => "Press 'q' to end the game"; }
        public override void Execute(char key)
        {
            GameService.Quit();
        }
    }
}
