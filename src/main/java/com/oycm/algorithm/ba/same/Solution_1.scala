package com.oycm.algorithm.ba.same

object Solution_1 {

  /**
   * 611. 有效三角形的个数
   * https://leetcode.cn/problems/valid-triangle-number/
   *
   * @param nums 非负整数的数组
   * @return 求 nums 中可以组成三角形三条边的三元组个数
   */
  def triangleNumber(nums: Array[Int]): Int = {
    /*
    对于三角形的三条边 a, b, c 三个正数，满足以下条件，则可以构成三角形
    a + b > c
    a + c > b
    b + c > a
    任意两边之和大于第三边
    题解：明确计算规则 [2,2,3,4] 符合要求的结果是 [2,3,4], [2,3,4], [2,2,3]，答案并没有将 [4,3,2] 作为答案，相当于下标相同的三元组构成唯一的答案。
    不如按顺序排列三条边，满足 1 <= a <= b <= c，这样就不会将 [4,3,2] 记录到答案中，根据前面构成三角形的条件
    只需要考虑：1 <= a <= b <= c 且 a + b > c 的方案数
    */
    var ans = 0


    ans
  }

  def enumLongest(nums: Array[Int]): Int = {
    /*
    枚举最长边：
    c = k in [2, n-1], a = l = 1, b = r = k-1
    a + b > c, ans += r - l, b--; 否则 l++; l >= r 退出循环
    */
    val sort = nums.sorted
    var ans = 0
    for (k <- 2 until nums.length) {
      var l = 0
      var r = k - 1
      while (l < r) {
        if (sort(l) + sort(r) > sort(k)) {
          ans += r - l
          r -= 1
        } else {
          l += 1
        }
      }
    }

    ans
  }

  def enumLongestOptimize(nums: Array[Int]): Int = {
    /*
    C(n, r) = n! / (r!)(n-r)!
    枚举最长边-优化：
    从 n-1 开始枚举：
    如果 nums(0) + nums(1) > nums(k)，则 [0, k] 里面的所有三元组合都符合答案，可以直接退出外层循环，答案是 (k+1)*k*(k-1) / 6
    如果 num(k-2) + nums(k-1) <= nums(k) 跳过内层循环
    */
    val sort = nums.sorted
    var ans = 0
    var flag = true
    for (k <- nums.length - 1 to 2 by -1 if flag) {
      if (sort(0) + sort(1) > sort(k)) {
        flag = false
        ans += k * (k + 1) * (k - 1) / 6
      } else if (sort(k - 2) + sort(k - 1) > sort(k)) {
        var l = 0
        var r = k - 1
        while (l < r) {
          if (sort(l) + sort(r) > sort(k)) {
            ans += r - l
            r -= 1
          } else {
            l += 1
          }
        }
      }

    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(enumLongestOptimize(Array(2, 2, 3, 4)))
  }
}
