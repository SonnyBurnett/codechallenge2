using System;
using System.Collections.Generic;
using System.Text;
using Tw.Ing.Challenge2.Commands;

namespace Tw.Ing.Challenge2.Services
{
    internal class GameService:IGameService
    {
        public GameService()
        {
        }

        public bool CanContinue { get; set; } = true;
        public List<IGameEngine> GameObjects { get; } = new List<IGameEngine>();
        public void RegisterGameObject(IGameEngine theObject)
        {
            GameObjects.Add(theObject);
        }


        public void DoAction(IEnumerable<GameCommandBase> commandList)
        {
            if (commandList == null)
                throw new ArgumentNullException(nameof(commandList), "Need a command list");

            if (Console.KeyAvailable)
            {
                foreach (var cmd in commandList)
                {
                    var keyPressed = Console.ReadKey(true).KeyChar;
                    if (cmd.Key == keyPressed)
                    {
                        cmd.Execute();
                        return;
                    }
                }
            }
        }

        public IEnumerable<GameCommandBase> GetActionCommands()
        {
            var list = new List<GameCommandBase>();
            foreach (var obj in GameObjects)
            {
                list.AddRange(obj.GetActionCommands());
            }
            return list;
        }

        public void Draw()
        {
            foreach (var obj in GameObjects)
            {
                obj.Draw();
            }
        }
    }
}
