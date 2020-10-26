using System;
using System.Collections.Generic;
using Tw.Ing.Challenge2.Commands;

namespace Tw.Ing.Challenge2
{
    internal partial class BoardContext 
    {
        public class BoardStateFinished : Board
        {
            public BoardStateFinished(BoardContext parent) : base(parent)
            {
            }

            public override Board End()
            {
                throw new InvalidOperationException("Cannot finish an already finished board");
            }

            public override Board Initialize()
            {
                Parent.Matrix.Clear();
                for (var rowNo = 1; rowNo <= 3; rowNo++)
                {
                    for (var columnName = 'a'; columnName <= 'c'; columnName++)
                    {
                        var cellCoordinate = new Coordinate(columnName, rowNo);
                        var cell = new Cell(cellCoordinate);
                        Parent.Matrix.Add(cellCoordinate, cell);
                    }
                }
                return new BoardStatePlaying(Parent);
            }

            public override Board Play(Coordinate coordinate, Cell.Marker mark)
            {
                throw new InvalidOperationException("Play on a finished board");
            }

            public override IEnumerable<GameCommandBase> GetActionCommands()
            {
                var commandList = new List<GameCommandBase>();
                return commandList;
            }

            public override void Draw()
            {
                Console.SetCursorPosition(3, 2);
                Console.WriteLine("     ");
                Console.WriteLine("     ");
                Console.WriteLine("     ");
                Console.WriteLine("     ");
                Console.WriteLine("     ");
            }
        }
    }
}
