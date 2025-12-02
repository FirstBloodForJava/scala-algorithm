package com.oycm.datastructure.stack;

public class Solution_5 {
}

/**
 * 1472. <a href="https://leetcode.cn/problems/design-browser-history/description/">设计浏览器历史记录</a> 1454
 */
class BrowserHistory {

    String[] history;
    // 历史记录容量
    int cnt = 0;
    // 当前位置
    int cur = 0;

    public BrowserHistory(String homepage) {
        history = new String[5001];
        history[cnt++] = homepage;
    }

    /**
     * 当前页跳转访问 url 对应的页面，浏览历史前进的记录全部删除
     *
     * @param url
     */
    public void visit(String url) {
        // 没有进行前进后退时 cnt = cur + 1，进行后退操作后，由于前进需删除记录，所以 cnt 只能等于 cur + 1
        cnt = cur + 1;
        history[cnt++] = url;
        cur++;
    }

    /**
     * 浏览历史中后退 steps 步，只能后退至多 历史记录
     *
     * @param steps
     * @return
     */
    public String back(int steps) {
        // 大于等于当前位置，只能到 0
        if (steps >= cur) {
            cur = 0;
        } else {
            // 其他情况，移动指定步数
            cur -= steps;
        }
        return history[cur];
    }

    /**
     * 浏览历史中前进 steps 步，只能前进至多 历史记录
     *
     * @param steps
     * @return
     */
    public String forward(int steps) {
        // cur 只能移动到 cnt - 1
        if (cnt - 1 >= cur + steps) {
            cur += steps;
        } else {
            cur = cnt - 1;
        }

        return history[cur];
    }
}
