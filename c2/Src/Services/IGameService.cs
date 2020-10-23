using System;
using System.Collections.Generic;
using System.Text;
using Tw.Ing.Challenge2.Commands;

namespace Tw.Ing.Challenge2.Services
{
    internal interface IGameService
    {
        public bool CanContinue { get; set; }
        public List<IGameEngine> GameObjects { get; }
        public void RegisterGameObject(IGameEngine theObject);
        public IEnumerable<GameCommandBase> GetActionCommands();
        public void DoAction(IEnumerable<GameCommandBase> commandList);
        public void Draw();
    }
}
