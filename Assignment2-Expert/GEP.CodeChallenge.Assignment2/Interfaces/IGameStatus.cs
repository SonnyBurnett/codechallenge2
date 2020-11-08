using GEP.CodeChallenge.Assignment2.AbstractClasses;

namespace GEP.CodeChallenge.Assignment2.Interfaces
{
    public interface IGameStatus
    {
        GameModel Evaluate(Game game);
    }
}
