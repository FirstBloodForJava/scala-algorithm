package com.oycm.algorithm.d.binary_search.advance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ouyangcm
 * create 2025/12/3 15:00
 */
public class Solution_9 {

    public static void main(String[] args) {
        SnapshotArray snap = new SnapshotArray(3);
        snap.set(1, 6);

        snap.snap();
        snap.snap();

        snap.set(1, 19);
        snap.set(0, 4);



        System.out.println(snap.get(2, 1));
        System.out.println(snap.get(2, 0));
        System.out.println(snap.get(0, 1));
    }
}

/**
 * 1146. <a href="https://leetcode.cn/problems/snapshot-array/description/">快照数组</a> 1771
 */
class SnapshotArray {

    int snap = 0;

    List<List<int[]>> lists = new ArrayList<>();

    /**
     * 创建长为 length 的数组，初始值默认 0
     *
     * @param length
     */
    public SnapshotArray(int length) {
        for (int i = 0; i < length; i++) {
            List<int[]> temp = new ArrayList<>();
            temp.add(new int[]{0, 0});
            lists.add(temp);
        }
    }

    /**
     * 指定数组 index 的值设为 val
     *
     * @param index
     * @param val
     */
    public void set(int index, int val) {
        List<int[]> indexes = lists.get(index);
        int n = indexes.size();
        if (snap == 0) {
            indexes.get(0)[1] = val;
        } else if (snap > indexes.get(n - 1)[0]) {
            int[] ints = new int[]{snap, val};
            indexes.add(ints);
        } else {
            indexes.get(n - 1)[1] = val;
        }

    }

    /**
     * 获取该数组快照
     *
     * @return
     */
    public int snap() {
        return snap++;
    }

    /**
     * 获取指定快照的 index
     * <p>
     * 每次快照 新增一个数组，是查询最快的方式，但是每次快照都需要复制一次数组，空间复杂度极高
     * <p>
     * 是否可以只记录每次 set 的版本和值，index = [{0,0}, {1,3}, {6,10}] 快照版本再 index 的数组中是递增的。
     * 如果 last >= snap_id, 则直接返回最后一次的值，否则 只需要 查询 大于等于 snap_id+1 在数组中的 index -1,
     *
     * @param index   数组 index
     * @param snap_id 快照 id
     * @return
     */
    public int get(int index, int snap_id) {
        // 二分查找
        List<int[]> indexes = lists.get(index);
        int r = indexes.size();
        int l = -1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            int[] ints = indexes.get(mid);
            if (ints[0] >= snap_id + 1) {
                r = mid;
            } else {
                l = mid;
            }
        }

        return indexes.get(r - 1)[1];
    }

}