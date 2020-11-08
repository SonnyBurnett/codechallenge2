using GEP.CodeChallenge.Assignment2.Interfaces;
using System.IO;

namespace GEP.CodeChallenge.Assignment2.Classes
{
    public class FileInputProcessor : IFileInputProcessor
    {
        private int _dimensions;

        public FileInputProcessor(int dimensions)
        {
            _dimensions = dimensions;
        }

        public char[,] ProcessFile(string inputPath)
        {
            char[,] input = new char[_dimensions, _dimensions];

            StreamReader file = new StreamReader(inputPath);
            for (int seq = 0; seq < _dimensions; seq++)
            {
                var line = file.ReadLine();
                input[seq, 0] = line[0];
                input[seq, 1] = line[1];
                input[seq, 2] = line[2];
            }

            return input;

        }

    }
}
