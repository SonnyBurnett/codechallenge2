using CsvHelper.Configuration.Attributes;

namespace CodeChallenge
{
    public class Record
    {
        [Name("productId")]
        public int Id { get; set; }

        [Name("name")]
        public string Name { get; set; }

        [Name("description")]
        public string Description { get; set; }

        [Name("price")]
        public decimal Price { get; set; }

        [Name("category")]
        public string Category { get; set; }
    }
}
