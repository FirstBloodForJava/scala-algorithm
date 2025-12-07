package com.oycm.datastructure.queue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Solution_2 {

    /**
     * 950. <a href="https://leetcode.cn/problems/reveal-cards-in-increasing-order/description/">按递增顺序显示卡牌</a> 1686
     * <p>
     * 按以下步骤显示卡牌，返回一个能以递增顺序显示卡牌的牌组顺序
     * <p>
     * - 从牌组顶部抽一张牌，显示它，然后将其从牌组中移出。
     * <p>
     * - 如果牌组中仍有牌，则将下一张处于牌组顶部的牌放在牌组的底部。
     * <p>
     * - 如果仍有未显示的牌，那么返回步骤 1。否则，停止行动。
     *
     * @param deck
     * @return
     */
    public static int[] deckRevealedIncreasing(int[] deck) {
        /*
        首先要对 deck 数组按升序排序
         */
        Arrays.sort(deck);
        int[] ans = new int[deck.length];
        Deque<Integer> index = new LinkedList<>();
        for (int i = 0; i < deck.length; i++) {
            index.add(i);
        }
        for (int x : deck) {
            ans[index.pollFirst()] = x;
            // 把坑位玩后移
            if (!index.isEmpty()) {
                index.add(index.pollFirst());
            }
        }

        return ans;
    }

}
