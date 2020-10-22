namespace Tw.Ing.Challenge2
{
    internal partial class GameContext : IGameContext
    {
        private Game _state;
        
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

        public GameContext()
        {
            _state = new GameStateNew(this);
        }

        public void Start(PlayerContext p1, PlayerContext p2, BoardContext board)
        {
            _state = _state.Start(p1, p2, board);
        }

        public void Quit()
        {
            _state = _state.Quit();
        }

        public void End()
        {
            _state = _state.End();
        }
    }
}
