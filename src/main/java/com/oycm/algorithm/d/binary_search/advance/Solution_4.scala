package com.oycm.algorithm.d.binary_search.advance

object Solution_4 {


  /**
   * 1170. 比较字符串最小字母出现频次
   * https://leetcode.cn/problems/compare-strings-by-frequency-of-the-smallest-character/description/
   *
   * 函数 f(s), 统计 s 中 字典序最小字母出现的频次
   *
   * 统计 words 中 f(s) > f(queries(i)) 的数目
   *
   * @param queries
   * @param words
   * @return
   */
  def numSmallerByFrequency(queries: Array[String], words: Array[String]): Array[Int] = {
    /*
    对 words 的字典序进行排序, 查找 f(queries(i) + 1 出现的位置, n - index 就是答案
    
    时间复杂度 
    */
    val n = words.length

    def f(s: String): Int = {
      var cnt = 0
      var ch = 'z'
      for (c <- s) {
        if (c < ch) {
          ch = c
          cnt = 1
        } else if (c == ch) {
          cnt += 1
        }
      }
      cnt
    }

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

    val ans = Array.fill(queries.length)(0)
    val wordFs = Array.fill(n)(0)
    for (i <- words.indices) {
      wordFs(i) = f(words(i))
    }
    val sort = wordFs.sorted
    for (i <- queries.indices) {
      val fs = f(queries(i))
      ans(i) = n - lowerBound(sort, fs + 1)
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(numSmallerByFrequency(Array("cbd"), Array("zaaaz")).toSeq)
    println(numSmallerByFrequency(Array("bbb", "cc"), Array("a", "aa", "aaa", "aaaa")).toSeq)
  }
}
