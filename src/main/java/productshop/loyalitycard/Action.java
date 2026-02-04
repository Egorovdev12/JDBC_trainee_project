package productshop.loyalitycard;

import productshop.entity.category.Category;

import java.time.LocalDate;

public class Action {

    private double currentDiscount;
    private Category category;
    private LocalDate startDate;
    private LocalDate endDate;

    public Action(double currentDiscount, Category category, LocalDate startDate, LocalDate endDate) {
        this.currentDiscount = currentDiscount;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Action{" +
                "currentDiscount=" + currentDiscount +
                ", category=" + category +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public Category getCategory() {
        return category;
    }

    public double getCurrentDiscount() {
        return currentDiscount;
    }
}
