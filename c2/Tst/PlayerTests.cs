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

            PlayCell(player, 'A', 1);
            PlayCell(player, 'B', 1);


            // ACT
            player.GiveTurn();
            var cell = new Cell('C', 1);
            player.MakeMove(cell);

            // ASSESS
            Assert.True(player.HasWon);
        }

        [Fact]
        public void Player_State_AtHandToDraw_Success()
        {
            // ARRANGE
            var player = new PlayerContext();
            player.Register("A", Cell.Marker.Circle);

            PlayCell(player, 'A', 1);
            PlayCell(player, 'B', 1);
            PlayCell(player, 'B', 2);
            PlayCell(player, 'C', 2);

            player.GiveTurn();
            var cell = new Cell('A', 3);

            // ACT
            player.MakeMove(cell);

            // ASSESS
            Assert.True(player.HasDraw);
        }

        [Fact]
        public void Player_InvalidStates_New()
        {
            //// ARRANGE
            var player = new PlayerContext();
            var cell1 = new Cell('A', 1);

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => player.GiveTurn());
            Assert.Throws<InvalidOperationException>(() => player.MakeMove(cell1));
        }

        [Fact]
        public void Player_InvalidStates_Active()
        {
            //// ARRANGE
            var player = new PlayerContext();
            player.Register("A", Cell.Marker.Circle);
            var cell1 = new Cell('A', 1);

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => player.Register("A", Cell.Marker.Circle));
            Assert.Throws<InvalidOperationException>(() => player.MakeMove(cell1));
        }

        [Fact]
        public void Player_InvalidStates_AtHand()
        {
            //// ARRANGE
            var player = new PlayerContext();
            player.Register("A", Cell.Marker.Circle);
            player.GiveTurn();

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => player.Register("A", Cell.Marker.Circle));
            Assert.Throws<InvalidOperationException>(() => player.GiveTurn());
        }


        [Fact]
        public void Player_InvalidStates_Win()
        {
            //// ARRANGE
            var player = new PlayerContext();
            player.Register("A", Cell.Marker.Circle);

            PlayCell(player, 'A', 1);
            PlayCell(player, 'B', 1);
            PlayCell(player, 'C', 1);

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => player.Register("A", Cell.Marker.Circle));
            Assert.Throws<InvalidOperationException>(() => player.GiveTurn());
            var cell = new Cell('A', 1);
            Assert.Throws<InvalidOperationException>(() => player.MakeMove(cell));
        }

        [Fact]
        public void Player_InvalidStates_Draw()
        {
            //// ARRANGE
            var player = new PlayerContext();
            player.Register("A", Cell.Marker.Circle);

            PlayCell(player, 'A', 1);
            PlayCell(player, 'B', 1);
            PlayCell(player, 'B', 2);
            PlayCell(player, 'C', 2);
            PlayCell(player, 'A', 3);

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => player.Register("A", Cell.Marker.Circle));
            Assert.Throws<InvalidOperationException>(() => player.GiveTurn());
            var cell = new Cell('A', 1);
            Assert.Throws<InvalidOperationException>(() => player.MakeMove(cell));
        }

        private void PlayCell(PlayerContext player, char columnName, int rowNumber)
        {
            player.GiveTurn();
            var cell = new Cell(columnName, rowNumber);
            player.MakeMove(cell);
        }
    }
}
