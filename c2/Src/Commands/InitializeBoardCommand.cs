using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Input;

namespace Tw.Ing.Challenge2.Commands
{
    internal class InitializeBoardCommand : ICommand
    {
        event EventHandler ICommand.CanExecuteChanged
        {
            add
            {
                throw new NotImplementedException();
            }

            remove
            {
                throw new NotImplementedException();
            }
        }

        bool ICommand.CanExecute(object parameter)
        {
            throw new NotImplementedException();
        }

        void ICommand.Execute(object parameter)
        {
            throw new NotImplementedException();
        }
    }
}
