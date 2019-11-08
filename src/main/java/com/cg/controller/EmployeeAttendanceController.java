package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Admin;
import com.cg.entity.Attendance;
import com.cg.entity.Employee;
import com.cg.entity.LossOfPay;
import com.cg.service.AdminService;
import com.cg.service.EmployeeService;

//@CrossOrigin("http://localhost:4200")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class EmployeeAttendanceController
{

    @Autowired
    AdminService    adminservice;

    @Autowired
    EmployeeService employeeservice;


    @GetMapping(path = "/getAdmin", produces = "application/json")
    public ResponseEntity<List<Admin>> getAdminList()
    {
        return new ResponseEntity<List<Admin>>((List<Admin>) adminservice.getAdminList(), HttpStatus.OK);
    }


    @GetMapping(path = "/getEmployee", produces = "application/json")
    public ResponseEntity<List<Employee>> getEmployeeList()
    {
        return new ResponseEntity<List<Employee>>((List<Employee>) employeeservice.getEmployeeList(), HttpStatus.OK);
    }


    @PostMapping(path = "/addEmployee", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee e)
    {
        return new ResponseEntity<Employee>(employeeservice.addEmployee(e), HttpStatus.OK);
    }


    @PutMapping(path = "/updateEmployee/{id}", consumes = "application/json")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, @RequestBody Employee e)
    {
        return new ResponseEntity<Employee>(employeeservice.updateEmployee(id, e), HttpStatus.OK);

    }


    @DeleteMapping(path = "/deleteEmployee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id)
    {
        return new ResponseEntity<String>(employeeservice.deleteEmployee(id), HttpStatus.OK);

    }


    @GetMapping(path = "/getEmployeeById/{id}", produces = "application/json")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id)
    {
        return new ResponseEntity<Employee>(employeeservice.getEmployeeById(id), HttpStatus.OK);
    }


    @PostMapping(path = "/doAttendance")
    public ResponseEntity<Attendance> doAttendance(@RequestBody Attendance attendance)
    {
        return new ResponseEntity<Attendance>(employeeservice.doAttendance(attendance), HttpStatus.OK);
    }


    @GetMapping(path = "/getAttendance/{date}", produces = "application/json")
    public ResponseEntity<List<Attendance>> getAttendance(@PathVariable("date") String atdDate)
    {
        return new ResponseEntity<List<Attendance>>(employeeservice.getAttendance(atdDate), HttpStatus.OK);
    }


    @GetMapping(path = "/getEmpByAtd/{id}", produces = "application/json")
    public ResponseEntity<Employee> getEmpByAtd(@PathVariable("id") int id)
    {
        return new ResponseEntity<Employee>(employeeservice.getEmpByAtd(id), HttpStatus.OK);
    }


    @GetMapping(path = "/getMappingForEmp/{id}/{date}", produces = "application/json")
    public ResponseEntity<Attendance> getAtdForEmp(@PathVariable("date") String atdDate, @PathVariable("id") int id)
    {
        return new ResponseEntity<Attendance>(employeeservice.findByEmpAndAttendanceDate(id, atdDate), HttpStatus.OK);
    }


    @GetMapping(path = "/applyLeave/{id}/{fromDate}/{toDate}", produces = "application/json")
    public ResponseEntity<String> applyLeave(@PathVariable("fromDate") String fromDate,
            @PathVariable("toDate") String toDate, @PathVariable("id") int id)
    {
        return new ResponseEntity<String>(employeeservice.applyLeave(id, fromDate, toDate), HttpStatus.OK);
    }


    @GetMapping(path = "/checkLeave/{id}/{fromDate}/{toDate}", produces = "application/json")
    public ResponseEntity<Boolean> checkLeave(@PathVariable("fromDate") String fromDate,
            @PathVariable("toDate") String toDate, @PathVariable("id") int id)
    {
        return new ResponseEntity<Boolean>(employeeservice.checkLeave(id, fromDate, toDate), HttpStatus.OK);
    }


    @GetMapping(path = "/checkLeaveOfYear/{id}/{year}", produces = "application/json")
    public ResponseEntity<Integer> getNoOfLeavesOfYear(@PathVariable("id") int id, @PathVariable("year") int year)
    {
        return new ResponseEntity<Integer>(employeeservice.getNoOfLeavesOfYear(id, year), HttpStatus.OK);
    }

    @GetMapping(path = "/checkLeave/{id}/{month}", produces = "application/json")
    public ResponseEntity<Integer> getNoOfLeaves(@PathVariable("id") int id, @PathVariable("month") int month)
    {
        return new ResponseEntity<Integer>(employeeservice.getNoOfLeaves(id, month), HttpStatus.OK);
    }
    
    @GetMapping(path = "/leavecount/{id}/{leaveCount}")
    public ResponseEntity<?> leaveCount(@PathVariable("id") int id,@PathVariable("leaveCount") int leaveCount)
    {
        return new ResponseEntity<>(employeeservice.leaveCount(id,leaveCount), HttpStatus.OK);
    }

    @PostMapping(path = "/submitLop")
    public ResponseEntity<LossOfPay> submitLop(@RequestBody LossOfPay lossOfPay)
    {
        return new ResponseEntity<LossOfPay>(employeeservice.submitLop(lossOfPay), HttpStatus.OK);
    }


    @GetMapping(path = "/getLop/{id}/{month}")
    public ResponseEntity<LossOfPay> getLop(@PathVariable("id") int id, @PathVariable("month") int month)
    {
        return new ResponseEntity<LossOfPay>(employeeservice.getLop(id, month), HttpStatus.OK);
    }
}
