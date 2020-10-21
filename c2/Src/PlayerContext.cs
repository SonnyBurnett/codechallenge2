using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge2
{
    internal partial class PlayerContext : IPlayerContext
    {
        private Player _state;
        
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
        

        public PlayerContext()
        {
            _state = new PlayerStateNew(this);
        }

        public void Register(string name, Cell.Marker mark)
        {
            Name = name;
            Mark = mark;
            _state = new PlayerStateActive(this);
        }

        public void GiveTurn()
        {
            _state = new PlayerStateAtHand(this);
        }

        public void MakeMove(Cell cellToPlay)
        {
            _state.Move(cellToPlay);

            // TODO: do check if the player wins
            _state = new PlayerStateActive(this);
        }
    }
}
