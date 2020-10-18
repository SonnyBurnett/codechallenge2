using GEP.CodeChallenge.Classes;
using GEP.CodeChallenge.Constants;
using GEP.CodeChallenge.Interfaces;
using GEP.CodeChallenge.Model;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using System.IO;

namespace GEP.CodeChallenge
{
    class EntryPoint
    {
        static void Main(string[] args)
        {
            var serviceProvider = new ServiceCollection()
           .AddSingleton<IDataFilter<ProductEntity>>(x => new PriceFilter<ProductEntity>(AppConstants.PRICE_THRESHOLD))
           .AddSingleton<IFileProcessor<ProductEntity, IList<ProductEntity>>>(x => new ProcessCsv<ProductEntity, IList<ProductEntity>>())
           .BuildServiceProvider();

            var fileHandler = serviceProvider.GetService<IFileProcessor<ProductEntity, IList<ProductEntity>>>();
            var priceFilterHandler = serviceProvider.GetService<IDataFilter<ProductEntity>>();

            Console.WriteLine(AppConstants.MESSAGE_READ_INPUT);
            var intialProdcuts = fileHandler.ProcessFile(new StreamReader(AppConstants.CSV_INPUT_PATH));

            Console.WriteLine(AppConstants.MESSAGE_PROCESSING_INPUT);
            var filteredProducts = priceFilterHandler.Operation(intialProdcuts);

            
            fileHandler.OutputFile(filteredProducts, AppConstants.OUTPUT_FILE_PATH);
            Console.WriteLine(AppConstants.MESSAGE_OUTPUT_GENERATED);
        }
    }
}
