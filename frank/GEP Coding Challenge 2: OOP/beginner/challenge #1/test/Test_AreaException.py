from abc import ABC
from data.AreaException import AreaException
from test.ITest import ITest


class TestAreaException(ITest, ABC):

    def __init__(self):
        self.message = None
        # Uncomment line below to test AreaException without message argument,
        # comment to test with message argument
        self.message = "Test for throwing an AreaException"

    def test(self):
        raise AreaException(self.message)


tae             =   TestAreaException()
tae.test()
