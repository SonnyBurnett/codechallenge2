using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Input;
using Tw.Ing.Challenge2.Commands;
using Tw.Ing.Challenge2.Services;

namespace Tw.Ing.Challenge2
{
    internal partial class BoardContext : ContextBase, IBoardContext
    {
        public Board _state;
        private ITweContextState EngineState { get => (ITweContextState)_state; }
        
        public Dictionary<Coordinate,Cell> Matrix { get; } = new Dictionary<Coordinate, Cell>();

        public bool IsInitialized
        {
            get
            {
                return (_state.GetType() == typeof(BoardStatePlaying));
            }
        }

        public bool NoMoreMoves
        {
            get
            {
                return !Matrix.Select(c => c.Value.Mark == Cell.Marker.Empty).Any();
            }
        }
        public BoardContext(ITwgeService gameService):base(gameService)
        {
            _state = (Board)new BoardStateBlanco(this);
        }

        public void Initialize()
        {
            _state = _state.Initialize();
            IsDirty = true;
        }

        public Cell Play(Coordinate coordinate, Cell.Marker mark)
        {
            _state = _state.Play(coordinate, mark);
            IsDirty = true;
            return Matrix[coordinate];
        }

        public void End()
        {
            _state = _state.End();
            IsDirty = true;
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
