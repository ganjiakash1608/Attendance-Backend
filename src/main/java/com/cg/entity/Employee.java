package com.cg.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "employees")
@SequenceGenerator(name = "empseq", sequenceName = "emp_seq", initialValue = 101, allocationSize = 1)
public class Employee
{
    @Id
    @Column(name = "emp_id", length = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empseq")
    private int    id;

    private String firstName;

    private String lastName;

    private String employeeEmailId;

    private String employeeContact;

    private String employeeDesignation;

    private String employeeDOB;

    private String employeeGender;

    private String employeePassword;

    private int    salary;


    public int getSalary()
    {
        return salary;
    }


    public void setSalary(int salary)
    {
        this.salary = salary;
    }

    @JsonManagedReference(value = "emp-atd")
    @OneToMany(mappedBy = "emp", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendance> attendance = new ArrayList<Attendance>();

    @JsonManagedReference(value = "emp-lop")
    @OneToMany(mappedBy = "empFromLop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LossOfPay>  lop        = new ArrayList<LossOfPay>();


    public List<LossOfPay> getLop()
    {
        return lop;
    }


    public void setLop(List<LossOfPay> lop)
    {
        this.lop = lop;
    }


    public void addLop(LossOfPay lossOfPay)
    {
        getLop().add(lossOfPay);
    }


    public List<Attendance> getAttendance()
    {
        return attendance;
    }


    public void setAttendance(List<Attendance> attendance)
    {
        this.attendance = attendance;
    }


    public void addAttendance(Attendance attendance)
    {
        getAttendance().add(attendance);
    }


    public void removeAttendance(Attendance attendance)
    {
        getAttendance().remove(attendance);
    }


    public String getEmployeePassword()
    {
        return employeePassword;
    }


    public void setEmployeePassword(String employeePassword)
    {
        this.employeePassword = employeePassword;
    }


    public String getEmployeeEmailId()
    {
        return employeeEmailId;
    }


    public void setEmployeeEmailId(String employeeEmailId)
    {
        this.employeeEmailId = employeeEmailId;
    }


    public String getEmployeeContact()
    {
        return employeeContact;
    }


    public void setEmployeeContact(String employeeContact)
    {
        this.employeeContact = employeeContact;
    }


    public String getEmployeeDesignation()
    {
        return employeeDesignation;
    }


    public void setEmployeeDesignation(String employeeDesignation)
    {
        this.employeeDesignation = employeeDesignation;
    }


    public String getEmployeeDOB()
    {
        return employeeDOB;
    }


    public void setEmployeeDOB(String employeeDOB)
    {
        this.employeeDOB = employeeDOB;
    }


    public String getEmployeeGender()
    {
        return employeeGender;
    }


    public void setEmployeeGender(String employeeGender)
    {
        this.employeeGender = employeeGender;
    }


    public int getId()
    {
        return id;
    }


    public void setId(int id)
    {
        this.id = id;
    }


    public String getFirstName()
    {
        return firstName;
    }


    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }


    public String getLastName()
    {
        return lastName;
    }


    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

}
