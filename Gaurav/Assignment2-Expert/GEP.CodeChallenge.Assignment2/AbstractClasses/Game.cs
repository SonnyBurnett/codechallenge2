namespace GEP.CodeChallenge.Assignment2.AbstractClasses
{
    public abstract class Game
    {
        public string Name { get; }

        public int Dimensions { get; }

        public char[,] Board { get; set; }

        public Game(string gameName, int dimensions)
        {
            Name = gameName;
            Dimensions = dimensions;
        }

        public abstract void InitializeBoard();

    }
}
