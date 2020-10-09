using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge.Commands
{
    [AttributeUsage(AttributeTargets.Property)]
    public class CsvFieldNameAttribute: Attribute
    {
        private string _fieldName;

        public CsvFieldNameAttribute(string fieldName)
        {
            _fieldName = fieldName;
        }

        public virtual string FieldName
        {
            get
            {
                return _fieldName;
            }
        }
    }
}
