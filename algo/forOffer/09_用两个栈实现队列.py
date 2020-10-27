class CQueue:
    li = []
    length = 0

    def __init__(self):
        self.li = []

    def appendTail(self, value: int) -> None:
        if value is not None:
            self.length += 1
            self.li.insert(0, value)

    def deleteHead(self) -> int:
        if self.length is not 0:
            res = self.li[self.length - 1]
            self.length -= 1
            self.li.pop()
            return res
        else:
            return -1

    # Your CQueue object will be instantiated and called as such:


# obj = CQueue()
# obj.appendTail(value)
# param_2 = obj.deleteHead()

queen = CQueue()

queen.appendTail([])
queen.deleteHead()
queen.deleteHead()
print(queen.li)
