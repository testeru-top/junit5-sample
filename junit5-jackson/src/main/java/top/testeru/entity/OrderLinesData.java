package top.testeru.entity;

import java.util.List;

/**
 * @program: junit5_samples
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/6/20 6:25 PM
 */
public class OrderLinesData {
    private List<OrderData> orderLines;

    @Override
    public String toString() {
        return "OrderLinesData{" +
                "orderLines=" + orderLines +
                '}';
    }

    public List<OrderData> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderData> orderLines) {
        this.orderLines = orderLines;
    }
}
