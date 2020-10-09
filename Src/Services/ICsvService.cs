using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge.Commands;

namespace Tw.Ing.Challenge.Services
{
    public interface ICsvService<T>
    {
        Task<IEnumerable<T>> Load(Uri csvFile);
    }
}
