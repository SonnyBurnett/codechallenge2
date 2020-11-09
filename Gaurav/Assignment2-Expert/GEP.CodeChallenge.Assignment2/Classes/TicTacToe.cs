using GEP.CodeChallenge.Assignment2.AbstractClasses;
using GEP.CodeChallenge.Assignment2.Interfaces;
using GEP.CodeChallenge.Constants;

namespace GEP.CodeChallenge.Assignment2.Classes
{
    public class TicTacToe : Game
    {
        private IFileInputProcessor _fileProcessor;
        private string _filePath;
        public TicTacToe(string name, string filePath) : base(name, AppConstants.TIC_TAC_TOE_DIMENSIONS)
        {
            _fileProcessor = new FileInputProcessor(AppConstants.TIC_TAC_TOE_DIMENSIONS);
            _filePath = filePath;

            InitializeBoard();
        }

        public override void InitializeBoard()
        {
            Board = _fileProcessor.ProcessFile(_filePath);
        }

    }
}
