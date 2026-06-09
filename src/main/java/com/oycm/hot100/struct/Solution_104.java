package com.oycm.hot100.struct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_104 {

    /**
     * 406. <a href="https://leetcode.cn/problems/queue-reconstruction-by-height/description/">根据身高重建队列</a>
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        /*
        假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
        每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
        请你重新构造并返回输入数组 people 所表示的队列。
        返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
         */
        /*
        题解思路：people 先按 people[0] 倒序排序，如果相等，则按 people[1] 升序排序
        [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
        排序后
        [[7,0],[7,1],[6,1],[5,0],[5,2],[4,4]]
        根据排序后的 people[1] 位置，往集合中插入元素
        [[7,0],[6,1],[7,1]] 后面要插入一个 [5,0] 前面的元素比它大，插入在他们前面不会影响后续的位置，所有可以直接插入到 0 这个位置。

         */
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        List<int[]> ans = new ArrayList<>();
        for (int[] person : people) {
            ans.add(person[1], person);
        }

        return ans.toArray(new int[people.length][]);
    }

}
