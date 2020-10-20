using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Input;

namespace Tw.Ing.Challenge2
{
    internal partial class BoardService: IBoardService, IGameServiceInterface
    {
        public IBoardServiceImplementation _currentState;
        public Board Board { get; } = new Board();


        public BoardService()
        {
            _currentState = (IBoardServiceImplementation)new BoardServiceImplementationBlanco(Board);
        }

        public void Initialize()
        {
            _currentState.Initialize();
            _currentState = (IBoardServiceImplementation)new BoardServiceImplementationDrawn(Board);
        }

        public void Play(char columnName, int rowNumber, Cell.Marker mark)
        {
            _currentState.Play(columnName, rowNumber, mark);
        }

        public void End()
        {
            _currentState.End();
            _currentState = (IBoardServiceImplementation)new BoardServiceImplementationFinished(Board);
        }

        IEnumerable<ICommand> IGameServiceInterface.AvailableCommands()
        {
            throw new NotImplementedException();
            //return ((IGameServiceInterface)CurrentState).AvailableCommands();
        }
    }
}
