using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Extensions;

namespace Tw.Ing.Challenge3.Command
{
    public class AssemblyVersionCommand : ICommandAsync
    {
        Task<int> ICommandAsync.Execute()
        {
            var assemblyVersion = Assembly.GetEntryAssembly().GetCustomAttribute<AssemblyInformationalVersionAttribute>().InformationalVersion;
            TraceExtensions.DoMessage($"Version: {assemblyVersion}");
            return new ValueTask<int>(0).AsTask();
        }
    }
}
