package com.cg.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
@SequenceGenerator(name = "adminseq", sequenceName = "admin_seq", initialValue = 101, allocationSize = 1)
public class Admin
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adminseq")
    private int    id;

    private String adminEmailId;

    private String password;


    public int getId()
    {
        return id;
    }


    public void setId(int id)
    {
        this.id = id;
    }


    public String getAdminEmailId()
    {
        return adminEmailId;
    }


    public void setAdminEmailId(String adminEmailId)
    {
        this.adminEmailId = adminEmailId;
    }


    public String getPassword()
    {
        return password;
    }


    public void setPassword(String password)
    {
        this.password = password;
    }

}
