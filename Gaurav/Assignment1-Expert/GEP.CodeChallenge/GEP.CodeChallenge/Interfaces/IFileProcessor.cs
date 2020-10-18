using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace GEP.CodeChallenge.Interfaces
{
    public interface IFileProcessor<T, K>
    {
        IList<T> ProcessFile(StreamReader reader);

        void OutputFile(K output, string filePath);
    }
}
