from datetime import datetime

#   Declare and initialize global variables
fib_euler                   =   4000000
show_exec_time              =   False           #   Set to true to time the execution
run_test                    =   True            #   Set to true to run test cases
if run_test :
    time_start              =   datetime.now()


def calc_fib_sum( fib_max ) :
    fib_1                   =   0
    fib_2                   =   1
    fib_tot                 =   0
    while fib_2 < fib_max :
        fib_sum             =   fib_1 + fib_2
        fib_1               =   fib_2
        fib_2               =   fib_sum
        if fib_2 % 2 == 0 :
            fib_tot         += fib_2
    return fib_tot


#   Run tests
#   ---------

if run_test :

    def test( fib_max, expected_nbr ) :
        s                       =   calc_fib_sum( fib_max )
        print( '- Expected output: ', expected_nbr )
        print( '- Calculated output: ', s )
        print( "Successful completion" if s == expected_nbr else
               "Failed: Calculated output " + str( s ) +
               " not equal to expected output " + str( expected_nbr ) )
        # assert s == expected_nbr, 'Result should be ' + str( expected_nbr )


    test_values             =   (
        {
            "max_value" : 5,
            "expected_value" : 2,
            "comments" : "term 2 ( fib_2) < 5 , fib numbers: 0 1 1 2 3, sum even numbers: 2"
        },
        {
            "max_value" : 10,
            "expected_value" : 10,
            "comments" : "term 2 ( fib_2) < 10 , fib numbers: 0 1 1 2 3 5 8, sum even numbers: 10 "
        },
        {
            "max_value" : 40,
            "expected_value" : 44,
            "comments" : "term 2 ( fib_3) < 40 , fib numbers: 0 1 1 2 3 5 8 13 21 34, sum even numbers: 44"
        },
    )

    print( "\nTest results" )

    test_nbr            =   0
    for t in test_values :
        test_nbr        +=  1
        print( "\nTest", test_nbr, ": ", t[ "comments" ] )
        test( t[ "max_value" ], t[ "expected_value" ] )


#   Solution Euler problem 2 result
x                       =   calc_fib_sum( fib_euler )
print( "\nSolution Euler problem 02:", x )
if x != 4613732:
    raise AssertionError("Answer Euler problem 2 should be 4613732")


if show_exec_time :
    time_end            =   datetime.now()
    print( "Execution time: ", time_end - time_start )
