package com.grigorievfinance.traderdiary.model;

import com.grigorievfinance.traderdiary.util.PositionUtil;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class User extends AbstractNamedEntity {
    private String email;
    private String password;
    private boolean enabled;
    private Date registered = new Date();
    private Set<Role> roles;
    private double balance;

    public User() {
    }



    public User(User u) {
        this(u.id, u.name, u.email, u.password, u.enabled, u.registered, u.roles, u.balance);
    }

    public User(Integer id, String name, String email, String password, Role... roles) {
        this(id, name, email, password, true, new Date(), Arrays.asList(roles), PositionUtil.MAX_LOSS);
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Date registered, Collection<Role> roles, double balance) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.balance = balance;
        this.registered = registered;
        setRoles(roles);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                ", balance=" + balance +
                '}';
    }
}
