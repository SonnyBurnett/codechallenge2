using System.Collections.Generic;

namespace CodeChallenge
{
    public interface IRecordInputService
    {
        IEnumerable<Record> GetRecords();
    }
}