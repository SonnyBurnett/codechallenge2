﻿namespace Tw.Ing.Challenge2
{
    interface IPlayerContext
    {
        public string Name { get; set; }
        public bool IsPlaying { get; }
        public bool HasWon { get; }
        public Cell.Marker Mark { get; set; }

        public void Register(string name, Cell.Marker mark);
        public void GiveTurn();
        public void MakeMove();
        public void SelectColumn(char columnName);
        public void SelectRow(int rowNumber);
    }
}
