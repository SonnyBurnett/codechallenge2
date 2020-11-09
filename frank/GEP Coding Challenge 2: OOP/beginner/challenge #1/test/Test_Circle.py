from decimal import Decimal

from domain.Circle import Circle
from test.ITest import ITest


class TestCircle(ITest):

    def __init__(self):
        self.circle = Circle()
        self.testdata = [
            { "size" : "0", "area" : "0"},
            { "size" : "1", "area" : "0.7853981633974483"},
            { "size" : "2", "area" : "3.141592653589793"},
            { "size" : "3", "area" : "7.0685834705770345"},
            { "size" : "4", "area" : "12.566370614359172"},
        ]

    def test(self):
        test_nbr            =   0;
        for row in self.testdata :
            result          =   "failed"
            test_nbr        +=  1
            rib_size        =   row["size"]
            area_size       =   row[ "area"]
            calc_area       =   self.circle.calc_area( Decimal(rib_size) )
            if area_size == str( calc_area) :
                result      =   "passed"
            else:
                result      =   "wrong"
            print( "\nTest: " + str( test_nbr)  + "\nRib size: " + str( rib_size ))
            print( "Calculated value of area: " + str( calc_area) + " - value should be: " + str(area_size ))
            print( "Test " + result )


tsq = TestCircle()
tsq.test();