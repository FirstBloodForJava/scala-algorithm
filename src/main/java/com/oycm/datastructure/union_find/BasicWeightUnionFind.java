package com.oycm.datastructure.union_find;

/**
 * 带权并查集-简单模板
 */
public class BasicWeightUnionFind {

    private final int[] fa; // 代表元
    private final int[] dis; // dis[x] 表示 x 到（x 所在集合的）代表元（fa[x]）的距离，dis[x] = fa[x] - x，后面后用到这个定义

    public BasicWeightUnionFind(int n) {
        fa = new int[n];
        dis = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }
    }

    // 返回 x 所在集合的代表元
    // 同时做路径压缩
    public int find(int x) {
        if (fa[x] != x) {
            /*
            a -> b 10;
            b -> c 20;
            a -> c ?
            dis[a] = dis[b] + (b-a)
             */
            int root = find(fa[x]);
            dis[x] += dis[fa[x]]; // 递归更新 x 到其代表元的距离
            fa[x] = root;
        }
        return fa[x];
    }

    // 判断 x 和 y 是否在同一个集合（同普通并查集）
    public boolean same(int x, int y) {
        return find(x) == find(y);
    }


    /**
     * 需要保证 from, to 在一个集合中
     * @param from
     * @param to
     * @return
     */
    public int getRelativeDistance(int from, int to) {
        find(from);
        find(to);
        // to-from = (x-from) - (x-to) = dis[from] - dis[to]
        return dis[from] - dis[to];
    }

    /**
     * to - from 表示 to 到 from 的距离是 value
     *
     * @param from
     * @param to
     * @param value
     * @return 如果 from 和 to 不在同一个集合，返回 true，否则返回是否与已知信息矛盾
     */
    public boolean merge(int from, int to, int value) {
        int x = find(from), y = find(to);
        if (x == y) {
            // to-from = (x-from) - (x-to) = dis[from] - dis[to] = value
            return dis[from] - dis[to] == value;
        }
        /*
        已知 dis[from] = x-from; dis[to] = y-to; value = to-from;
        现在要计算 y-x 距离
           x ----?---- y
          /           /
        from ------- to
        由于 y-from = (y-x) + (x-from) = (y-to) + (to-from)，把 (x-from) 移项到右边得
        dis[x] = y-x = (y-to) + (to-from) - (x-from)
               = dis[to] + value - dis[from];
         */
        dis[x] = value + dis[to] - dis[from];
        fa[x] = y;
        return true;
    }

}
