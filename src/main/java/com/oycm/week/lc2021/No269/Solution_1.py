"""
2089. 找出数组排序后的目标下标
https://leetcode.cn/problems/find-target-indices-after-sorting-array/description/

给你一个下标从 0 开始的整数数组 nums 以及一个目标元素 target 。
目标下标 是一个满足 nums[i] == target 的下标 i 。
将 nums 按 非递减 顺序排序后，返回由 nums 中目标下标组成的列表。
如果不存在目标下标，返回一个 空 列表。返回的列表必须按 递增 顺序排列。
"""


class Solution:
    def targetIndices(self, nums: List[int], target: int) -> List[int]:
        # nums.sort()
        # ans = []
        # for i in range(len(nums)):
        #     if nums[i] == target:
        #         ans.append(i)
        # return ans
        less = 0
        equal = 0
        for x in nums:
            if x < target:
                less += 1
            elif x == target:
                equal += 1
        # range(start, end) 生成一个 [start, end) 序列，，list 将序列转换成 列表
        ans = list(range(less, less + equal))
        return ans
