from math import pow
from domain.IArea import IArea


class Square( IArea ) :

    def calc_area( self, value ) :
        return pow( value, 2 )


# sq = Square()
# print( sq.calc_area( 4))

