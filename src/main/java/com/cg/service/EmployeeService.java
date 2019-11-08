package com.cg.service;

import java.util.List;
import java.util.Map;

import com.cg.entity.Attendance;
import com.cg.entity.Employee;
import com.cg.entity.LossOfPay;

public interface EmployeeService {

	public Employee addEmployee(Employee e);

	public List<Employee> getEmployeeList();
	
	public Employee updateEmployee(int id,Employee e);
	
	public String deleteEmployee(int id);
	
	public Employee getEmployeeById(int id);

    public Attendance doAttendance(Attendance attendance);

    public List<Attendance> getAttendance(String atdDate);

    public Employee getEmpByAtd(int id);

    public Attendance findByEmpAndAttendanceDate(int id, String atdDate);

    public String applyLeave(int id, String fromDate, String toDate);

    public Boolean checkLeave(int id, String fromDate, String toDate);

    public Integer getNoOfLeavesOfYear(int id, int year);
    
    public Integer getNoOfLeaves(int id, int month);

    public LossOfPay submitLop(LossOfPay lossOfPay);

    public LossOfPay getLop(int id, int month);

	public String leaveCount(int id, int leaveCount);

	

}
