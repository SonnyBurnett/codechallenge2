using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge.Services;

namespace Tw.Ing.Challenge.Commands
{
    public sealed class ChallengeCommand : ICommandAsync
    {
        private readonly ICsvService _csvService ;
        public ChallengeCommand(ICsvService service)
        {
            _csvService = service;
        }

        async Task ICommandAsync.Execute()
        {
            await _csvService.DownloadCsv(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csvv")).ConfigureAwait(false);
        }
    }
}
