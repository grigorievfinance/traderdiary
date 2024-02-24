package com.grigorievfinance.traderdiary.model;

import com.grigorievfinance.traderdiary.util.PositionUtil;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

public class User extends AbstractNamedEntity {
    private String email;
    private String password;
    private boolean enabled;
    private Date registered = new Date();
    private Set<Role> roles;
    private double balance;

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, true, EnumSet.of(role, roles), PositionUtil.MAX_LOSS);
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Set<Role> roles, double balance) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.balance = balance;
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

    public void setRoles(Set<Role> roles) {
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
