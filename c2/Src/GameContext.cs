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
        private IGameEngineState EngineState { get => (IGameEngineState)_state; }

        public PlayerContext PlayerCircle{ get; set; }
        public PlayerContext PlayerCross { get; set; }
        public BoardContext Board { get; set; }

        public bool IsActive
        {
            get
            {
                var stateType = _state.GetType();
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
                return (_state.GetType() == typeof(GameStateFinished));
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
            _state = new GameStateNew(this);
        }

        public void Start()
        {
            _state = _state.Start();
            Engine.IsDirty = true;
        }

        public void Quit()
        {
            _state = _state.Quit();
            Engine.IsDirty = true;
        }

        public void End()
        {
            _state = _state.End();
            Engine.IsDirty = true;
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
