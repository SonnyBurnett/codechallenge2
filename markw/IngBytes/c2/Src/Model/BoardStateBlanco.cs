using System;
using System.Collections.Generic;
using Tw.Ing.Challenge2.Commands;

namespace Tw.Ing.Challenge2
{
    internal partial class BoardContext
    {
        protected class BoardStateBlanco : Board
        {
            public BoardStateBlanco(BoardContext parent) : base(parent)
            {
            }

            public override Board End()
            {
                throw new InvalidOperationException("Cannot finish a Blanco board");
            }

            public override Board Initialize()
            {
                for (var rowNo = 1; rowNo <= 3; rowNo++)
                {
                    for (var columnName = 'a'; columnName <= 'c'; columnName++)
                    {
                        var coordinate = new Coordinate(columnName, rowNo);
                        var cell = new Cell(coordinate);
                        Parent.Matrix.Add(coordinate, cell);
                    }
                }

                return new BoardStatePlaying(Parent);
            }

            public override Board Play(Coordinate coordinate, Cell.Marker mark)
            {
                throw new InvalidOperationException("Cannot play on a Blanco board. Initialize before playing");
            }

            public override IEnumerable<GameCommandBase> GetActionCommands()
            {
                var commandList = new List<GameCommandBase>();
                return commandList;
            }

            public override void Draw()
            {
                Console.SetCursorPosition(3,2);
                Console.Write("  Tic");
                Console.SetCursorPosition(3, 3);
                Console.Write("- Tac - ");
                Console.SetCursorPosition(3, 4);
                Console.Write("  Toe");
            }
        }
    }
}
