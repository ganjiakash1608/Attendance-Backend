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
@Table(name = "emp_attendance")
@SequenceGenerator(name = "atdseq", sequenceName = "atd_seq", initialValue = 101, allocationSize = 1)
public class Attendance
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atdseq")
    private int      id;

    private String   present;

    private String   attendanceDate;

    @JsonBackReference(value = "emp-atd")
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee emp;


    public int getId()
    {
        return id;
    }


    public void setId(int id)
    {
        this.id = id;
    }


    public String getPresent()
    {
        return present;
    }


    public void setPresent(String present)
    {
        this.present = present;
    }


    public void setPresent(Boolean present)
    {
        this.present = present ? "YES" : "NO";
    }


    public String getDate()
    {
        return attendanceDate;
    }


    public void setDate(String date)
    {
        this.attendanceDate = date;
    }


    public Employee getEmp()
    {
        return emp;
    }


    public void setEmp(Employee emp)
    {
        this.emp = emp;
    }


    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Attendance other = (Attendance) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
