# Project Object Oriented Programing
## Expert Assignment 1
### Assignment 1: Experts
1. Before you start download the file inputs.csv from https://www.henrybeen.nl/wpcontent/uploads/2020/10/001-experts-inputs.csv
2. Write a program that:
1. Reads all inputs.csv
2. Writes a file outputs.csv that:
1. Contains the same products as from products.csv
2. Leaving out all products with a price below $ 10
3. Writes out prices in euro’s, assuming a rate of $ 1 : € 0,85
Remember, the goal of the assignment: write an OO implementation that solves the problem,
but mostly shows off your OO skills. Do not focus only solving the problem.

### Assignment 1: Experts – example inputs and outputs
Input file:
```
productId, name, description, price, category
45848, shorts, short pants, 8, pants
4184688, trousers, trousers, 12, pants
848488, blue shirt, shirt, 88, shirts
```
Output file:
```
productId, name, description, price, category
4184688, trousers, trousers, 10.2, pants
848488, blue shirt, shirt, 74.8, shirts
```
## Brainstorm
Oke, this time is different to the previous challenges, I have done a lot on paper too much to actually put it all in
writing. But still I'll put some of those steps of reasoning in here and off course the final implemented way will be 
described.
1. First the decisions that I have made (most are to save time on the coding):
    * I'll put the solution flow of the exercise in the Expert01 main method, some could argue it does not belong 
   there. Depends on what the goal is, it this case it is about Object Orientated setup, so the main will do the 
   solutions control flow. So I'm not focusing on parsing from the arguments to influence the possible.
   If this assignment will be used in a follow-up assignment, then this can be implemented then.
    * No defencive programing for now I'll assume that usage of the code will be handled by experienced developers 
   and that the solution will be used on a contract like set-up ea all involved know what they are doing. This will
   make the code more readable for the Object Orientated part (the intention of the assignment).
2. Second the Setup of the Object:
    * As there are loads of ways to solve this I'll pick one that could be re-usable in case the assignment are building
    from the initial assignment or similar assignments. In order to do this I make an assumption that we actually do not 
    know the real set of properties/attributes/fields for a product, the solution for this is to have a class for the 
    that kind of fields/data object (I will call them properties from now on).
    * The lines in the cvs could be seen as list of products so each line is a product (that what I'm going to name them).
    The products will be defined in their properties as given on the line.
    * The whole file is actually a list of products, see it as the Ruler who runs his subjects.
    * To make segregation and have traceability I'll use an interface to make sure that both writing and loading 
    capabilities are part of the solution but not actually part of it. It does need to be implemented to interact with
    the file system, and are grouped in the Products.
    * Last and not least we need some currency calculation, let's do this the proper way and use the java object that 
    can handle the proper sensitivity on the decimal value (you can read this here [link](https://dzone.com/articles/never-use-float-and-double-for-monetary-calculatio))
    
 Here below I show a simplified diagram model on usages and depandancies etc. (note this is simplified and not a real
 UML compliant representation, due to ascii art):
```
    +-----------------------+       +-------------------------------+
    | Class | Products      |-------| Interface | ProductsHandler   |
    +-----------------------+       +-------------------------------+
                |
    +-----------------------+
    | Class |Product        |
    +-----------------------+
                |
    +-----------------------+       +-----------------------+
    | Class | ProductDetail |-------| Enumeration | Curency |        
    +-----------------------+       +-----------------------+
```