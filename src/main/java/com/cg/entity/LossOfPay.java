package com.cg.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "LOP")
@SequenceGenerator(name = "lopseq", sequenceName = "lop_seq", initialValue = 101, allocationSize = 1)
public class LossOfPay
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lopseq")
    private int id;
    
    private int month;
    
    private int year;
    
    private int lop;
    
    private int totalDays;
    
    @JsonBackReference(value = "emp-lop")
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee empFromLop;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getMonth()
    {
        return month;
    }

    public void setMonth(int month)
    {
        this.month = month;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public Employee getEmpFromLop()
    {
        return empFromLop;
    }

    public void setEmpFromLop(Employee empFromLop)
    {
        this.empFromLop = empFromLop;
    }

    public int getLop()
    {
        return lop;
    }

    public void setLop(int lop)
    {
        this.lop = lop;
    }

    public int getTotalDays()
    {
        return totalDays;
    }

    public void setTotalDays(int totalDays)
    {
        this.totalDays = totalDays;
    }
    
}
