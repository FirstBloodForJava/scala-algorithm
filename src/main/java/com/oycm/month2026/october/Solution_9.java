package com.oycm.month2026.october;

public class Solution_9 {

    /**
     * 1541. <a href="https://leetcode.cn/problems/minimum-insertions-to-balance-a-parentheses-string/description/">平衡括号字符串的最少插入次数</a> 1759
     *
     * @param s
     * @return
     */
    public int minInsertions(String s) {
        /*
        给你一个括号字符串 s ，它只包含字符 '(' 和 ')' 。一个括号字符串被称为平衡的当它满足：
            任何左括号 '(' 必须对应两个连续的右括号 '))' 。
            左括号 '(' 必须在对应的连续两个右括号 '))' 之前。
        比方说 "())"， "())(())))" 和 "(())())))" 都是平衡的， ")()"， "()))" 和 "(()))" 都是不平衡的。
        你可以在任意位置插入字符 '(' 和 ')' 使字符串平衡。
        请你返回让 s 平衡的最少插入次数。
         */
        /*
        (()))
         */
        int balance = 0;
        int ans = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (balance % 2 == 0) {
                    if (balance < 0) {
                        // 右括号多，左边需填好
                        ans -= balance / 2;
                        balance = 0;
                    }

                } else if (balance > 0) {
                    // 左括号多，奇数个右括号
                    ans += balance;
                    balance = 0;
                } else {
                    // 左括号少，奇数个右括号
                    ans -= balance / 2 - 2;
                    balance = 0;
                }
                balance += 2;
            } else {
                balance--;
            }
        }

        if (balance > 0) {
            ans += balance;
        } else if (balance % 2 != 0) {
            ans -= balance / 2 - 2;
        }

        return ans;
    }

}
