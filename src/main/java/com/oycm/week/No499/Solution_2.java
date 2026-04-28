package com.oycm.week.No499;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_2 {

    /**
     * 3913. <a href="https://leetcode.cn/problems/sort-vowels-by-frequency/description/">按频率对元音排序</a>
     *
     * @param s 由小写英文字母组成的字符串 s
     * @return
     */
    public String sortVowels(String s) {
        /*
        仅重新排列字符串中的 元音字母，使它们按照出现频率的 非递增 顺序排列。
        如果多个元音字母的 出现频率 相同，则按照它们在 s 中 首次出现 的位置排序。
        元音字母为 'a'、'e'、'i'、'o' 和 'u'。
         */
        /*
        用一个什么数据来记录元音字母的次数以及首次出现的位置，按 频率从大到小，频率相同则按顺序从小到大

         */
        char[] ans = s.toCharArray();
        // 记录出现次数
        int[] cnt = new int[5];
        // 次数相同，值在前面保持顺序（稳定排序）
        List<Character> vowels = new ArrayList<>();
        for (char c : ans) {
            int idx = mp[c];
            if (idx < 0) {
                continue;
            }
            if (cnt[idx] == 0) {
                vowels.add(c);
            }
            cnt[idx]++;
        }
        vowels.sort((a, b) -> cnt[mp[b]] - cnt[mp[a]]);
        int j = 0;
        for (int i = 0; i < ans.length; i++) {
            if (mp[ans[i]] < 0) continue;
            ans[i] = vowels.get(j);
            if (--cnt[mp[ans[i]]] == 0) {
                // 使用 下一个元音
                j++;
            }
        }

        return new String(ans);
    }

    private static final int[] mp = new int[128];

    static {
        Arrays.fill(mp, -1);
        mp['a'] = 0;
        mp['e'] = 1;
        mp['i'] = 2;
        mp['o'] = 3;
        mp['u'] = 4;
    }

}
