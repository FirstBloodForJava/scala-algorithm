package com.oycm.algorithm.f.basic

object Solution_4 {

  /**
   * 3152. 特殊数组 II 1523
   * https://leetcode.cn/problems/special-array-ii/
   *
   * 特殊数组：每一对相邻元素都是两个奇偶性不同的数字
   *
   * @param nums    待查询数组
   * @param queries [i][0, 1] 的二维数组
   * @return queries 数组对 nums [l, r] 进行查找，判断该数组是否为 特殊数组
   */
  def isArraySpecial(nums: Array[Int], queries: Array[Array[Int]]): Array[Boolean] = {
    /*
    本质可以把 nums 看作只有 0 和 1 的 数组
    题解思路：如何快速知道 是否 有奇偶性相同的相邻元素？
    [4,3,1,6] 关注数组的 , 左右的奇偶性是否相同，相同记为 1，否则记为 0，如果一段子数组出现了 1，就表示不是特殊数组
    这样就得到了新的长为 n-1 的数组，且元素只有 0 和 1的，可以通过前缀和快速查找一段子数组是否存在 1
    a(0) 表示 nums(0) 和 nums(1) 奇偶性关系
    a(1) 表示 nums(1) 和 nums(2) 奇偶性关系
    a(n-2) 表示 nums(n-2) 和 nums(n-1) 奇偶性关系

    [queries(0), queries(1)] 表示新的数组 a [queries(0), queries(1) - 1] 子数组和情况
    a [queries(0), queries(1) - 1] 的前缀和就是 sums(queries(1)) - sums(queries(0))

     */
    val sums = Array.fill(nums.length)(0)
    for (i <- 1 until nums.length) {
      // 位运算判断是否
      // nums(i - 1) ^ nums(i) 奇偶性不同，最低位为 1， 1 ^ 1 = 0
      // nums(i - 1) ^ nums(i) 奇偶性相同，最低位为 0， 0 ^ 1 = 1
      sums(i) = sums(i - 1) + ((nums(i - 1) ^ nums(i) ^ 1) & 1
        )
    }

    val ans = Array.fill(queries.length)(false)
    for (i <- queries.indices if sums(queries(i)(1)) - sums(queries(i)(0)) == 0) {
      ans(i) = true
    }

    ans
  }


  def main(args: Array[String]): Unit = {
    println(isArraySpecial(Array(3, 4, 1, 2, 6), Array(Array(0, 4))).toSeq)
  }

}
