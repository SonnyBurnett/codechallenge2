import main as ch1
import unittest
from math import pi


class TestShapes(unittest.TestCase):
    def test_circle_area(self):
        circle = ch1.ShapeCircle(1)
        result = circle.get_area()
        self.assertEqual(pi, result)

    def test_square_area(self):
        square = ch1.ShapeSquare(4)
        result = square.get_area()
        self.assertEqual(16, result)

    def test_shape_area(self):
        processor = ch1.ShapeProcessor()
        shape1 = ["c", 2] #diameter of 2, so radius of 1
        shape2 = ["s", 9]
        result1 = processor.get_shape_area(shape1[0], shape1[1])
        result2 = processor.get_shape_area(shape2[0], shape2[1])
        self.assertEqual(pi, result1)
        self.assertEqual(81, result2)

    def test_create_output_file(self):
        processor = ch1.ShapeProcessor()
        input_file = r"001-beginners.csv"
        output_file = r"001-beginners_output.csv"
        processor.create_output_file(input_file, output_file)
        with open(r"001-beginners_output.csv") as result_file:
            result_string = [line for line in result_file]
        self.assertEqual(['c 78.53981633974483\n', 's 25\n', 's 64\n'], result_string)

if __name__ == "__main__":
    unittest.main()
