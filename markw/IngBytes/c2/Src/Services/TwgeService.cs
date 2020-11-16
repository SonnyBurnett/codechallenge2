using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Tw.Ing.Challenge2.Commands;

namespace Tw.Ing.Challenge2.Services
{
    internal class TwgeService:ITwgeService
    {
        public TwgeService()
        {
        }

        public bool CanContinue { get; private set; } = true;
        public List<ITweContext> GameObjects { get; } = new List<ITweContext>();
        public List<GameCommandBase> CommandList { get; } = new List<GameCommandBase>();
        public string ErrorMessage { get; private set; }
        public void RegisterGameObject(ITweContext theObject)
        {
            GameObjects.Add(theObject);
        }

        public void UnRegisterGameObject(ITweContext theObject)
        {
            GameObjects.Remove(theObject);
        }

        public void Quit()
        {
            CanContinue = false;
        }

        public void DoAction(IEnumerable<GameCommandBase> commandList)
        {
            if (commandList == null)
                throw new ArgumentNullException(nameof(commandList), "Need a command list");

            var autoCommands = commandList.Where(c => c.Key.Length == 0).ToList();
            foreach (var cmd in autoCommands)
            {
                cmd.Execute(char.MinValue);
            }

            while (Console.KeyAvailable)
            {
                var keyPressed = Console.ReadKey(true).KeyChar;
                bool keyFound = false;



                var keyCommands = commandList.Where(c => c.Key.Length > 0).ToList();
                foreach (var cmd in keyCommands)
                {
                    bool hasKeyMatch = cmd.Key.Where(k => k == keyPressed).Any();
                    if (hasKeyMatch)
                    {
                        try
                        {
                            cmd.Execute(keyPressed);
                        }
                        catch(InvalidOperationException iox)
                        {
                            ErrorMessage = iox.Message;
                        }
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
            for(var i =  0; i < 4; i++)
            {
                Console.SetCursorPosition(0, 13 + offset);
                Console.Write("                                            ");
                offset++;
            }

            if (String.IsNullOrWhiteSpace(ErrorMessage))
            {
                offset = 0;
                foreach (var cmd in CommandList)
                {
                    Console.SetCursorPosition(0, 14 + offset);
                    Console.Write(cmd.Title);
                    offset++;
                }
            }
            else
            {
                Console.Beep();
                Console.SetCursorPosition(0, 13);
                Console.Write(ErrorMessage);
                ErrorMessage = "";
            }
        }
    }
}
