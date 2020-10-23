using System.Collections.Generic;
using System.Windows.Input;
using Tw.Ing.Challenge2.Commands;

namespace Tw.Ing.Challenge2
{
    interface IGameEngineState
    {
        public IEnumerable<GameCommandBase> GetActionCommands();
        public void Draw();
    }
}
