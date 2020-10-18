using System.Collections.Generic;

namespace GEP.CodeChallenge.Interfaces
{
    public interface IDataFilter<T>
    {
        IList<T> Operation(IList<T> productList);
    }
    
}
