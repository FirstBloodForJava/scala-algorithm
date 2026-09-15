class Solution_11:
    def maxPalindromes(self, s: str, k: int) -> int:
        # aba => ^#a#b#a#$
        # s[i] 和 t[j] 下标关系 2(i+1) = j
        t = '#'.join('^' + s + '$')
        # half_len[i] 表示 t 以 t[i] 为回文中心的最长回文半径
        # i 为偶数时，对应原 s 奇数长度回文串
        # i 为奇数时，对应原 s 偶数长度回文串
        half_len = [0] * (len(t) - 2)

        # box_r 表示当前右边界下标最大的回文子串的右边界下标+1
        # box_m 表示该回文子串的中心位置
        box_m = box_r = 0
        for i in range(2, len(half_len)):
            hl = 1
            if i < box_r:
                hl = min(half_len[2 * box_m - i], box_r - i)
            while t[i - hl] == t[i + hl]:
                hl += 1
                box_m, box_r = i, i + hl
            half_len[i] = hl

        # s[l, r) 是否为回文串
        def is_palindrome(l: int, r: int) -> bool:
            # l => 对应 t 中下标 2(l+1)
            # r-1 => 对应 t 中下标 2r
            # t 字符串回文中心下标 (2(l+1) + 2r) / 2 = l+r+1
            # half_len[i]-1 表示 s 以 j 为 中心回文字符串长度
            # i 为偶数，j = i/2 - 1，奇数长度回文串
            # i 为奇数, j = i/2-1, i/2 为中心，偶数长度回文串
            return half_len[l + r + 1] > r - l

        n = len(s)
        f = [0] * (n + 1)
        for i in range(k, n + 1):
            f[i] = f[i - 1]
            if is_palindrome(i - k, i):
                f[i] = max(f[i], f[i - k] + 1)
            if i > k and is_palindrome(i - k - 1, i):
                f[i] = max(f[i], f[i - k - 1] + 1)
        return f[n]
