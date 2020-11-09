from data.InFileOperations import InFileOperations
from data.OutFileOperations import OutFileOperations


class AreaMain:

    def __init__(self):
        self.infile     =   InFileOperations()
        self.outfile    =   OutFileOperations()
        self.output     =   None

    def process_data(self):
        self.infile.read_file()
        output          =   self.infile.get_output()
        self.outfile.write2file(output)


am = AreaMain()
am.process_data()