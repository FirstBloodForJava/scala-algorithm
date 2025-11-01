package com.oycm.algorithm.abc.just

object Solution_3 {

  /**
   * 3306.元音辅音字符串计数 II 2200
   * https://leetcode.cn/problems/count-of-substrings-containing-every-vowel-and-k-consonants-ii/?envType=problem-list-v2&envId=Nqw5Czic
   *
   * 元音字母: a, e, i, o, u。
   * 求每个元音字母都至少出现一次，并且恰好包含 k 个辅音字母的 子字符串总数
   *
   * @param word 仅小写字母组成的字符串
   * @param k    非负整数
   * @return
   */
  def countOfSubstrings(word: String, k: Int): Long = {
    /*
    问题是否可以转换成求 元音字母都至少出现一次，恰好小于等于 k 个辅音字母的字符串，用 k 的结果 - (k-1) 的结果
    [l, r1] （元音字母至少首次都出现 1 次），且辅音字母出现小于等于 k 次，当 [l, r2] 中辅音字母出现大于 k 次时，以 l 为左端点开始符合要求的字串数就是 r2 - r1
    下一步，l 右移，随着 l 的不断右移，r1，r2 也会不断右移
    固定左端点求最长？
    这样是不是就可以使用滑动窗口求解
    上面答案只考虑了这种情况 aeiouppp paeioupp 这种情况会少计答案 aeipoupp 不会
    */

    /*
    题解：某班有 10 人至少 20岁，3 个人至少 21 岁，恰好 20 岁的有多少？
    10 人 年龄 >= 20，3 人 年龄 >= 21，20 岁年龄的人 = 10 - 7
    由于 元音字母至少首次都出现 1 次 的条件，字符串需要越多越好，转换成至少问题更简单
    至少出现 k 次 - 至少出现 k+1 次
    至少出现，要求字符串越长越好
    */
    def countLeast(word: String, k: Int): Long = {
      var ans = 0L

      val cntMap = scala.collection.mutable.Map[Char, Int]()
      var kCnt = 0
      var r = 0
      val n = word.length
      for (l <- word.indices) {

        while (r < word.length && (kCnt < k || cntMap.size < 5)) {
          val char = word(r)
          if (char == 'a' || char == 'e' || char == 'i' || char == 'o' || char == 'u') {
            cntMap(char) = cntMap.getOrElse(char, 0) + 1
          } else {
            kCnt += 1
          }
          r += 1
        }
        // 记录答案
        if (kCnt >= k && cntMap.size == 5) {
          ans += n - r + 1
        }

        val char = word(l)
        if (char == 'a' || char == 'e' || char == 'i' || char == 'o' || char == 'u') {
          if (cntMap(char) == 1) {
            cntMap.remove(char)
          } else {
            cntMap(char) = cntMap(char) - 1
          }
        } else {
          kCnt -= 1
        }
      }
      ans
    }

    countLeast(word, k) - countLeast(word, k + 1)
  }


  def main(args: Array[String]): Unit = {
    println(countOfSubstrings("aeioqq", 1))
    println(countOfSubstrings("aeiou", 0))
    println(countOfSubstrings("ieaouqqieaouqq", 1))
    println(countOfSubstrings("ieaouqqieaouqq", 2))
  }
}
