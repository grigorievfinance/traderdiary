package com.grigorievfinance.traderdiary.model;

import org.springframework.util.Assert;

import javax.persistence.*;

import static com.grigorievfinance.traderdiary.util.Util.getEffectiveClass;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity {
    public static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer id;

    public AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public int id() {
        Assert.notNull(id, "Entity must have id");
        return id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getEffectiveClass(this) != getEffectiveClass(o)) return false;
        return getId() != null && getId().equals(((AbstractBaseEntity) o).getId());
    }

    @Override
    public final int hashCode() {
        return getEffectiveClass(this).hashCode();
    }
}
