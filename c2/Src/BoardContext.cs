using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Input;
using Tw.Ing.Challenge2.Plumbing;

namespace Tw.Ing.Challenge2
{
    internal partial class BoardContext: IBoardContext, IGameServiceInterface
    {
        public Board _state;
        public Dictionary<Pair<char,int>,Cell> Matrix { get; } = new Dictionary<Pair<char, int>, Cell>();

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

        public void Play(char columnName, int rowNumber, Cell.Marker mark)
        {
            _state = _state.Play(columnName, rowNumber, mark);
        }

        public void End()
        {
            _state = _state.End();
        }

        IEnumerable<ICommand> IGameServiceInterface.AvailableCommands()
        {
            throw new NotImplementedException();
            //return ((IGameServiceInterface)CurrentState).AvailableCommands();
        }
    }
}
