class AreaException( Exception) :

    def __init__(self, message=None ):
        if message is None:
            super().__init__()
        else :
            self.message        =   message
            super().__init__(self.message)