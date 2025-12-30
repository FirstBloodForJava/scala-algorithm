package com.oycm.datastructure.stack.opposite;


public class Solution_1 {

    /**
     * 2296. <a href="https://leetcode.cn/problems/design-a-text-editor/description/">设计一个文本编辑器</a> 1912
     * <p>
     * 带光标的文本编辑器
     */
    static class TextEditor {

        /*
        preStack 记录光标左边的添加的字符, sufStack 记录光标左移后, 在光标后面的字符

        StringBuilder 解决
         */
        StringBuilder pre = new StringBuilder();
        StringBuilder suf = new StringBuilder();


        public TextEditor() {

        }

        /**
         * 将 text 添加到光标所在位置, 添加完后光标在 text 后
         *
         * @param text
         */
        public void addText(String text) {
            pre.append(text);
        }

        /**
         * 删除光标左边 k 个字符
         *
         * @param k
         * @return 实际删除的字符数目
         */
        public int deleteText(int k) {
            k = Math.min(k, pre.length());
            pre.setLength(pre.length() - k);
            return k;
        }

        /**
         * 将光标向左移动 k 次
         *
         * @param k
         * @return 返回移动后光标左边 min(10, len) 个字符, len 是光标左边的字符数目
         */
        public String cursorLeft(int k) {
            while (k > 0 && pre.length() > 0) {
                suf.append(pre.charAt(pre.length() - 1));
                pre.setLength(pre.length() - 1);
                k--;
            }
            return getPreMin(10);
        }

        /**
         * 将光标向右移动 k 次
         *
         * @param k
         * @return 返回移动后光标左边 min(10, len) 个字符, len 是光标左边的字符数目
         */
        public String cursorRight(int k) {
            while (k > 0 && suf.length() > 0) {
                pre.append(suf.charAt(suf.length() - 1));
                suf.setLength(suf.length() - 1);
                k--;
            }
            return getPreMin(10);
        }


        private String getPreMin(int min) {
            return pre.substring(Math.max(pre.length() - min, 0));
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor(); // 当前 text 为, '|' 字符表示光标
        textEditor.addText("leetcode"); // 当前文本为 "leetcode|"
        System.out.println(textEditor.deleteText(4)); // 返回 4, leet|

        textEditor.addText("practice"); // 当前文本为 "leetpractice|" 。
        System.out.println(textEditor.cursorRight(3).equals("etpractice")); // 返回 "etpractice"

        System.out.println(textEditor.cursorLeft(8).equals("leet")); // leet|practice返回 "leet"

        System.out.println(textEditor.deleteText(10) == 4); // 返回 4, |practice

        System.out.println(textEditor.cursorLeft(2) == ""); // 返回 ""

        System.out.println(textEditor.cursorRight(6)); // 返回 "practi"

    }

}
