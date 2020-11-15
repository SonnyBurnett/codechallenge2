using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tw.Ing.Challenge3.Command
{
    public interface ICommandAsync
    {
        public bool CanExecute();
        public Task<int> Execute();
    }
}
