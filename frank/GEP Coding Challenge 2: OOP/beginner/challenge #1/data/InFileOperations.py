import os
from decimal import Decimal

from data.AreaException import AreaException
from domain.Circle import Circle
from domain.Square import Square


class InFileOperations:

    def __init__(self):
        path            =   os.getcwd()
        self.INFILE_PATH = path + "\\..\\data\\001-beginners.csv"
        self.inFile = None
        self.obj = {}
        self.form = None
        self.output   =   []

    def open_file(self):
        try:
            self.inFile = open(self.INFILE_PATH, "r")
        except IOError as ioe:
            raise AreaException("Unable to open file " + self.INFILE_PATH + "\n" + ioe )

    def process_data(self, line) :
        form        =   line.split()[ 0 ]
        value       =   Decimal( line.split()[ 1 ])
        self.obj = {}
        self.obj["form"] = form
        if form == "s":
            self.form = Square()
        elif form == "c":
            self.form = Circle()
        area = self.form.calc_area( value)
        self.obj["area"] = area
        self.output.append( self.obj)

    def read_file(self):
        try:
            self.open_file()
            for line in self.inFile:
                print(line)
                self.process_data( line)
        except IOError as ioe:
            raise AreaException( "Unable to read from file " + self.INFILE_PATH + "\n" + ioe )
        finally:
            try:
                self.inFile.close()
            except IOError:
                pass

    def get_output(self) :
        return self.output


run_test = False
if run_test:
    ifo = InFileOperations()
    ifo.read_file()
    output = ifo.get_output()
    for row in output:
        print( row)
