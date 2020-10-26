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

        public bool CanContinue { get; private set; } = true;
        public List<IGameEngine> GameObjects { get; } = new List<IGameEngine>();
        public List<GameCommandBase> CommandList { get; } = new List<GameCommandBase>();
        public void RegisterGameObject(IGameEngine theObject)
        {
            GameObjects.Add(theObject);
        }

        public void Quit()
        {
            CanContinue = false;
        }

        public void DoAction(IEnumerable<GameCommandBase> commandList)
        {
            if (commandList == null)
                throw new ArgumentNullException(nameof(commandList), "Need a command list");

            while (Console.KeyAvailable)
            {
                var keyPressed = Console.ReadKey(true).KeyChar;
                bool keyFound = false;
                foreach (var cmd in commandList)
                {
                    if (cmd.Key == keyPressed)
                    {
                        cmd.Execute();
                        return;
                    }
                }

                if (!keyFound)
                {
                    Console.Beep();
                }
            }
        }

        public IEnumerable<GameCommandBase> GetActionCommands()
        {
            CommandList.Clear();
            foreach (var obj in GameObjects)
            {
                CommandList.AddRange(obj.GetActionCommands());
            }
            CommandList.Add(new QuitGameCommand(this));
            return CommandList;
        }

        public void Draw()
        {
            foreach (var obj in GameObjects)
            {
                obj.Draw();
            }

            int offset = 0;
            for(var i =  0; i < 3; i++)
            {
                Console.SetCursorPosition(0, 9 + offset);
                Console.Write("                              ");
                offset++;
            }
            offset = 0;
            foreach (var cmd in CommandList)
            {
                Console.SetCursorPosition(0, 9 + offset);
                Console.Write(cmd.Title);
                offset++;
            }

        }
    }
}
