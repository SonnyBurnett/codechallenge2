﻿using Microsoft.Extensions.CommandLineUtils;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Reflection;
using Tw.Ing.Challenge2.Commands;
using Tw.Ing.Challenge2.Extensions;
using Tw.Ing.Challenge2.Services;

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
                try
                {
                    // initialize
                    
                    const int REFRESH_TIME_MS = 500;
                    var gameService = new TwgeService();
                    var game = new ResetGameCommand(gameService, null);
                    game.Execute(char.MinValue);

                    Console.Clear();

                    var nextLoopTime = DateTime.UtcNow.AddMilliseconds(REFRESH_TIME_MS);
                    //Game loop
                    do
                        if (nextLoopTime < DateTime.UtcNow)
                        {
                            gameService.Draw();
                            nextLoopTime = DateTime.UtcNow.AddMilliseconds(REFRESH_TIME_MS);

                            var commandList = gameService.GetActionCommands();
                            gameService.DoAction(commandList);
                        }
                    while (gameService.CanContinue);
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
