using System;
using System.Collections.Generic;
using System.Windows.Input;
using Tw.Ing.Challenge2.Commands;
using Tw.Ing.Challenge2.Services;

namespace Tw.Ing.Challenge2
{
    internal partial class BoardContext : ContextBase, IBoardContext
    {
        public Board _state;
        private IGameEngineState EngineState { get => (IGameEngineState)_state; }
        
        public Dictionary<Coordinate,Cell> Matrix { get; } = new Dictionary<Coordinate, Cell>();

        public bool IsInitialized
        {
            get
            {
                return (_state.GetType() == typeof(BoardStatePlaying));
            }
        }
        public BoardContext(IGameService gameService):base(gameService)
        {
            _state = (Board)new BoardStateBlanco(this);
        }

        public void Initialize()
        {
            _state = _state.Initialize();
        }

        public Cell Play(Coordinate coordinate, Cell.Marker mark)
        {
            _state = _state.Play(coordinate, mark);
            return Matrix[coordinate];
        }

        public void End()
        {
            _state = _state.End();
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
