'''
410. 分割数组的最大值

给定一个非负整数数组 nums 和一个整数 k ，你需要将这个数组分成 k 个非空的连续子数组，使得这 k 个子数组各自和的最大值 最小。
返回分割后最小的和的最大值。
子数组 是数组中连续的部分。
'''


class Solution_3:
    def splitArray(self, nums: List[int], k: int) -> int:
        '''
        dfs(i, k) 表示将长为 i 的子数组，分为 k 段，各自和的最大值 最小。
        dfs(i, k) = max(dfs(j, k-1), sum(j, i)) 取最小, j >= k - 1, j > 0
        递归边界 dfs(i, 0) 0 if i == 0 else inf
        '''

        @cache
        def dfs(i: int, k: int) -> int:
            res = 2 ** 31
            if k == 0:
                return 0 if i == 0 else res

            sum_ = 0
            for j in range(i - 1, k - 2, -1):
                sum_ += nums[j]
                res = min(res, max(dfs(j, k - 1), sum_))

            return res

        n = len(nums)
        return dfs(n, k)
