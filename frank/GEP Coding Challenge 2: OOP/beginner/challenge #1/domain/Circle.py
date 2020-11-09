from math import pow, pi
from domain.IArea import IArea


class Circle(IArea):

    def calc_area(self, value):
        return pi * pow(value/2, 2)


# c = Circle()
# for x in range( 10 ):
#    print( x, c.calc_area( x ) )
