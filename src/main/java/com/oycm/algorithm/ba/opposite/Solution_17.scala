package com.oycm.algorithm.ba.opposite

object Solution_17 {

  /**
   * 1577. 数的平方等于两数乘积的方法数
   * https://leetcode.cn/problems/number-of-ways-where-square-of-number-is-equal-to-product-of-two-numbers/description
   *
   * 两个整数数组 nums1 和 nums2
   *
   * 根据以下规则形成的三元组的数目（类型 1 和类型 2 ）
   *  - 类型 1：三元组 (i, j, k) ，如果 nums1[i] pow 2 == nums2[j] * nums2[k] 其中 0 <= i < nums1.length 且 0 <= j < k < nums2.length
   *  - 类型 2：三元组 (i, j, k) ，如果 nums2[i] pow 2 == nums1[j] * nums1[k] 其中 0 <= i < nums2.length 且 0 <= j < k < nums1.length
   *
   * @param nums1 正整数数组
   * @param nums2 正整数数组
   * @return
   */
  def numTriplets(nums1: Array[Int], nums2: Array[Int]): Int = {
    /*
    哈希表计算：先用 map 记录 两个数组平方的数量记为 pow1 pow2
    再计算 两个数组各自乘积的数量，几位 p1 p2
    遍历 pow1 的额 key 取 p2 对于的 value 相乘

    时间复杂度 O(n^2) 计算乘积的次数
    空间复杂度 O(n^2)
    */
    def pow(nums: Array[Int]): scala.collection.mutable.Map[Long, Int] = {
      val map = scala.collection.mutable.Map[Long, Int]()
      for (num <- nums) {
        // 注意计算精度丢失问题
        val temp = Math.pow(num, 2).toLong
        map(temp) = map.getOrElse(temp, 0) + 1
      }
      map
    }

    def product(nums: Array[Int]): scala.collection.mutable.Map[Long, Int] = {
      val map = scala.collection.mutable.Map[Long, Int]()
      val n = nums.length
      for (i <- 0 until n - 1) {
        for (j <- i + 1 until n) {
          val temp = nums(i).toLong * nums(j)
          map(temp) = map.getOrElse(temp, 0) + 1
        }
      }
      map
    }

    def calculate(pow: scala.collection.mutable.Map[Long, Int], product: scala.collection.mutable.Map[Long, Int]): Int = {
      var ans = 0
      for ((k, v) <- pow) {
        if (product.contains(k)) {
          ans += v * product(k)
        }
      }
      ans
    }

    val pow1 = pow(nums1)
    val pow2 = pow(nums2)
    val p1 = product(nums1)
    val p2 = product(nums2)
    calculate(pow1, p2) + calculate(pow2, p1)
  }

  def main(args: Array[String]): Unit = {
    println(numTriplets(Array(7, 4), Array(5, 2, 8, 9)) == 1)
    println(numTriplets(Array(1, 1), Array(1, 1, 1)) == 9)
    println(numTriplets(Array(7, 7, 8, 3), Array(1, 2, 9, 7)) == 2)
    println(numTriplets(Array(4, 7, 9, 11, 23), Array(3, 5, 1024, 12, 18)) == 0)
    println(numTriplets(Array(43024, 99908), Array(1864)) == 0)
    println(numTriplets(Array(100000, 100000, 100000, 100000), Array(100000, 100000, 100000, 100000)) == 48)
  }
}
