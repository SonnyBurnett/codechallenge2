using System.Collections.Generic;
using Tw.Ing.Challenge2.Commands;

namespace Tw.Ing.Challenge2
{
    interface ITweContextState
    {
        public IEnumerable<GameCommandBase> GetActionCommands();
        public void Draw();
    }
}
