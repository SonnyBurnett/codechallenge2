using Microsoft.Extensions.Logging;

namespace GEP.CodeChallenge.Assignment2.Logger
{

    public static class Logging
    {
        public static ILoggerFactory LoggerFactory { get; } = new LoggerFactory();
        public static ILogger CreateLogger<T>() =>
        LoggerFactory.CreateLogger<T>();
    }

}
