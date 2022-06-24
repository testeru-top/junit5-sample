/*
 * @Author: 霍格沃兹测试开发学社-盖盖
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */
package top.testeru.entity;

import java.util.List;

/**
 * @program: junit5-sample
 * @author: testeru.top
 * @description: sum-data实体类
 * @Version 1.0
 * @create: 2022/6/22 17:08
 */
public class SumData {
    private List<Integer> add;
    private int re;

    public SumData(List<Integer> add, int re) {
        this.add = add;
        this.re = re;
    }

    public List<Integer> getAdd() {
        return add;
    }

    public void setAdd(List<Integer> add) {
        this.add = add;
    }

    public int getRe() {
        return re;
    }

    public void setRe(int re) {
        this.re = re;
    }

    @Override
    public String toString() {
        return "SumData{" +
                "add=" + add +
                ", re=" + re +
                '}';
    }
}
