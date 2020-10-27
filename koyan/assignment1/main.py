import abc
from math import pi


class IArea(abc.ABC):
    @abc.abstractmethod
    def get_area(self):
        pass


class ShapeSquare(IArea):
    def __init__(self, width):
        self.__size = width
        self.__area = self.__size ** 2
    
    def get_area(self):
        area = self.__area
        return area


class ShapeCircle(IArea):
    def __init__(self, radius):
        self.__size = radius
        self.__area = pi * self.__size ** 2
    
    def get_area(self):
        area = self.__area
        return area


class ShapeProcessor:
    def get_shape_area(self, shape, size):
        if shape == "c":
            circle = ShapeCircle(size/2)
            shape_area = circle.get_area()
        if shape == "s":
            square = ShapeSquare(size)
            shape_area = square.get_area()
        return shape_area

    def create_output_file(self, input_file, output_file):
        input_file = open(input_file, "r")
        output_file = open(output_file, "w")
        output_string = ""
        for line in input_file:
            new_shape = list(line.strip().split(" "))
            shape_area = self.get_shape_area(new_shape[0], int(new_shape[1]))
            output_string= "{}{} {}\n".format(output_string, new_shape[0], str(shape_area))
        input_file.close()
        output_file.write(output_string)
        output_file.close()
                    

input_file =r"001-beginners.csv"
output_file =r"001-beginners_output.csv"
processor = ShapeProcessor()
processor.create_output_file(input_file, output_file)
   
