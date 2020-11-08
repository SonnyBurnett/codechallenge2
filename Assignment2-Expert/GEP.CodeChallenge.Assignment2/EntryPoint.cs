using GEP.CodeChallenge.Assignment2.Classes;
using GEP.CodeChallenge.Assignment2.Enums;
using GEP.CodeChallenge.Assignment2.Helper;
using GEP.CodeChallenge.Assignment2.Interfaces;
using GEP.CodeChallenge.Assignment2.Logger;
using GEP.CodeChallenge.Constants;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;

namespace GEP.CodeChallenge.Assignment2
{
    public class EntryPoint
    {
        public static void Execute(string inputPath, string outputPath)
        {
            ILogger logger = Logging.CreateLogger<EntryPoint>();

            var gameType = GameType.TIC_TAC_TOE;

            var serviceProvider = new ServiceCollection()
            .AddSingleton<IGameFactory>(x => new GameFactory(gameType, inputPath))
            .AddSingleton<IGameStatus>(x => new GameStatus())
            .AddSingleton<IFileOutputProcessor>(x => new FileOutputProcessor())
            .BuildServiceProvider();

            var gameFactory = serviceProvider.GetService<IGameFactory>();
            var gameStatus = serviceProvider.GetService<IGameStatus>();
            var fileOutputProcessor = serviceProvider.GetService<IFileOutputProcessor>();

            var ticTacToeInstance = gameFactory.GetGameInstance();

            var gameOutputModel = gameStatus.Evaluate(ticTacToeInstance);

            var result = OutputHelper.GenerateOutput(gameOutputModel);
            fileOutputProcessor.OutputFile(outputPath, result);

            logger.LogInformation(AppConstants.MESSAGE_OUTPUT_GENERATED);
        }
    }
}
