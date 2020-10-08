using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace Tw.Ing.Challenge.Services
{
    public interface ICsvService
    {
        Task<Object> Load(Uri csvFile);
    }
}
