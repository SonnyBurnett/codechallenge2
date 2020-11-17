using Microsoft.Extensions.CommandLineUtils;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Net.Http;
using System.Reflection;
using System.Threading;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Command;
using Tw.Ing.Challenge3.Extensions;
using Tw.Ing.Challenge3.Service;
using Tw.Ing.Challenge3.Service.Order;

namespace Tw.Ing.Challenge3
{
    class Program
    {
        static async Task<int> Main(string[] args)
        {
            Thread.CurrentThread.CurrentCulture = System.Globalization.CultureInfo.InvariantCulture;

            var tw = new TextWriterTraceListener(Console.Out);
            tw.TraceOutputOptions |= TraceOptions.None;
            Trace.Listeners.Add(tw);
            Trace.AutoFlush = true;
            Trace.Indent();

            // Setup
            var httpClient = new HttpClient();
            var fileService = new CsvFileService(httpClient);

            var app = new CommandLineApplication
            {
                Name = "TW ING Coding Challenge",
                Description = "The TeamWildenberg console app for the different assignments in the ING Coding Challenge"
            };
            app.HelpOption("-?|-h|--help");
            var versionOption = app.Option("-v|--version", "Check which version we are running", CommandOptionType.NoValue);

            app.OnExecute(() =>
            {
                return 0;
            });
            app.Execute(args);

            ICommandAsync cmd = null;

            if (versionOption.HasValue())
            {
                cmd = new AssemblyVersionCommand();
            }
            else
            {
                cmd = new PrintOrdersCommand(fileService);
            }

            return await cmd.Execute().ConfigureAwait(false);
        }
    }
}
