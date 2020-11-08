using GEP.CodeChallenge.Assignment2.Interfaces;
using System.IO;

namespace GEP.CodeChallenge.Assignment2.Classes
{
    public class FileOutputProcessor : IFileOutputProcessor
    {

        public void OutputFile(string filePath, string outputText)
        {
            using (StreamWriter file = new StreamWriter(filePath))
            {
                file.Write(outputText);
            }
        }

    }
}
