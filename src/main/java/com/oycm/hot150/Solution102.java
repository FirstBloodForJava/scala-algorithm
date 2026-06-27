package com.oycm.hot150;

import java.util.*;

public class Solution102 {


}

/**
 * 380. <a href="https://leetcode.cn/problems/insert-delete-getrandom-o1/description/">O(1) 时间插入、删除和获取随机元素</a>
 */
class RandomizedSet {

    /*
    RandomizedSet() 初始化 RandomizedSet 对象
    bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false。
    bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false。
    int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
     */

    /*
    怎么做多随机返回集合中的元素？
    使用两个 map，以及额外变量记录，但是随机元素无法实现。不能判断随机到的下标是否还在集合中。

    map 记录 val，及其在可变数组中的下标
    插入操作，在判断需要插入后：
        将 val 添加到数组末尾，map 记录 val 和 下标 idx
    删除操作，在判断需要删除后：
        获取删除元素的下标，将数组最后一个元素交换到要删除的下标处，同时最后一个元素在 map 中的下标也需要修改
     */
    Map<Integer, Integer> map = new HashMap<>();
    List<Integer> vals = new ArrayList<>();


    Random random = new Random();

    public RandomizedSet() {

    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, vals.size());
        vals.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int updateIdx = map.get(val);
        int lastVal = vals.get(vals.size() - 1);
        map.put(lastVal, updateIdx);
        // 先修改，后移除，只有最后一个元素时，会有问题
        vals.set(updateIdx, lastVal);
        vals.remove(vals.size() - 1);
        // 最后做删除，先删除后修改，只有最后一个元素时，会有问题
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return vals.get(random.nextInt(vals.size()));
    }
}
