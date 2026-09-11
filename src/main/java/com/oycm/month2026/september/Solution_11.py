'''
给你一个数字数组 digits，你需要从中选择三个数字组成一个三位偶数，你的任务是求出 不同 三位偶数的数量。
注意：每个数字在三位偶数中都只能使用 一次 ，并且 不能 有前导零。
'''
class Solution_11:
    def totalNumbers(self, digits: List[int]) -> int:
        # 所有数字出现次数统计
        cnt = Counter(digits)

        # 不同数字种类
        kinds = len(cnt)

        # 非零数字种类
        non_zeros = kinds - (0 in cnt)

        # 恰好出现一次的非零数字
        singles = sum(c == 1 for c in cnt.values()) - (cnt.get(0, 0) == 1)

        ans = 0

        for d, c in cnt.items():
            if d % 2 > 0:
                continue
            # 十位填任意数字
            k = kinds - (c == 1)

            # 百位填非零数字
            nz = non_zeros - (d > 0 and c == 1)

            # 恰好出现一次的非零数字，不能同时填入十位和百位
            '''
            0,2,3,4 * 2,3,4 = 4 * 3 非零数字互相选择时，是不合法的
            '''
            s = singles
            if d > 0:
                if c == 1:
                    # 前面已经减少过了，不会计算
                    s -= 1
                elif c == 2:
                    # 个位使用一次，后面只剩一次可用
                    s += 1
            ans += k * nz - s

        return ans


