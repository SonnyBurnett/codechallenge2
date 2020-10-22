using System.Collections.Generic;
using System.Windows.Input;

namespace Tw.Ing.Challenge2
{
    interface IGameServiceInterface
    {
        public IEnumerable<ICommand> AvailableCommands();
    }
}
