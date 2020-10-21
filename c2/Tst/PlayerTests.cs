using System;
using System.Collections.Generic;
using System.Text;
using Xunit;

namespace Tw.Ing.Challenge2.Tests
{
    public class PlayerTests
    {
        [Fact]
        public void Player_State_NewToActive_Success()
        {
            // ARRANGE
            var player = new PlayerContext();

            // ACT
            player.Register("A", Cell.Marker.Circle);

            // ASSESS
            Assert.Equal("A", player.Name);
            Assert.Equal(Cell.Marker.Circle, player.Mark);
            Assert.False(player.IsPlaying);
        }

        [Fact]
        public void Player_State_ActiveToAtHand_Success()
        {
            // ARRANGE
            var player = new PlayerContext();
            player.Register("A", Cell.Marker.Circle);

            // ACT
            player.GiveTurn();

            // ASSESS
            Assert.True(player.IsPlaying);
        }

        [Fact]
        public void Player_State_AtHandToActive_Success()
        {
            // ARRANGE
            var player = new PlayerContext();
            player.Register("A", Cell.Marker.Circle);
            player.GiveTurn();
            var cell = new Cell('A', 1);
            // ACT
            player.MakeMove(cell);

            // ASSESS
            Assert.False(player.IsPlaying);
        }

        [Fact]
        public void Player_State_AtHandToWin_Success()
        {
            // ARRANGE
            var player = new PlayerContext();
            player.Register("A", Cell.Marker.Circle);
            player.GiveTurn();
            var cell1 = new Cell('A', 1);
            player.MakeMove(cell1);
            player.GiveTurn();
            var cell2 = new Cell('B', 1);
            player.MakeMove(cell2);
            player.GiveTurn();
            var cell3 = new Cell('C', 1);

            // ACT
            player.MakeMove(cell3);

            // ASSESS
            Assert.True(player.HasWon);
        }

        [Fact]
        public void Player_State_AtHandToDraw_Success()
        {
            // ARRANGE
            var player = new PlayerContext();
            player.Register("A", Cell.Marker.Circle);
            player.GiveTurn();
            var cell1 = new Cell('A', 1);
            player.MakeMove(cell1);
            player.GiveTurn();
            var cell2 = new Cell('B', 1);
            player.MakeMove(cell2);
            player.GiveTurn();
            var cell3 = new Cell('C', 1);
            player.MakeMove(cell3);
            player.GiveTurn();
            var cell4 = new Cell('A', 2);
            player.MakeMove(cell4);
            player.GiveTurn();
            var cell5 = new Cell('B', 2);
            player.MakeMove(cell4);
            player.GiveTurn();
            var cell6 = new Cell('C', 2);

            // ACT
            player.MakeMove(cell6);

            // ASSESS
            Assert.True(player.HasDraw);
        }
    }
}
