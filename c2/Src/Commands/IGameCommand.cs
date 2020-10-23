using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge2.Commands
{
    public interface IGameCommand
    {
        public void Execute();
        public char Key { get; }
    }
}
