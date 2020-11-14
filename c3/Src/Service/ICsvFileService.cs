using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace Tw.Ing.Challenge3.Service
{
    interface ICsvFileService
    {
        Task<IEnumerable<OrderLineCsv>> DownloadCsv(Uri csvFile);
    }
}
