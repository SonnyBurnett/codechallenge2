using CommandLine;
using System.IO;

namespace CodeChallenge
{
    public class Options
    {
        private string inputFilePath;
        private string outputFilePath;


        [Option(Default = (double)0.85)]
        public double ExchangeRate { get; set; }

        [Option(
            'i',
            "input-file",
            Required = true,
            HelpText = "Path to the input file.")]
        public string InputFilePath
        {
            get
            {
                return this.inputFilePath;
            }

            set
            {
                string fullpath = Path.GetFullPath(value);

                if (!File.Exists(fullpath))
                {
                    throw new FileNotFoundException($"Input file was not found at '{fullpath}' ", fullpath);
                }

                this.inputFilePath = fullpath;
            }
        }

        [Option(
            'o',
            "output-file",
            Required = true,
            HelpText = "Path to the output file.",
            Default = "outputs.csv")]
        public string OutpuFilePath
        {
            get
            {
                return this.outputFilePath;
            }

            set
            {
                string fullpath = Path.GetFullPath(value);

                if (!File.Exists(fullpath))
                {
                    throw new FileNotFoundException($"Input file was not found at '{fullpath}' ", fullpath);
                }

                this.outputFilePath = fullpath;
            }
        }
    }
}
