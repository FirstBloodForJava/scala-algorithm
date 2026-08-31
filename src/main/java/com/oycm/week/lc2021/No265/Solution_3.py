"""
2059. 转化数字的最小运算数
https://leetcode.cn/problems/minimum-operations-to-convert-number/description/

给你一个下标从 0 开始的整数数组 nums ，该数组由 互不相同 的数字组成。另给你两个整数 start 和 goal.
整数 x 的值最开始设为 start ，你打算执行一些运算使 x 转化为 goal 。你可以对数字 x 重复执行下述运算：
如果 0 <= x <= 1000 ，那么，对于数组中的任一下标 i（0 <= i < nums.length），可以将 x 设为下述任一值：
    x + nums[i]
    x - nums[i]
    x ^ nums[i]（按位异或 XOR）
注意，你可以按任意顺序使用每个 nums[i] 任意次。使 x 越过 0 <= x <= 1000 范围的运算同样可以生效，但该该运算执行后将不能执行其他运算。
返回将 x = start 转化为 goal 的最小操作数；如果无法完成转化，则返回 -1。
"""


class Solution_3:
    def minimumOperations(self, nums: List[int], start: int, goal: int) -> int:
        # 题解思路：把问题想象成一张图，一次运算对应图中的一条边，求 start 到 goal 的最短路径
        # 图中的点
        vis = [False] * 1001
        vis[start] = True
        q = [start]
        step = 1
        while q:
            temp = q
            q = []
            for cur in temp:
                for num in nums:
                    for x in (cur + num, cur - num, cur ^ num):
                        if x == goal:
                            return step
                        if 0 <= x <= 1000 and not vis[x]:
                            vis[x] = True
                            q.append(x)
            step += 1
        return -1