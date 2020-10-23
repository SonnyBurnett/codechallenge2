using System.Collections.Generic;
using System.Windows.Input;
using Tw.Ing.Challenge2.Commands;

namespace Tw.Ing.Challenge2
{
    interface IGameEngine
    {
        public bool IsDirty { get; set; }
        public bool CanContinue { get; set; }
        public IEnumerable<IGameCommand> GetActionCommands();
        public void Draw();
    }
}
