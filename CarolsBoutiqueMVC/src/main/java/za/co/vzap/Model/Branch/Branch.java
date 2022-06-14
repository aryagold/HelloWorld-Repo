package za.co.vzap.Model.Branch;

import za.co.vzap.Interface.Model.IEntity;

public class Branch implements IEntity {
    public String branchId;
    private String name;
    private double monthlyTarget;
    private double dailyTarget;

    
    public Branch(String name, double monthlyTarget, double dailyTarget) {
        this.name = name;
        this.monthlyTarget = monthlyTarget;
        this.dailyTarget = dailyTarget;
    }

    public Branch(String branchId, String name, double monthlyTarget, double dailyTarget) {
        this.branchId = branchId;
        this.name = name;
        this.monthlyTarget = monthlyTarget;
        this.dailyTarget = dailyTarget;
    }
    
    
    public Branch() {
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMonthlyTarget() {
        return monthlyTarget;
    }

    public void setMonthlyTarget(double monthlyTarget) {
        this.monthlyTarget = monthlyTarget;
    }

    public double getDailyTarget() {
        return dailyTarget;
    }

    public void setDailyTarget(double dailyTarget) {
        this.dailyTarget = dailyTarget;
    }

}
