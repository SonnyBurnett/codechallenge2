#   =============================================================================================================
#
#   Euler challenge 01
#   Frank G.M. Nijssen
#   ING – Tech GEP Ops Eng IT4IT / ServiceNow LCM
#   Euler ID: fgmni
#
#   -------------------------------------------------------------------------------------------------------------
#   Description
#   Problem 1: Multiples of 3 and 5
#   If we list all the natural numbers below 10 that are multiples of 3 or 5,
#   we get 3, 5, 6 and 9. The sum of these multiples is 23.
#   Find the sum of all the multiples of 3 or 5 below 1000.
#   -------------------------------------------------------------------------------------------------------------
#   Answer:     233168
#   Completed on Fri, 5 Jun 2020, 08:13
#   -------------------------------------------------------------------------------------------------------------
#
#   IDE:
#       IntelliJ IDEA 2020.1
#   Input:
#       -
#   Output:
#       C:\Users\fgmni\AppData\Local\Programs\Python\Python37-32\python.exe C:/Users/fgmni/IdeaProjects/Euler/euler01.py
#       233168
#       Process finished with exit code 0
#
#   =============================================================================================================


#   Start-of program
#   -------------------------------------------------------------------------------------------------------------

tot         =   0                               #   Declare global parameter tot and initialize it to 0

for nbr in range( 1, 1000 ) :                   #   For all numbers from 1 to 1000 ...
    if nbr % 3 == 0 or nbr % 5 == 0 :           #   ... validate if the number can be divided by 3 or by 5 ...
        tot +=  nbr                             #   ... and if it does, add it's value to that of parameter tot.
    #   End-of if-branch
#   End-of for-loop

print( tot )                                    #   Show the value of parameter tot

#   -------------------------------------------------------------------------------------------------------------
#   End-of program

