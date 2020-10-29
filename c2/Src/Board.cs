using System.Collections.Generic;
using Tw.Ing.Challenge2.Commands;

namespace Tw.Ing.Challenge2
{
    internal partial class BoardContext
    {
        public abstract class Board: ITweContextState
        {
            public BoardContext Parent { get; private set; }

            public Board(BoardContext parent)
            {
                Parent = parent;
            }
            public abstract Board End();
            public abstract Board Initialize();
            public abstract Board Play(Coordinate coordinate, Cell.Marker mark);
            public abstract IEnumerable<GameCommandBase> GetActionCommands();
            public abstract void Draw();
        }
    }
}
