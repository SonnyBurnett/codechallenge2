using System.Collections.Generic;

namespace RecordService
{
    public interface IRecordInputService
    {
        IEnumerable<Record> GetRecords();
    }
}