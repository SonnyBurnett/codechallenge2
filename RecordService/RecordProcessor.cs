using System;
using System.Collections.Generic;
using System.Text;

namespace CodeChallenge
{
    public class RecordProcessor
    {
        private readonly IRecordInputService inputService;
        private readonly IRecordOutputService outputService;

        public RecordProcessor(IRecordInputService inputService, IRecordOutputService outputService)
        {
            this.inputService = inputService;
            this.outputService = outputService;
        }


    }
}
