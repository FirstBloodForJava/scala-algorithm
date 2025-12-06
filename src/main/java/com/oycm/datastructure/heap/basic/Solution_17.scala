package src.main.java.com.oycm.datastructure.heap.basic

object Solution_17 {

  /**
   * 3478. 选出和最大的 K 个元素 1753
   * https://leetcode.cn/problems/choose-k-elements-with-maximum-sum/description/
   *
   * i 从 [0, n-1], 执行以下操作
   *  - 找出所有满足 nums[j] < nums[i] 的下标 j
   *  - 下标 j 中在 nums2 中选出至多 k 个，最大化这些值作为结果
   *
   * @param nums1
   * @param nums2
   * @param k
   * @return
   */
  def findMaxSum(nums1: Array[Int], nums2: Array[Int], k: Int): Array[Long] = {
    /*
    可以新建一个二元组 nums(i), i(nums2 的值), 升序排序, 根据排序后的数组遍历答案，遍历过程中用最小堆维护前 k 大的元素
    越往右 元素越来越多，会有越来越多越小的数，只有新进来的数大于 堆顶元素，删除再加入
    怎么考虑 排序后 nums(j) == nums(j+1)的情况？ 分组循环计算
    核心点：最小堆 维护前 k 大和
    */
    val n = nums1.length
    val num3 = Array.fill[(Int, Int, Int)](n)(0, 0, 0)
    for (i <- nums1.indices) {
      num3(i) = (nums1(i), nums2(i), i)
    }
    val sort = num3.sortBy(_._1)
    val min = scala.collection.mutable.PriorityQueue[Int]()(Ordering.Int.reverse)
    val ans = Array.fill(n)(0L)

    var sum = 0L
    var i = 0
    while (i < n) { // 要找到相同元素的下标
      val start = i
      val num = sort(i)(0)
      // 分组循环
      while (i < n && sort(i)(0) == num) {
        ans(sort(i)(2)) = sum
        i += 1
      }
      // sort(start)(0) < sort(i)(0), 计算 前 k 大元素和
      var j = start
      while (j < i) {
        sum += sort(j)(1)
        min.addOne(sort(j)(1))
        // 最小堆，有 前 k 大的值
        if (min.size > k) {
          sum -= min.dequeue()
        }
        j += 1
      }

    }
    ans
  }

}
