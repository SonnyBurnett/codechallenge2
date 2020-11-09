from datetime import datetime
from functools import lru_cache


max_nbr                 =   987654321
max_pan_prime           =   0
show_exec_time          =   False       # Set to true to time the execution
run_test                =   False       # Set to true to run test cases


@lru_cache( maxsize=5000000)
def isPrime( nbr ) :
    # Corner cases
    if nbr <= 1 :
        return False
    if nbr <= 3 :
        return True
    # This is checked so that we can skip
    # middle five numbers in below loop
    if nbr % 2 == 0 or nbr % 3 == 0 :
        return False
    i = 5
    while i * i <= nbr :
        if nbr % i == 0 or nbr % (i + 2) == 0 :
            return False
        i = i + 6
    return True


def isPanDigital( nbr ) :
    s                   =   str( nbr )
    length              =   len( s )
    result              =   True       # s.count( str( length ) ) == 1
    for i in range( 1, length + 1 ) :
        result          =   result and s.count( str( i ) ) == 1
    return result


def main() :
    global max_pan_prime
    max_pan_prime           =   -1
    divisor                 =   5
    step                    =   max_nbr // divisor
    for y in ( 0, divisor + 1 ) :
        for x in range( y * step , ( y + 1 ) * step  ) :
            if isPanDigital( x ) :
                if isPrime( x ) :
                    max_pan_prime   =   x
                    print( max_pan_prime )
                    if show_exec_time:
                        calDuration()
    print( "\nExecution program completed." )


def test( n, expected_nbr ) :
    global max_pan_prime
    max_pan_prime   =   -1
    s               =   ""
    for x in range( n, 0, -1 ) :
        s           += str( x )
    max_n           =   int( s )
    for x in range( 1, max_n   ) :
        if isPanDigital( x ) :
            if isPrime( x ) :
                max_pan_prime   =   x
                print( x )
                if show_exec_time:
                    calDuration()
    print( '- Expected output for max pandigital and prime: ', expected_nbr )
    print( '- Calculated output: ', max_pan_prime )
    print( "Successful completion of test"
           if max_pan_prime == expected_nbr
           else
           "Failed: Calculated output " + str( max_pan_prime ) +
           " not equal to expected output " + str( expected_nbr ) )


if show_exec_time :

    time_start              =   datetime.now()

    def calDuration() :
        time_end            =   datetime.now()
        print( "Execution time: ", time_end - time_start, "\n" )


def exec_tests() :

    test_values             =   (
        {
            "n" : 1,
            "expected_value" : -1,
            "comments" : "pandigital of 1 digit would result in " +
                         "the number 1, but 1 is not a prime, " +
                         "so result is set to -1,\n" +
                         "meaning no valid solution possible"
        },
        {
            "n" : 2,
            "expected_value" : -1,
            "comments" : "pandigital of 2 digits would result in " +
                         "the numbers 12 and 21, " +
                         "but both numbers are not primes," +
                         "\n so result is set -1, " +
                         "meaning no valid solution is possible."
        },
        {
            "n" : 4,
            "expected_value" : 4231,
            "comments" : "Maximal 4-digit pandigital prime is 4231"
        },
    )
    print( "\nTest results\n============" )
    test_nbr            =   0
    for t in test_values :
        test_nbr        +=  1
        print( "\nTest", test_nbr, ":\n", t[ "comments" ] )
        test( t[ "n" ], t[ "expected_value" ] )


if not run_test :
    main()
else :
    exec_tests()

if show_exec_time :
    calDuration()
