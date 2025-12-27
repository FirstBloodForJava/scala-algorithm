package com.oycm.datastructure.heap.opposite;

import java.util.PriorityQueue;

public class Solution_1 {


    /**
     * 2102. <a href="https://leetcode.cn/problems/sequentially-ordinal-rank-tracker/description/">序列顺序查询</a> 2159
     */
    static class SORTracker {

        /*
        新添加的景点会影响 后续 get 的排名, 能否定义两个堆 min size == cnt 时, top 就是第 i 好景点, max 记录最好的景点, 每次 get 时, 把 max.top 移到 min
        怎么维护 添加这个过程
            如果 cur 大于 min 中的最小值, 需要将 min 中的较小值提取出来
            如果 cur 小于 min 中的最小值, cur 可以直接加入到 max 中
            综上所述, 可以将 cur 添加到 min 中, 再从 max.add(min.poll()); 也可以不用管 min 是否为空
         */
        PriorityQueue<Tuple> max = new PriorityQueue<>((a, b) -> a.score == b.score ? a.name.compareTo(b.name) : b.score - a.score);
        PriorityQueue<Tuple> min = new PriorityQueue<>((a, b) -> a.score == b.score ? b.name.compareTo(a.name) : a.score - b.score);

        int cnt = 0;

        public SORTracker() {

        }

        /**
         * 添加景点, 景点按照最好到最坏排序。景点评分越高越好，景点评分相同，景点名称 字典序 越小越好。
         *
         * @param name
         * @param score
         */
        public void add(String name, int score) {
            min.add(new Tuple(name, score));
            max.add(min.poll());
        }

        /**
         * 查询已添加经典中第 i 好的景点
         *
         * @return
         */
        public String get() {
            cnt++;
            if (min.size() != cnt) {
                min.add(max.poll());
            }
            return min.peek().name;
        }

        static class Tuple {
            String name;
            int score;

            public Tuple(String name, int score) {
                this.name = name;
                this.score = score;
            }
        }
    }

    public static void main(String[] args) {
        SORTracker tracker = new SORTracker();
        tracker.add("bradford", 2);
        tracker.add("branford", 3);


    }

}
