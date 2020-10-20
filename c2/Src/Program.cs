using Microsoft.Extensions.CommandLineUtils;
using System;
using System.Diagnostics;
using System.Reflection;
using System.Threading.Tasks;
using Tw.Ing.Challenge2.Extensions;

namespace Tw.Ing.Challenge2
{
    class Program
    {
        static int Main(string[] args)
        {
            var tw = new TextWriterTraceListener(Console.Out);
            tw.TraceOutputOptions |= TraceOptions.None;
            Trace.Listeners.Add(tw);
            Trace.AutoFlush = true;
            Trace.Indent();

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

            if (versionOption.HasValue())
            {
                var assemblyVersion = Assembly.GetEntryAssembly().GetCustomAttribute<AssemblyInformationalVersionAttribute>().InformationalVersion;
                TraceExtensions.DoMessage($"Version: {assemblyVersion}");
                return 0;
            }
            else
            {
                // execute command
                try
                {
                    // Game Loop
                    return 0;
                }
                catch (Exception)
                {
                    TraceExtensions.DoError($"Something fishy happened, exiting.");
                    throw;
                }
            }
        }
    }
}
