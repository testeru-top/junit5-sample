/*
 * @Author: 霍格沃兹测试开发学社-盖盖
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */
package top.testeru.entity;

import java.util.List;

/**
 * @program: junit5-sample
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/6/24 17:01
 */
public class ResultList {
    List<ShellResult> resultList;

    @Override
    public String toString() {
        return "ResultList{" +
                "resultList=" + resultList +
                '}';
    }

    public List<ShellResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<ShellResult> resultList) {
        this.resultList = resultList;
    }
}
