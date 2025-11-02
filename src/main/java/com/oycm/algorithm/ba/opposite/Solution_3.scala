package com.oycm.algorithm.ba.opposite

object Solution_3 {

  /**
   * 345.反转字符串中的元音字母
   * https://leetcode.cn/problems/reverse-vowels-of-a-string/
   *
   * 元音字母: a, e, i, o, u 大小写两种格式都有
   *
   * 仅反转字符串中的所有元音字母，并返回结果字符串。
   *
   * @param s 可打印的 ASCII 字符组成的字符串
   * @return
   */
  def reverseVowels(s: String): String = {
    /*
    反转就是：首-尾 交换 => 首+1-尾-1 知道 l >= r
    */
    val n = s.length
    val ans = s.toCharArray

    var l = 0
    var r = n - 1
    val vowels = scala.collection.mutable.ArrayBuffer[Int]()
    val vowel = "aeiouAEIOU"

    /*
    这里为什么要 l <= r
    当 l = 8, r = 10 时 进入循环，如果 l 符合条件 l = 9，r = 10 不符合 r = 9 最后有一个字符没有遍历到
    */
    while (l <= r) {
      if (vowels.isEmpty) {
        if (vowel.indexOf(s(l)) > -1) {
          vowels.append(l)
        }
        l += 1
      }
      if (vowels.size == 1) {
        if (vowel.indexOf(s(r)) > -1) {
          vowels.append(r)
        }
        r -= 1
      }
      if (vowels.size == 2) {
        ans(vowels(0)) = s(vowels(1))
        ans(vowels(1)) = s(vowels(0))
        vowels.clear
      }
    }
    String.valueOf(ans)
  }

  def method_1(s: String): String = {
    val ans = s.toCharArray
    var l = 0
    var r = s.length - 1
    val vowels = scala.collection.mutable.ArrayBuffer[Int]()
    val vowel = "aeiouAEIOU"
    // 题解：在记录答案时移动，并且只有 2 种情况，不需要额外变量记录
    while (l < r) {
      if (vowels.isEmpty) {
        if (vowel.indexOf(s(l)) > -1) {
          vowels.append(l)
        } else {
          l += 1
        }
      }
      if (vowels.size == 1) {
        if (vowel.indexOf(s(r)) > -1) {
          vowels.append(r)
        } else {
          r -= 1
        }
      }
      if (vowels.size == 2) {
        ans(vowels(0)) = s(vowels(1))
        ans(vowels(1)) = s(vowels(0))
        vowels.clear
        l += 1
        r -= 1
      }
    }
    String.valueOf(ans)
  }

  def method_2(s: String): String = {
    /*
    去掉记录的数组
    */
    val ans = s.toCharArray
    var l = 0
    var r = s.length - 1

    val vowel = "aeiouAEIOU"
    // 题解：在记录答案时移动
    while (l < r) {
      if (vowel.indexOf(s(l)) == -1) {
        l += 1
      } else if (vowel.indexOf(s(r)) == -1) {
        r -= 1
      } else {
        // 更新答案
        ans(l) = s(r)
        ans(r) = s(l)
        l += 1
        r -= 1
      }
    }
    String.valueOf(ans)
  }

  def method_3(s: String): String = {
    val ans = s.toCharArray
    val n = s.length
    var l = 0
    var r = n - 1

    val vowel = "aeiouAEIOU"

    while (l < r) {
      while (l < n && vowel.indexOf(s(l)) == -1) {
        l += 1
      }
      while (r > 0 && vowel.indexOf(s(r)) == -1) {
        r -= 1
      }
      if (l < r) {
        ans(l) = s(r)
        ans(r) = s(l)
        l += 1
        r -= 1
      }
    }

    String.valueOf(ans)
  }


  def main(args: Array[String]): Unit = {
    println(reverseVowels("IceCreAm") == "AceCreIm")
    println(reverseVowels("leetcode") == "leotcede")
    println(reverseVowels(" apG0i4maAs::sA0m4i0Gp0") == " ipG0A4mAas::si0m4a0Gp0")

    println(method_1("IceCreAm") == "AceCreIm")
    println(method_1("leetcode") == "leotcede")
    println(method_1(" apG0i4maAs::sA0m4i0Gp0") == " ipG0A4mAas::si0m4a0Gp0")

    println(method_2("IceCreAm") == "AceCreIm")
    println(method_2("leetcode") == "leotcede")
    println(method_2(" apG0i4maAs::sA0m4i0Gp0") == " ipG0A4mAas::si0m4a0Gp0")

    println(method_3("IceCreAm") == "AceCreIm")
    println(method_3("leetcode") == "leotcede")
    println(method_3(" apG0i4maAs::sA0m4i0Gp0") == " ipG0A4mAas::si0m4a0Gp0")
  }
}
