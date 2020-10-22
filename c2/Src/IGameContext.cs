namespace Tw.Ing.Challenge2
{
    interface IGameContext
    {
        public PlayerContext PlayerCircle { get; set; }
        public PlayerContext PlayerCross { get; set; }
        public BoardContext Board { get; set; }
        public bool IsActive { get;  }
        public bool HasEnded { get; }

        public void Start(PlayerContext playerCircle, PlayerContext playerCross, BoardContext board);
        public void Quit();
        public void End();
    }
}
