package com.oycm.algorithm.bb

object Solution_5 {

  /**
   * 350. 两个数组的交集 II
   * https://leetcode.cn/problems/intersection-of-two-arrays-ii/description/
   *
   * @param nums1
   * @param nums2
   * @return
   */
  def intersect(nums1: Array[Int], nums2: Array[Int]): Array[Int] = {
    val ans = scala.collection.mutable.ArrayBuffer[Int]()
    val map = scala.collection.mutable.Map[Int,Int]()
    if (nums1.length < nums2.length) {
      for(num <- nums1) {
        map(num) = map.getOrElse(num, 0) + 1
      }
    } else {
      for (num <- nums2) {
        map(num) = map.getOrElse(num, 0) + 1
      }
    }
    if (nums1.length < nums2.length) {
      for (num <- nums2) {
        if (map.contains(num) && map(num) > 0) {
          ans.addOne(num)
          map(num) -= 1
        }
      }
    } else {
      for (num <- nums1) {
        if (map.contains(num) && map(num) > 0) {
          ans.addOne(num)
          map(num) -= 1
        }
      }
    }

    ans.toArray
  }
}
