from abc import ABC, abstractmethod


class IArea(ABC):

    def __init__(self):
        super().__init__()

    @abstractmethod
    def calc_area(self, value):
        pass
