using System.Collections.Generic;

namespace Tw.Ing.Challenge2
{
    interface IBoardContext
    {
        public bool IsInitialized { get; }
        Dictionary<Coordinate, Cell> Matrix { get; }
        void End();
        void Initialize();
        Cell Play(Coordinate coordinate, Cell.Marker mark);
    }
}
