package com.alex.ecoscan.model;
import java.util.List;

public class OrderWithCodes {
    private Order order;
    private List<Code> codes;

    public OrderWithCodes(Order order, List<Code> codes) {
        this.order = order;
        this.codes = codes;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Code> getCodes() {
        return codes;
    }

    public void setCodes(List<Code> codes) {
        this.codes = codes;
    }
}
