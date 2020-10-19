import abc
from math import pi


class IArea(abc.ABC):
    @abc.abstractmethod
    def get_area(self):
        pass


class ShapeSquare(IArea):
    def __init__(self, width):
        self.__width = width
        self.__area = self.__width ** 2
    
    def get_area(self):
        area = self.__area
        return area


class ShapeCircle(IArea):
    def __init__(self, radius):
        self.__radius = radius
        self.__area = pi * ( self.__radius / 2 ) ** 2
    
    def get_area(self):
        area = self.__area
        return area


class ShapeProcessor:
    def create_output_file(self, input_file, output_file):
        input_file = open(input_file, "r")
        output_file = open(output_file, "w")
        output_string = ""
        for line in input_file:
            data_item = list(line.strip().split(" "))
            if data_item[0] == "c":
                shape = ShapeCircle(int(data_item[1]))
            if data_item[0] == "s":
                shape = ShapeSquare(int(data_item[1]))
            shape_area = shape.get_area()
            output_string= "{}{} {}\n".format(output_string, data_item[0], str(shape_area))
        input_file.close()
        output_file.write(output_string)
        output_file.close()
                    

input_file =r"001-beginners.csv"
output_file =r"001-beginners_output.csv"
processor = ShapeProcessor()
processor.create_output_file(input_file, output_file)
