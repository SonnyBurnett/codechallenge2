using CommandLine;
using System;
using System.Threading.Tasks;

namespace CodeChallenge
{
    public static class Program
    {
        private const int ErrorBadArguments = 0xA0;

        /// <summary>
        /// Entry point of the command-line utility.
        /// </summary>
        /// <param name="args">Command line arguments.</param>
        /// <returns>A <see cref="Task"/> representing the result of the asynchronous operation.</returns>
        public static async Task Main(string[] args)

        {
            using (var parser = new Parser(with =>
            {
                with.CaseSensitive = false;
                with.CaseInsensitiveEnumValues = true;
                with.EnableDashDash = true;
                with.HelpWriter = Console.Out;
            }))
            {

                Options options = null;

                parser.ParseArguments<Options>(args)
                    .WithParsed(opts => options = opts)
                    .WithNotParsed(errors => Environment.Exit(ErrorBadArguments));




            }
        }
    }
}
