using CommandLine;
using System.IO;

namespace CodeChallenge
{
    public class Options
    {
        private string inputFilePath;

        [Option('r',
            "ExchangeRate",
            HelpText = "Dollar to Euro exchange rate.",
            Default = (float)0.85)]
        public float ExchangeRate { get; set; }

        [Option('p',
            "PricePoint",
            HelpText = "Path to the input file.",
            Default = (float)10)]
        public float PricePoint { get; set; }

        [Option(
            'i',
            "InputFile",
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
            "OutputFile",
            HelpText = "Path to the output file.",
            Default = "outputs.csv")]
        public string OutpuFilePath { get; set; }
    }
}
