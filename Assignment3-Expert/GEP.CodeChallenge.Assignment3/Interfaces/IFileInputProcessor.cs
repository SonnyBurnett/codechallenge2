using System.Collections.Generic;
using System.IO;

namespace GEP.CodeChallenge.Assignment3.Interfaces
{
    public interface IFileInputProcessor<T>
    {
        IList<T> ProcessFile(StreamReader reader);

    }
}
