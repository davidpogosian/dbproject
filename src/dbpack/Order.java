package dbpack;

import java.util.Date;

public class Order {
    private int orderId;
    private int quoteId;
    private String status;
    private Date datePaid;

    // Constructors
    public Order() {
    }

    public Order(int orderId, int quoteId, String status, Date datePaid) {
        this.orderId = orderId;
        this.quoteId = quoteId;
        this.status = status;
        this.datePaid = datePaid;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }
}
