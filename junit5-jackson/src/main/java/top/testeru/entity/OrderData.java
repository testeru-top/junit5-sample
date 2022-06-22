package top.testeru.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @program: junit5_samples
 * @author: testeru.top
 * @description: order的yaml文件
 * @Version 1.0
 * @create: 2022/6/20 5:39 PM
 */
public class OrderData {
    private String item;
    private Integer quantity;
    private BigDecimal unitPrice;
    private LocalDate orderDate;
    private Boolean getFlag;


    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Boolean getGetFlag() {
        return getFlag;
    }

    public void setGetFlag(Boolean getFlag) {
        this.getFlag = getFlag;
    }

    @Override
    public String toString() {
        return "Order{" +
                "item='" + item + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", orderDate=" + orderDate +
                ", getFlag=" + getFlag +
                '}';
    }
}
