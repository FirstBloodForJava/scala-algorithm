package com.oycm.algorithm.ba.modify

object Solution_10 {

  /**
   * 2460. 对数组执行操作 1224
   * https://leetcode.cn/problems/apply-operations-to-an-array/description/
   *
   * 需要对数组执行 n - 1 步操作，第 i 步操作要求对 nums 中第 i 个元素执行下述指令：
   *  - 如果 nums[i] == nums[i + 1]，则 nums[i] 的值变成原来的 2 倍，nums[i + 1] 的值变成 0 。否则，跳过这步操作。
   *
   * 执行完 全部 操作后，将所有 0 移动 到数组的 末尾 。
   *
   * [1,0,2,0,0,1] 0 移动到末尾 [1,2,1,0,0,0]
   * 要保持非 0 的相对顺序
   *
   * @param nums 非负 整数数组，长为 n
   * @return
   */
  def applyOperations(nums: Array[Int]): Array[Int] = {
    /*
    两次循环实现
    第一次 执行操作
    第二次 队列记录 0 的下标
    nums(i) == 0 时，队列记录 i
    当 nums(i) > 0 且 队列不为空，nums(i) 和 队列头交换，队列添加 i

    时间复杂度 O(n)
    空间复杂度 O(n) 额外队列记录 0 的下标
     */
    val n = nums.length
    for (i <- 0 until n - 1) {
      if (nums(i) == nums(i + 1)) {
        nums(i) *= 2
        nums(i + 1) = 0
      }
    }
    // 队列 记录 0 的下标
    val queue = scala.collection.mutable.Queue[Int]()
    for (i <- nums.indices) {
      if (nums(i) == 0) {
        queue.append(i)
      } else if (queue.nonEmpty && i > queue.head) {
        val j = queue.removeHead()
        nums(j) = nums(i)
        nums(i) = 0
        queue.append(i)
      }
    }

    nums
  }

  def main(args: Array[String]): Unit = {
    println(applyOperations(Array(1, 2, 2, 1, 1, 0)).toSeq)
    println(applyOperations(Array(0, 1)).toSeq)

    println(applyOperations(Array(847, 847, 0, 0, 0, 399, 416, 416, 879, 879, 206, 206, 206, 272)).toSeq == Array(1694, 399, 832, 1758, 412, 206, 272, 0, 0, 0, 0, 0, 0, 0).toSeq)


  }

}
