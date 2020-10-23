using System;
using System.Collections;
using System.Collections.Generic;
using Tw.Ing.Challenge2.Commands;
using Tw.Ing.Challenge2.Services;

namespace Tw.Ing.Challenge2
{
    internal partial class GameContext : ContextBase,IGameContext
    {
        private Game _state;
        protected Game State { get => _state; set => _state = value; }
        protected IGameEngineState EngineGameState { get => (IGameEngineState)_state; }

        public PlayerContext PlayerCircle{ get; set; }
        public PlayerContext PlayerCross { get; set; }
        public BoardContext Board { get; set; }

        public bool IsActive
        {
            get
            {
                var stateType = State.GetType();
                if (stateType == typeof(GameStateActive))
                { 
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }

        public bool HasEnded
        {
            get
            {
                return (State.GetType() == typeof(GameStateFinished));
            }
        }

        public PlayerContext ActivePlayer 
        { 
            get
            {
                if (PlayerCircle.IsPlaying)
                    return PlayerCircle;
                else if (PlayerCross.IsPlaying)
                    return PlayerCross;
                else
                    return null;
            } 
        }

        public GameContext(IGameService gameService):base(gameService)
        {
            State = new GameStateNew(this);
        }

        public void Start(PlayerContext p1, PlayerContext p2, BoardContext board)
        {
            _state = _state.Start(p1, p2, board);
            Engine.IsDirty = true;
        }

        public void Quit()
        {
            State = State.Quit();
            Engine.IsDirty = true;
        }

        public void End()
        {
            State = State.End();
            Engine.IsDirty = true;
        }

        public override IEnumerable<GameCommandBase> GetActionCommands()
        {
            return EngineGameState.GetActionCommands();
        }

        public override void Draw()
        {
            if (Engine.IsDirty)
            {
                EngineGameState.Draw();
                Engine.IsDirty = false;
            }
        }
    }
}
