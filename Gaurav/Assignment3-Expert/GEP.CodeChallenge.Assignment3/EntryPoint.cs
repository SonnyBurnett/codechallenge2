
using GEP.CodeChallenge.Assignment2.Classes;
using GEP.CodeChallenge.Assignment2.Logger;
using GEP.CodeChallenge.Assignment3.Model;
using GEP.CodeChallenge.Assignment3.Interfaces;
using GEP.CodeChallenge.Constants;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.IO;
using GEP.CodeChallenge.Assignment3.Helper;
using GEP.CodeChallenge.Assignment3.Classes;

namespace GEP.CodeChallenge.Assignment3
{
    public class EntryPoint
    {
        public static void Execute(string inputPath, string outputPath)
        {
            ILogger logger = Logging.CreateLogger<EntryPoint>();
            IList<CountryShippingModel> outputModel = new List<CountryShippingModel>();
            var serviceProvider = new ServiceCollection()
           .AddSingleton<IFileInputProcessor<InputModel>>(x => new FileInputProcessor<InputModel>())
           .AddSingleton<IFileOutputProcessor<List<CountryShippingModel>>>(x => new GenerateOutput<List<CountryShippingModel>>())
           .BuildServiceProvider();

            var fileHandler = serviceProvider.GetService<IFileInputProcessor<InputModel>>();
            var outputGenerator = serviceProvider.GetService<GenerateOutput<IList<CountryShippingModel>>>();

            var inputData = fileHandler.ProcessFile(new StreamReader(AppConstants.FILE_INPUT_PATH));

            var model = MapCountryShippingModel.Map(inputData);
            foreach(var item in model)
            {
                StrategySelector selector = new StrategySelector(item);
                var shippingInfo = selector.ShippingStrategy.ShippingInformation(item);
                outputModel.Add(shippingInfo);
            }

        }
    }
}
