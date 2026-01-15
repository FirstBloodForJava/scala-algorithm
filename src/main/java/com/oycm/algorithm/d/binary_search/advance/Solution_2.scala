package com.oycm.algorithm.d.binary_search.advance

object Solution_2 {

  /**
   * 1385. 两个数组间的距离值
   * https://leetcode.cn/problems/find-the-distance-value-between-two-arrays/description/
   *
   * 距离值：对于元素 arr1[i] ，不存在任何元素 arr2[j] 满足 |arr1[i]-arr2[j]| <= d
   *
   * @param arr1 整数数组
   * @param arr2 整数数组
   * @param d    整数
   * @return 求符合 距离值 要求的元素数目
   */
  def findTheDistanceValue(arr1: Array[Int], arr2: Array[Int], d: Int): Int = {
    /*
    对 arr2 进行排序，遍历 arr1 查找 arr2 是否存在 index 满足 a1 - a2 <= d 或 a1 - a2 >= -d
    a1 - d <= a2 或 a1 + d >= a2, a2 的数不在 [a1-d, a1+d] 访问
    */
    def lowerBound(nums: Array[Int], x: Int): Int = {
      var l = 0
      var r = nums.length - 1
      while (l <= r) {
        val mid = l + (r - l) / 2
        if (nums(mid) >= x) {
          r = mid - 1
        } else {
          l = mid + 1
        }
      }
      l
    }

    var ans = 0
    val sort = arr2.sorted

    for (a1 <- arr1) {
      /*
      查找 大于等于 a1 - d 的第一个 index
      如果不存在，或 sort(index) > a1 + d 记录答案
       */
      val index = lowerBound(sort, a1 - d)
      if (index == sort.length || sort(index) > a1 + d) {
        ans += 1
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(findTheDistanceValue(Array(4, 5, 8), Array(10, 9, 1, 8), 2))
    println(findTheDistanceValue(Array(1, 4, 2, 3), Array(-4, -3, 6, 10, 20, 30), 3))
    println(findTheDistanceValue(Array(2, 1, 100, 3), Array(-5, -2, 10, -3, 7), 6))
  }
}
