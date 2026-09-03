'''
3876. 构造奇偶一致的数组 II

给你一个长度为 n 的数组 nums1，其中包含 互不相同 的整数。
你需要构造另一个长度为 n 的数组 nums2，使得 nums2 中的元素要么全部为 奇数，要么全部为 偶数。

对于每个下标 i，你必须从以下两种选择中 任选其一（顺序不限）：

nums2[i] = nums1[i]
nums2[i] = nums1[i] - nums1[j]，其中 j != i，且满足 nums1[i] - nums1[j] >= 1
如果能够构造出满足条件的数组，则返回 true；否则，返回 false。
'''


class Solution_3:
    def uniformArray(self, nums1: list[int]) -> bool:
        # 如果存在奇数或偶数，怎么判断是否能修改呢？
        # 变奇数：偶数 - 奇数（取最小值）
        # 变偶数：奇数 - 奇数，总会存在奇数，不成立
        # 奇偶存在时，只能变奇数
        even, odd = inf, inf
        for x in nums1:
            if x % 2 == 0:
                even = min(even, x)
            else:
                odd = min(odd, x)
        if even == inf or odd == inf:
            return True

        return even > odd
