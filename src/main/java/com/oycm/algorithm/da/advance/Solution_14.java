package com.oycm.algorithm.da.advance;


import java.util.HashMap;
import java.util.Map;

public class Solution_14 {


}

/**
 * 911. <a href="https://leetcode.cn/problems/online-election/description/">在线选举</a> 2001
 * 第 i 张票是在 times[i] 时刻投给 候选人 persons[i]
 */
class TopVotedCandidate {

    int[] times;
    // 记录 times[i] 领先人编号
    Map<Integer, Integer> map = new HashMap<>();

    /**
     * @param persons
     * @param times   严格递增的数组
     */
    public TopVotedCandidate(int[] persons, int[] times) {

        /*
        q 进行二分查找，times 中大于 t 的 index，[0, index-1) 中谁的票数领先返回谁
        可以先维护 map 记录每个 times[i] 的 领先者编号，每次 q 的时间复杂度就是 log t
        怎么 在 遍历 times 的过程中记录当前时间点的领先者？
         */
        this.times = times;
        Map<Integer, Integer> personScore = new HashMap<>();
        // 记录投票记录最多的人，越往后投票，出现相同次数时，后出现的次数覆盖前面相同次数的人
        int maxPerson = persons[0];
        for (int i = 0; i < times.length; i++) {
            int person = persons[i];
            personScore.merge(person, 1, Integer::sum);
            if (personScore.get(person) >= personScore.get(maxPerson)) {
                maxPerson = person;
                map.put(i, person);
            } else {
                map.put(i, maxPerson);
            }
        }
    }

    /**
     * 求在 时刻 t 选举中，领先的选举人编号，在平局的情况下，最近获得投票的人获胜
     *
     * @param t
     * @return
     */
    public int q(int t) {
        int l = -1, r = times.length;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (times[mid] > t) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return map.get(r - 1);
    }
}
