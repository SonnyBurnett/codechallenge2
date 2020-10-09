using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge.Commands;

namespace Tw.Ing.Challenge.Services
{
    public interface ICsvService
    {
        Task<IEnumerable<Product>> DownloadCsv(Uri csvFile);
        void SaveCsv(IEnumerable<Product> productList, TextWriter textWriter);
    }
}
