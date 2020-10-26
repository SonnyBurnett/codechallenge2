using System.Collections.Generic;
using Tw.Ing.Challenge2.Commands;
using Tw.Ing.Challenge2.Services;

namespace Tw.Ing.Challenge2
{
    internal partial class PlayerContext : ContextBase, IPlayerContext
    {
        private Player _state;
        private IGameEngineState EngineState { get => (IGameEngineState)_state; }

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

        public PlayerContext(IGameService gameService, IBoardContext board):base(gameService)
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

        public void MakeMove()
        {
            _state = _state.Move();
        }
        public void SelectColumn(char columnName)
        {
            _state = _state.SelectColumn(columnName);
        }
        public void SelectRow(int rowNumber)
        {
            _state = _state.SelectRow(rowNumber);
        }

        public override IEnumerable<GameCommandBase> GetActionCommands()
        {
            return EngineState.GetActionCommands();
        }

        public override void Draw()
        {
            if (Engine.IsDirty)
            {
                EngineState.Draw();
                Engine.IsDirty = false;
            }
        }
    }
}
