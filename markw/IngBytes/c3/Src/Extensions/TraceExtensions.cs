﻿using System;
using System.Diagnostics;

namespace Tw.Ing.Challenge3.Extensions
{
    public static class TraceExtensions
    {

        public static void DoMessage(String message)
        {
            Trace.TraceInformation(message);
        }

        public static void DoWarn(String message)
        {
            Console.ForegroundColor = ConsoleColor.Yellow;
            Trace.TraceWarning(message);
            Console.ResetColor();
        }

        public static void DoError(String message)
        {
            Console.ForegroundColor = ConsoleColor.Red;
            Trace.TraceError(message);
            Console.ResetColor();
        }

        }
}
