namespace GEP.CodeChallenge.Assignment3.Interfaces
{
    public interface IFileOutputProcessor<K>
    {
        void OutputFile(K output, string filePath);
    }
}
