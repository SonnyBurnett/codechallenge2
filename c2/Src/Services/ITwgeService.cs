using System;
using System.Collections.Generic;
using System.Text;
using Tw.Ing.Challenge2.Commands;

namespace Tw.Ing.Challenge2.Services
{
    internal interface ITwgeService
    {
        public bool CanContinue { get; }
        public List<ITweContext> GameObjects { get; }
        public void RegisterGameObject(ITweContext theObject);
        public void UnRegisterGameObject(ITweContext theObject);
        public IEnumerable<GameCommandBase> GetActionCommands();
        public void Quit();
        public void DoAction(IEnumerable<GameCommandBase> commandList);
        public void Draw();
    }
}
