/*
 * @Author: 霍格沃兹测试开发学社-盖盖
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */
package top.testeru.entity;

/**
 * @program: junit5-sample
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/6/24 17:04
 */
public class ShellResult {
    private String caseName;
    private Boolean result;

    @Override
    public String toString() {
        return "ShellResult{" +
                "caseName='" + caseName + '\'' +
                ", result=" + result +
                '}';
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
