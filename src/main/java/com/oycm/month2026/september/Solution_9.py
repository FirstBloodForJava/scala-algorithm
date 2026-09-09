class Solution_9:
    def countCommas(self, n: int) -> int:
        ans, k = 0, 1000
        while n >= k:
            ans += n - k + 1
            k *= 1000
        return ans
