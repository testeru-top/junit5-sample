package top.testeru.entity;

import java.util.List;

/**
 * @program: junit5_samples
 * @author: testeru.top
 * @description: sum.yaml文件对应的实体类
 * @Version 1.0
 * @create: 2022/6/20 3:04 PM
 */
public class Sum {
    private List<Data> datas;

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "Sum{" +
                "datas=" + datas +
                '}';
    }
}
