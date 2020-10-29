namespace Tw.Ing.Challenge2
{
    interface IGameContext
    {
        public PlayerContext ActivePlayer { get; }
        public PlayerContext PlayerCircle { get; set; }
        public PlayerContext PlayerCross { get; set; }
        public BoardContext Board { get; set; }
        public bool IsActive { get;  }
        public bool HasEnded { get; }

        public void Start();
        public void End();
    }
}
