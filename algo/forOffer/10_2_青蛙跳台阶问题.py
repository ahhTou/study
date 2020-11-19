class Solution:
    def numWays(self, n: int) -> int:
        if n == 0:
            return 1
        now, a1, b = 1, 1, 1
        n = n - 1
        for i in range(n):
            a1 = a1 + b
            b = a1 - b
        return a1 % 1000000007


a = Solution()
print(a.numWays(10))
