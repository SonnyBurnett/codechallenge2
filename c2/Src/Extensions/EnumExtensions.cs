using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge2.Extensions
{
    public static class EnumExtensions
    {
        public static string ToBoardChar(this Cell.Marker mark)
        {
            return mark switch
            {
                Cell.Marker.Empty => " ",
                Cell.Marker.Circle => "O",
                Cell.Marker.Cross => "X",
                _ => throw new ArgumentOutOfRangeException(nameof(mark), $"Invalid enum value {mark}"),
            };
        }
    }
}
