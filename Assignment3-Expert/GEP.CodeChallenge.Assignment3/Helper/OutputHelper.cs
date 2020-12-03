using GEP.CodeChallenge.Assignment2.Enums;
using GEP.CodeChallenge.Constants;

namespace GEP.CodeChallenge.Assignment2.Helper
{
    public class OutputHelper
    {
        public static string GenerateOutput(GameModel model)
        {
            string output = string.Empty;
            switch(model.CurrentStatus)
            {
                case CurrentStatus.Win:
                    output = string.Concat(AppConstants.WINNER, AppConstants.OUTPUT_SPACING, model.Player);
                    break;
                case CurrentStatus.NextMove:
                    output = string.Concat(AppConstants.NEXT_MOVE, AppConstants.OUTPUT_SPACING, model.Player);
                    break;
            }

            return output;
        }
    }
}
