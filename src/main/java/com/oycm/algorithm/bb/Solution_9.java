package com.oycm.algorithm.bb;

public class Solution_9 {

    /**
     * 925. <a href="https://leetcode.cn/problems/long-pressed-name/description/">长按键入</a> 1271
     *
     * @param name
     * @param typed
     * @return
     */
    public boolean isLongPressedName(String name, String typed) {
        /*
        你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
        检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 true。
         */
        /*
        遍历 typed name,
            如果 typed[j] == name[i] 都自增；
            如果不相等，看 typed[j] 是不是和前一个字符多次输入，如果不是，则就是不匹配
        最后看 是否完全遍历所有 name 字符
         */
        int n = name.length(), m = typed.length();
        char[] c1 = name.toCharArray();
        char[] c2 = typed.toCharArray();
        int i = 0, j = 0;
        while (j < m) {
            if (i < n && c1[i] == c2[j]) {
                i++;
                j++;
            } else if (j > 0 && c2[j] == c2[j - 1]) {
                j++;
            } else {
                return false;
            }
        }
        return i == n;
    }

}
