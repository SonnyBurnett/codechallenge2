using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Input;

namespace Tw.Ing.Challenge2
{
    interface IGameServiceInterface
    {
        public IEnumerable<ICommand> AvailableCommands();
    }
}
