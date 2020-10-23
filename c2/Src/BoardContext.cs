using System;
using System.Collections.Generic;
using System.Windows.Input;


namespace Tw.Ing.Challenge2
{
    internal partial class BoardContext: IBoardContext
    {
        public Board _state;
        public Dictionary<Coordinate,Cell> Matrix { get; } = new Dictionary<Coordinate, Cell>();

        public bool IsInitialized
        {
            get
            {
                return (_state.GetType() == typeof(BoardStatePlaying));
            }
        }
        public BoardContext()
        {
            _state = (Board)new BoardStateBlanco(this);
        }

        public void Initialize()
        {
            _state = _state.Initialize();
        }

        public Cell Draw(Coordinate coordinate, Cell.Marker mark)
        {
            _state = _state.Draw(coordinate, mark);
            return Matrix[coordinate];
        }

        public void End()
        {
            _state = _state.End();
        }
    }
}
