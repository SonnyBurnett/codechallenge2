using Microsoft.Extensions.CommandLineUtils;
using System;
using System.Diagnostics;
using System.Net.Http;
using System.Reflection;
using System.Threading.Tasks;
using Tw.Ing.Challenge.Commands;
using Tw.Ing.Challenge.Extensions;
using Tw.Ing.Challenge.Services;

namespace Tw.Ing.Challenge
{
    class Program
    {
        static async Task<int> Main(string[] args)
        {
            var tw = new TextWriterTraceListener(Console.Out);
            tw.TraceOutputOptions |= TraceOptions.None;
            Trace.Listeners.Add(tw);
            Trace.AutoFlush = true;
            Trace.Indent();

            var app = new CommandLineApplication
            {
                Name = "TW ING Coding Challenge",
                Description = "The TeamWildenber console app for the different assignments in the ING Coding Challenge"
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
                // instantiate services
                ICsvService csvService = new CsvService(new HttpClient());

                // execute command
                try
                {
                    ICommandAsync cmd = new ChallengeCommand(csvService);
                    await cmd.Execute().ConfigureAwait(false);
                    return 0;
                }
                catch (CsvServiceException x)
                {
                    TraceExtensions.DoError(x.Message);
                    return -1;
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
