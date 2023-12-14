package dbpack;

import java.util.Date;

public class Statistic {
    private int userId;
    private String username;
    private int totalTrees;
    private double totalDueAmount;
    private double totalPaidAmount;
    private Date workDoneDate;

    public Statistic(int userId, String username, int totalTrees, double totalDueAmount,
                               double totalPaidAmount, Date workDoneDate) {
        this.userId = userId;
        this.username = username;
        this.totalTrees = totalTrees;
        this.totalDueAmount = totalDueAmount;
        this.totalPaidAmount = totalPaidAmount;
        this.workDoneDate = workDoneDate;
    }

    // Getter methods

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public int getTotalTrees() {
        return totalTrees;
    }

    public double getTotalDueAmount() {
        return totalDueAmount;
    }

    public double getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public Date getWorkDoneDate() {
        return workDoneDate;
    }
}
