using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge.Services;

namespace Tw.Ing.Challenge.Commands
{
    public sealed class ChallengeCommand : ICommandAsync
    {
        private readonly ICsvService<Product> _csvService ;
        public ChallengeCommand(ICsvService<Product> service)
        {
            _csvService = service;
        }

        async Task ICommandAsync.Execute()
        {
            await _csvService.Load(new Uri("https://henrybeen.nl/wp-content/uploads/2020/10/001-experts-inputs.csv")).ConfigureAwait(false);
        }
    }
}
