package com.cg.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cg.entity.Attendance;
import com.cg.entity.Employee;

public interface AttendanceRepo extends CrudRepository<Attendance, Integer>
{

    List<Attendance> findByAttendanceDate(String atdDate);
    
    Attendance findByEmpAndAttendanceDate(Employee emp , String atdDate);
    
    List<Attendance> findByEmpAndPresent(Employee emp,String present);
}
