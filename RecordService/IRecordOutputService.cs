using System.Collections.Generic;
using System.Threading.Tasks;

namespace CodeChallenge
{
    public interface IRecordOutputService
    {
        Task SetRecords(IEnumerable<Record> records);
    }
}