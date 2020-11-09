from domain.Square import Square
from test.ITest import ITest


class TestSquare(ITest):

    def __init__(self):
        self.square = Square()
        self.testdata = [
            { "size" : "0", "area" : "0"},
            { "size" : "1", "area" : "1"},
            { "size" : "2", "area" : "4"},
            { "size" : "3", "area" : "9"},
            { "size" : "4", "area" : "16"}
        ]

    def test(self):
        test_nbr            =   0;
        for row in self.testdata :
            result          =   "wrong"
            test_nbr        +=  1
            rib_size        =   row["size"]
            area_size       =   row[ "area"]
            calc_area       =   self.square.calc_area( int(rib_size) )
            if int(area_size) == calc_area :
                result      =   "right"
            else:
                result      =   "wrong"
            print( "\n\nTest: " + str( test_nbr)  + "\nRib size: " + str( rib_size ))
            print( "Calculated value of area: " + str( calc_area) + " - value should be: " + str(area_size ))
            print( "")
            print( "Test " + result )


tsq = TestSquare()
tsq.test();