using System.Collections.Generic;

namespace CodeChallenge
{
    public interface IRecordOutputService
    {
        void SetRecords(IEnumerable<Record> records);
    }
}