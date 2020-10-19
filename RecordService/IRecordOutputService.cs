using System.Collections.Generic;
using System.Threading.Tasks;

namespace RecordService
{
    public interface IRecordOutputService
    {
        Task SetRecords(IEnumerable<Record> records);
    }
}