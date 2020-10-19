using CodeChallenge1.Products;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace CodeChallenge1.Utils
{
    public class Files
    {
        private static List<string> readFile(string path)
        {
            List<string> lines = new List<string>();

            using (var reader = new StreamReader(path))
            {
                while (!reader.EndOfStream)
                {
                    var line = reader.ReadLine();
                    lines.Add(line);
                }
            }
            lines.RemoveAt(0);
            return lines;
        }

        public static List<Dictionary<string, string>> csvFileToProductList(string path)
        {
            List<string> lines = readFile(path);
            List<Dictionary<string, string>> listOfProducts = new List<Dictionary<string, string>>();
            var header = getHeaderSplit(path);
            foreach (var line in lines)
            {
                string[] properties = line.Split(',');

                Dictionary<string, string> product = new Dictionary<string, string>();
                for (int i = 0; i < header.Length; i++)
                {
                    product.Add(header[i], properties[i]);
                }

                listOfProducts.Add(product);
            }

            return listOfProducts;
        }

        public static void listToCSVFile(List<Dictionary<string, string>> listOfProducts, string pathInput, string pathOutput = "output.csv")
        {
            StringBuilder csv = new StringBuilder();
            var header = getHeaderSplit(pathInput);
            
            StringBuilder head = new StringBuilder();
            head.Append(header[0]);
            for (int i = 1; i < header.Length; i++)
            {
                head.Append(",");
                head.Append(header[i]);
            }
            csv.AppendLine(head.ToString());

            foreach (Dictionary<string, string> product in listOfProducts)
            {
                StringBuilder line = new StringBuilder();
                line.Append(product[header[0]]);
                for (int i = 1; i < header.Length; i++)
                {
                    line.Append(",");
                    line.Append(product[header[i]]);
                }
                csv.AppendLine(line.ToString());
            }
            File.WriteAllText(pathOutput, csv.ToString());
        }

        public static string[] getHeaderSplit(string path)
        {
            var header = getHeader(path).Split(',');
            for(int i =0;i<header.Length;i++)
            {
                header[i] = header[i].Trim();
            }
            return header;
        }

        static string getHeader(string path)
        {
            using (var reader = new StreamReader(path))
            {
                return reader.ReadLine();
            }
        }
    }
}
