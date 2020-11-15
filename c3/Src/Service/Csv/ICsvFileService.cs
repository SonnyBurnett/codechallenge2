using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace Tw.Ing.Challenge3.Service
{
    public interface ICsvFileService
    {
        Task<IEnumerable<OrderLine>> DownloadCsv(Uri csvFile);
    }
}
