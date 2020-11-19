class Solution:
    def fib(self, n: int) -> int:
        content = [0, 1]
        now = 2
        if n < 2:
            return content[n]
        else:
            res = None
            while now < n + 1:
                res = content[now - 2] + content[now - 1]
                content.append(res)
                now += 1
            return res % 1000000007


a = Solution()

print(a.fib(11))
