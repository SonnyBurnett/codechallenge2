import os


class OutFileOperations:

    def __init__(self):
        path = os.getcwd()
        self.OUTFILE_PATH = path + "\\..\\data\\001-beginners_out.csv"
        self.outFile = None
        self.products = {}

    def open_file(self):
        try:
            self.outFile = open(self.OUTFILE_PATH, "w")
        except IOError as ioe:
            print("Unable to open file " + self.OUTFILE_PATH)
            print(ioe)

    def write2file(self, output):
        data            =   ""
        try:
            self.open_file()
            for obj in output:
                print( obj)
                line = obj["form"] + " " + str( obj["area"]) + "\n"
                data += line
            print( "Writing to file: " + data )
            self.outFile.write( data)
        except IOError as ioe:
            print("Unable to write to file " + self.OUTFILE_PATH)
            print(ioe)
        finally:
            try:
                self.outFile.close()
            except:
                pass


# ofo = OutFileOperations()
# ofo.write2File()
