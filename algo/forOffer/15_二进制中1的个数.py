from typing import List


class Solution:
    def printNumbers_my(self, n: int) -> List[int]:
        if n is 0:
            return []
        res = []
        i = 10
        for x in range(n - 1):
            i *= 10
        for y in range(i - 1):
            res.append(y + 1)
        return res

    def printNumbers(self, n: int) -> List[int]:
        res = []
        for y in range(1, 10 ** n):
            res.append(y)
        return res


a = Solution()
print(a.printNumbers(0))
