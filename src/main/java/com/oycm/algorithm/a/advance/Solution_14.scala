package com.oycm.algorithm.a.advance

object Solution_14 {

  /**
   * 2156. 查找给定哈希值的子串 2063
   * https://leetcode.cn/problems/find-substring-with-given-hash-value/description/
   *
   * 长为 k 且下标从 0 开始的字符串 哈希值 按照以下函数计算：
   * hash(s, p, m) = (val(s(0)) * p0 + val(s(1)) * p1 + val(s(k-1)) * p(k-1)) % m
   *
   * @param s         小写字母字符串, 长度 [1, 2 * 10^4]
   * @param power     正整数 [1, 10^9]
   * @param modulo    正整数 [1, 10^9]
   * @param k         [1, s.length]
   * @param hashValue 目标哈希值
   * @return
   */
  def subStrHash(s: String, power: Int, modulo: Int, k: Int, hashValue: Int): String = {
    /*
    一个 hash 的计算过程可能会超 Int 类型的阈值，怎么快速计算长为 k 连续字符串的 hash 值是解决问题的关键

    暴力计算过程 (n-k) k, 计算每个子串的 hash, 可能会超时
    [0, k) (s0 + s1 * p + s2 * p2 + ... + s(k-1) * p(k-1)) % m
    [1, k+1) (s1 + s2 * p1 + s3 * p2 + ... + sk * p(k-1)) % m

    窗口形成时，判断 hash 是否符合条件，移除 s0，重置s(1,k) 为下一个窗口计算做准备

    正序不太好算
    题解：倒序滑动窗口计算 + 秦九韶算法计算多项式的和
    s(n-1) p(k-1) + s(n-2) p(k-2) + ... + s(n-k+1) p + s(n-k)
    由于 (a + b) % m = a % m + b % m = c; a % m = c - b % m
    由于 c - b % m 可能小于 0， a % m = (c - b % m + m) % m

    秦九韶算法，多项式拆分计算
    s0 + s1 * p + s2 * p2 + ... + s(k-1) * p(k-1)
    => s0 + p * (s1 + s2 p1 + ... + s(k-1) * p(k-2) )
    => s0 + p * (s1 + p * (s2 + s3 p1 + s(k-1) * p(k-3) ))
    ...
    => s0 + p * (s1 + p * (s2 + p * (s3 + p * s4 + ... ) + p * (s(k-2) + s(k-1) p) ...)))

    位运算计算 字母的位置 'a' = 1100001, 'z' = 1111010 可以与 11111 进行与运算得到字母的下标顺序
     */
    val n = s.length
    // [n-k, n) 的 hash
    var hash = 0L
    // p pow k
    var pk = 1L
    for (i <- n - 1 to n - k by -1) {
      hash = (hash * power + (s(i) & 31)) % modulo
      pk = pk * power % modulo
    }
    var ans = if (hash == hashValue) n - k else 0
    // 倒序滑动
    for (i <- n - k - 1 to 0 by -1) {
      // 滑动计算下一个 hash 值 (hash * p - (s(i+k) & 31) * pk % mod + modulo + s(i) & 31 ) % modulo
      // 取模减法避免出现负数 + mod 再取模
      hash = ((s(i) & 31) + hash * power - (s(i + k) & 31) * pk % modulo + modulo) % modulo
      if (hash == hashValue) {
        ans = i
      }
    }

    s.substring(ans, ans + k)
  }

  def main(args: Array[String]): Unit = {
    println(subStrHash("leetcode", 7, 20, 2, 0))
  }

}
