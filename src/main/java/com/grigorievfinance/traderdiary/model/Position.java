package com.grigorievfinance.traderdiary.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries({
        @NamedQuery(name = Position.ALL_SORTED, query = "SELECT p FROM Position p WHERE p.user.id=:userId ORDER BY p.dateTime DESC"),
        @NamedQuery(name = Position.DELETE, query = "DELETE FROM Position p WHERE p.id=:id AND p.user.id=:userId"),
        @NamedQuery(name = Position.GET_BETWEEN, query = "SELECT p FROM Position p WHERE p.user.id=:userId AND p.dateTime >= :startDateTime and p.dateTime < :endDateTime ORDER BY p.dateTime DESC"),
})

@Entity
@Table(name = "position", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "position_unique_user_datetime_idx")})
public class Position extends AbstractBaseEntity {

    public static final String ALL_SORTED = "Position.getAll";
    public static final String DELETE = "Position.delete";
    public static final String GET_BETWEEN = "Position.getBetween";

    @Column(name = "date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "symbol", nullable = false)
    @NotBlank
    @Size(min = 5, max = 20)
    private String symbol;

    @Column(name = "profit", nullable = false)
    @Range(min = -10, max = 10)
    private double profitLoss;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    public Position() {
    }

    public Position(LocalDateTime dateTime, String symbol, double profitLoss) {
        this(null, dateTime, symbol, profitLoss);
    }

    public Position(Integer id, LocalDateTime dateTime, String symbol, double profitLoss) {
        super(id);
        this.dateTime = dateTime;
        this.symbol = symbol;
        this.profitLoss = profitLoss;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getProfitLoss() {
        return profitLoss;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setProfitLoss(double profitLoss) {
        this.profitLoss = profitLoss;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", symbol='" + symbol + '\'' +
                ", profitLoss=" + profitLoss +
                '}';
    }
}
