using System;
using System.Windows.Input;
using Tw.Ing.Challenge2.Services;

namespace Tw.Ing.Challenge2.Commands
{
    internal abstract class GameCommandBase
    {
        protected readonly ITwgeService GameService;
        public GameCommandBase(ITwgeService gameService) => GameService = gameService;

        public abstract char[] Key { get; }
        public abstract string Title { get; }
        public abstract void Execute(char key);
    }
}
