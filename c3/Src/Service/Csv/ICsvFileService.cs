using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge3.Model;

namespace Tw.Ing.Challenge3.Service
{
    public interface ICsvFileService
    {
        Task<IEnumerable<CsvOrderLine>> DownloadCsv(Uri csvFile);
    }
}
