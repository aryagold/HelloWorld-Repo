package co.vzap.Branch.Model;

import co.vzap.Sale.Model.IEntity;

public class Branch implements IEntity {
    public String branchId;
    public String name;
    public double monthlyTarget;
    public double dailyTarget;
    public double monthlySales;
    public double dailySales;

    
    public Branch(String name, double monthlyTarget, double dailyTarget, double monthlySales, double dailySales) {
        this.name = name;
        this.monthlyTarget = monthlyTarget;
        this.dailyTarget = dailyTarget;
        this.monthlySales = monthlySales;
        this.dailySales = dailySales;
    }

    public Branch() {
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

    public double getMonthlySales() {
        return monthlySales;
    }

    public void setMonthlySales(double monthlySales) {
        this.monthlySales = monthlySales;
    }

    public double getDailySales() {
        return dailySales;
    }

    public void setDailySales(double dailySales) {
        this.dailySales = dailySales;
    }
}
