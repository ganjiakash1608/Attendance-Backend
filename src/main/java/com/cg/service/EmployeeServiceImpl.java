package com.cg.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Attendance;
import com.cg.entity.Employee;
import com.cg.entity.LossOfPay;
import com.cg.repo.AttendanceRepo;
import com.cg.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepo employeerepo;

	@Autowired
	AttendanceRepo attendanceRepo;

	@Override
	public Employee addEmployee(Employee e) {
		String str = e.getLastName() + "@123";
		String password = str.substring(0, 1).toUpperCase() + str.substring(1);
		e.setEmployeePassword(password);
		return employeerepo.save(e);
	}

	@Override
	public List<Employee> getEmployeeList() {

		return (List<Employee>) employeerepo.findAll();
	}

	@Override
	public Employee updateEmployee(int id, Employee e) {
		e.setId(id);
		return employeerepo.save(e);
	}

	@Override
	public String deleteEmployee(int id) {
		Employee e1 = employeerepo.findById(id).get();
		employeerepo.delete(e1);
		return ("Deleted");
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee e1 = employeerepo.findById(id).get();
		return e1;
	}

	@Override
	public Attendance doAttendance(Attendance attendance) {
		Employee emp = employeerepo.findById(attendance.getEmp().getId()).get();
		emp.addAttendance(attendance);
		employeerepo.save(emp);
		List<Attendance> list = emp.getAttendance();
		return list.get(list.size() - 1);
	}

	@Override
	public List<Attendance> getAttendance(String atdDate) {
		return attendanceRepo.findByAttendanceDate(atdDate);
	}

	@Override
	public Employee getEmpByAtd(int id) {
		Attendance atd = attendanceRepo.findById(id).get();
		return atd.getEmp();
	}

	@Override
	public Attendance findByEmpAndAttendanceDate(int id, String atdDate) {
		return attendanceRepo.findByEmpAndAttendanceDate(employeerepo.findById(id).get(), atdDate);
	}

	@Override
	public String applyLeave(int id, String fromDate, String toDate) {
		Employee emp = employeerepo.findById(id).get();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fDate = LocalDate.parse(fromDate, dtf);
		LocalDate nDate = LocalDate.parse(toDate, dtf);
		DateFormat formatter = new SimpleDateFormat("EEEE");
		String day;
		List<Attendance> attendancesList = emp.getAttendance();

		List<Attendance> remove = new ArrayList<Attendance>();

		for (LocalDate date = fDate; date.isBefore(nDate) || date.isEqual(nDate); date = date.plusDays(1)) {
			for (Attendance attendance : attendancesList) {
				if (attendance.getDate().equals(date.format(dtf)) && attendance.getPresent().equals("NO")) {
					remove.add(attendance);
				}
			}

			for (Attendance attendance : remove) {
				emp.removeAttendance(attendance);
			}

			day = formatter.format(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			if (!(day.equals("Sunday") || day.equals("Saturday"))) {
				Attendance attendance = new Attendance();
				attendance.setDate(date.format(dtf));
				attendance.setEmp(emp);
				attendance.setPresent("Leave");
				emp.addAttendance(attendance);
				employeerepo.save(emp);
			}
		}
		return "Record Added";
	}

	@Override
	public Boolean checkLeave(int id, String fromDate, String toDate) {
		Employee emp = employeerepo.findById(id).get();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fDate = LocalDate.parse(fromDate, dtf);
		LocalDate nDate = LocalDate.parse(toDate, dtf);
		DateFormat formatter = new SimpleDateFormat("EEEE");
		String day;

		List<Attendance> attendancesList = emp.getAttendance();
		for (LocalDate date = fDate; date.isBefore(nDate) || date.isEqual(nDate); date = date.plusDays(1)) {
			for (Attendance attendance : attendancesList) {
				if (attendance.getDate().equals(date.format(dtf)) && !attendance.getPresent().equals("NO")) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}

	@Override
	public Integer getNoOfLeavesOfYear(int id, int year) {
		int countYear = 0;
		Employee emp = employeerepo.findById(id).get();
		List<Attendance> attendancesList = emp.getAttendance();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		// int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = 01;
		int date = 01;
		while (month < 12) {
			calendar.set(year, month, date);
			int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			String mt = (month >= 0 && month < 9 ? "0" : "") + String.valueOf(month + 1);
			LocalDate fDate = LocalDate.parse(String.valueOf(year) + "-" + mt + "-0" + String.valueOf(date), dtf);
			LocalDate nDate = LocalDate.parse(String.valueOf(year) + "-" + mt + "-" + String.valueOf(maxDays), dtf);
			int count = 0;
			for (LocalDate date1 = fDate; date1.isBefore(nDate) || date1.isEqual(nDate); date1 = date1.plusDays(1)) {
				for (Attendance attendance : attendancesList) {
					if (attendance.getDate().equals(date1.format(dtf))
							&& (attendance.getPresent().equals("NO") || attendance.getPresent().equals("Leave"))) {
						count++;
					}
				}
			}
			countYear = countYear + count;
			month++;
		}
		return countYear;

	}

	@Override
	public Integer getNoOfLeaves(int id, int month) {
		Employee emp = employeerepo.findById(id).get();
		List<Attendance> attendancesList = emp.getAttendance();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int date = 01;
		calendar.set(year, month, date);
		int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		String mt = (month >= 0 && month < 9 ? "0" : "") + String.valueOf(month + 1);
		LocalDate fDate = LocalDate.parse(String.valueOf(year) + "-" + mt + "-0" + String.valueOf(date), dtf);
		LocalDate nDate = LocalDate.parse(String.valueOf(year) + "-" + mt + "-" + String.valueOf(maxDays), dtf);
		int count = 0;
		for (LocalDate date1 = fDate; date1.isBefore(nDate) || date1.isEqual(nDate); date1 = date1.plusDays(1)) {
			for (Attendance attendance : attendancesList) {
				if (attendance.getDate().equals(date1.format(dtf))
						&& (attendance.getPresent().equals("NO") || attendance.getPresent().equals("Leave"))) {
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public LossOfPay submitLop(LossOfPay lossOfPay) {
		Employee emp = employeerepo.findById(lossOfPay.getEmpFromLop().getId()).get();
		emp.addLop(lossOfPay);
		emp = employeerepo.save(emp);
		return emp.getLop().get(emp.getLop().size() - 1);
	}

	@Override
	public LossOfPay getLop(int id, int month) {
		Employee emp = employeerepo.findById(id).get();
		List<LossOfPay> lop = emp.getLop();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		for (LossOfPay lossOfPay : lop) {
			if (lossOfPay.getMonth() == month && lossOfPay.getYear() == year) {
				return lossOfPay;
			}
		}
		return null;
	}

	@Override
	public String leaveCount(int id, int leaveCount) {
		Employee emp = employeerepo.findById(id).get();
		List<Attendance> attendancesList = attendanceRepo.findByEmpAndPresent(emp,"NO");
		System.out.println(attendancesList.size());
		for (int i = 0; i < leaveCount; i++) {
			attendancesList.get(i).setPresent("YES");
		}
		attendanceRepo.saveAll(attendancesList);
		return "Success";
	}
	
	

}
