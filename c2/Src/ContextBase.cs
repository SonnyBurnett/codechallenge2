using System;
using System.Collections;
using System.Collections.Generic;
using Tw.Ing.Challenge2.Commands;
using Tw.Ing.Challenge2.Services;

namespace Tw.Ing.Challenge2
{
    internal abstract class ContextBase : ITweContext
    {
        protected ITweContext Engine { get => (ITweContext)this; }
        protected ITwgeService Service { get; }

        public bool IsDirty { get; set; } = true;

        public ContextBase(ITwgeService gameService)
        {
            Service = gameService;
            gameService.RegisterGameObject(this);
        }

        public abstract IEnumerable<GameCommandBase> GetActionCommands();

        public abstract void Draw();
    }
}
