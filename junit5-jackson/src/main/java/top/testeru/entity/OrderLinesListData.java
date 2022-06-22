package top.testeru.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @program: junit5_samples
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/6/20 6:29 PM
 */
public class OrderLinesListData {
    @JsonProperty("orderLines")
    private List<OrderListData> lists;


    @Override
    public String toString() {
        return "OrderLinesListData{" +
                "lists=" + lists +
                '}';
    }
}
