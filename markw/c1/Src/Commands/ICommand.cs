using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace Tw.Ing.Challenge.Commands
{
    public interface ICommandAsync
    {
        Task Execute();
    }
}
