using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge2
{
    interface IPlayerContext
    {
        public string Name { get; set; }
        public bool IsPlaying { get; }
        public bool HasWon { get; }
        public Cell.Marker Mark { get; set; }

        public void Register(string name, Cell.Marker mark);
        public void GiveTurn();
        public void MakeMove(Cell cellToPlay);
    }
}
