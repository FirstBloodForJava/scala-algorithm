package com.oycm.algorithm.ba.modify

object Solution_11 {


  /**
   * 1089. 复写零 1263
   * https://leetcode.cn/problems/duplicate-zeros/description/
   *
   * 将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
   *
   * 开始 arr = [1,0,2,3,0,4,5,0] 8
   * 结果 arr = [1,0,0,2,3,0,0,4] 8
   *
   * @param arr 整数数组 长
   */
  def duplicateZeros(arr: Array[Int]): Unit = {
    /*
    i in [0, n)
    使用一个 有序集合记录 需要填写 0 的位置，cnt 记录 0 出现的次数
    这样还是无法解决，有一个问题：arr(1) == 0, 则需要修改 arr(2) == 0, 但是下次遍历 2 时，还需使用 arr(2)，所以这样不行

    所以需要 一个 数据结构记录 记录这个值，存在则使用 该值进行遍历

     */
    // 存 下次遍历的值
    val queue = scala.collection.mutable.Queue[Int]()
    val n = arr.length
    var i = 0

    while (i < n) {
      if (queue.nonEmpty) {
        // 先入队，遍历上次存的 元素
        queue.append(arr(i))
        // 取上次值修改
        arr(i) = queue.removeHead()
        if (arr(i) == 0 && i < n - 1) {
          i += 1
          queue.append(arr(i))
          arr(i) = 0
        }

      } else if (arr(i) == 0) {
        i += 1
        queue.append(arr(i))
        arr(i) = 0
      }

      i += 1
    }

    println(arr.toSeq)
  }

  def main(args: Array[String]): Unit = {
    duplicateZeros(Array(1, 0, 2, 3, 0, 4, 5, 0))
    duplicateZeros(Array(1, 2, 3))
    duplicateZeros(Array(0, 1, 0, 1, 2, 3, 0))
  }

}
