using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Text;

namespace Tw.Ing.Challenge2.Extensions
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
