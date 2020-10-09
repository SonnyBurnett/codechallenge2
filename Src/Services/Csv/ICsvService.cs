using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge.Commands;

namespace Tw.Ing.Challenge.Services
{
    public interface ICsvService
    {
        Task<IEnumerable<Product>> DownloadCsv(Uri csvFile);
        Task SaveCsv(List<Product> productList, Currency currency);
    }
}
