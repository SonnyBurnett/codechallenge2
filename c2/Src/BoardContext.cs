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


        public BoardContext()
        {
            _state = (Board)new BoardStateBlanco(Matrix);
        }

        public void Initialize()
        {
            _state.Initialize();
            _state = (Board)new BoardStatePlaying(Matrix);
        }

        public void Play(char columnName, int rowNumber, Cell.Marker mark)
        {
            _state.Play(columnName, rowNumber, mark);
        }

        public void End()
        {
            _state.End();
            _state = (Board)new BoardStateFinished(Matrix);
        }

        IEnumerable<ICommand> IGameServiceInterface.AvailableCommands()
        {
            throw new NotImplementedException();
            //return ((IGameServiceInterface)CurrentState).AvailableCommands();
        }
    }
}
