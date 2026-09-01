class Solution_1:
    def checkEqualPartitions(self, nums: List[int], target: int) -> bool:
        # 折半枚举，先判断乘积是否匹配
        if prod(nums) != target ** 2:
            return False
        m = len(nums) // 2
        set1 = self.calc(nums[:m], target)
        set2 = self.calc(nums[m:], target)
        return len(set1 & set2) > 0

    def calc(self, nums: List[int], target: int) -> Set[Tuple[int, int]]:
        st = set()

        def dfs(i: int, a: int, b: int) -> None:
            if a > target or b > target:
                return
            if i == len(nums):
                g = gcd(a, b)
                st.add((a // g, b // g))
                return
            dfs(i + 1, a * nums[i], b)
            dfs(i + 1, a, b * nums[i])

        dfs(0, 1, 1)
        return st
