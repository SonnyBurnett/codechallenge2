using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge.Services
{
    public class CsvServiceException: Exception
    {
        public CsvServiceException()
        {
        }        
        
        public CsvServiceException(string message) : base(message)
        {
        }

        public CsvServiceException(string message, Exception innerException) : base(message, innerException)
        {
        }
    }
}
