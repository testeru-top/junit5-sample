package top.testeru.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @program: junit5_samples
 * @author: testeru.top
 * @description: 成员变量与key不一致
 * @Version 1.0
 * @create: 2022/6/20 5:49 PM
 */
public class OrderListData {
    @JsonProperty("item")
    private String otherItem;
    @JsonProperty("quantity")
    private int qua;
    @JsonProperty("unitPrice")
    private BigDecimal price;
    @JsonProperty("orderDate")
    private LocalDate date;
    @JsonProperty("getFlag")
    private Boolean isFlag;


    @Override
    public String toString() {
        return "OrderList{" +
                "otherItem='" + otherItem + '\'' +
                ", qua=" + qua +
                ", price=" + price +
                ", date=" + date +
                ", isFlag=" + isFlag +
                '}';
    }

    public String getOtherItem() {
        return otherItem;
    }

    public void setOtherItem(String otherItem) {
        this.otherItem = otherItem;
    }

    public int getQua() {
        return qua;
    }

    public void setQua(int qua) {
        this.qua = qua;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getFlag() {
        return isFlag;
    }

    public void setFlag(Boolean flag) {
        isFlag = flag;
    }
}
