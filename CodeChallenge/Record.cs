using CsvHelper.Configuration.Attributes;
using System;
using System.Collections.Generic;
using System.Text;

namespace CodeChallenge
{
    public class Record
    {
        [Name("productId")]
        public int Id { get; set; }


        public string Name { get; set; }

        public string Description { get; set; }



    }
}
