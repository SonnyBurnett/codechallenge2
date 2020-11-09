from abc import ABC, abstractmethod


class ITest(ABC):

    def __init__(self):
        super().__init__()

    @abstractmethod
    def test(self):
        pass
