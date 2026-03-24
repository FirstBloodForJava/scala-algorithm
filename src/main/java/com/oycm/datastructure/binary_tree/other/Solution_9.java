package com.oycm.datastructure.binary_tree.other;

public class Solution_9 {

    /**
     * 331. <a href="https://leetcode.cn/problems/verify-preorder-serialization-of-a-binary-tree/description/">验证二叉树的前序序列化</a>
     *
     * @param preorder 前序遍历 二叉树的结果, # 表示空节点
     * @return
     */
    public boolean isValidSerialization(String preorder) {
        /*
        题解思路: 用栈来记录当前节点需要的槽位,
         */
        int i = 0, n = preorder.length();
        // 这里特殊初始化 为 1, 如果第一个 是 #, 槽位为 0, 还有元素则不合法, 如果不为空, 去掉槽位, 栈顶元素需要新的两个槽位
        int slot = 1;
        while (i < n) {
            if (slot == 0) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#') {
                slot--;
                i++;
            } else {
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                slot++;
            }
        }

        return slot == 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_9().isValidSerialization("9,#,1,#,#"));
    }

}
