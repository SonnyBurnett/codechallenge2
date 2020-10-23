using System.Collections.Generic;
using System.Windows.Input;
using Tw.Ing.Challenge2.Commands;

namespace Tw.Ing.Challenge2
{
    internal interface IGameEngine
    {
        public bool IsDirty { get; set; }
        public IEnumerable<GameCommandBase> GetActionCommands();
        public void Draw();
    }
}
