using System.Collections.Generic;

namespace Tw.Ing.Challenge2
{
    internal partial class PlayerContext : IPlayerContext
    {
        private Player _state;

        protected IBoardContext Board {get;private set;}
        
        public string Name { get; set; }
        public bool IsPlaying
        {
            get
            {
                var stateType = _state.GetType();
                if (    (stateType == typeof(PlayerStateAtHand))
                     || (stateType == typeof(PlayerStateWin))
                     || (stateType == typeof(PlayerStateDraw))
                     )
                { 
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }

        public bool HasWon
        {
            get
            {
                return (_state.GetType() == typeof(PlayerStateWin));
            }
        }
        public bool HasDraw
        {
            get
            {
                return (_state.GetType() == typeof(PlayerStateDraw));
            }
        }

        public Cell.Marker Mark { get; set; }

        public List<Cell> Moves { get; set; } = new List<Cell>();

        public PlayerContext(IBoardContext board)
        {
            _state = new PlayerStateNew(this);
            Board = board;
        }

        public void Register(string name, Cell.Marker mark)
        {
            _state = _state.Register(name, mark);
        }

        public void GiveTurn()
        {
            _state = _state.Turn();
        }

        public void MakeMove(Coordinate coordinate)
        {
            _state = _state.Move(coordinate);
        }
    }
}
