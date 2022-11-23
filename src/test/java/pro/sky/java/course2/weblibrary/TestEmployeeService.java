package pro.sky.java.course2.weblibrary;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.weblibrary.model.Employee;
import pro.sky.java.course2.weblibrary.record.EmployeeRequest;
import pro.sky.java.course2.weblibrary.service.EmployeeService;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEmployeeService {
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        this.employeeService = new EmployeeService();
        Stream.of(new EmployeeRequest("TestOne", "TestOne", 1, 1000),
                        new EmployeeRequest("TestTwo", "TestTwo", 1, 2000),
                        new EmployeeRequest("TestThree", "TestThree", 1, 7000),
                        new EmployeeRequest("TestFour", "TestFour", 2, 10200),
                        new EmployeeRequest("TestFive", "TestFive", 2, 100000))
                .forEach(employeeService::addEmployee);
    }

    @Test
    public void addEmployee() {
        EmployeeRequest expected = new EmployeeRequest("Ivanov", "Ivan", 3, 100000);
        Employee result = employeeService.addEmployee(expected);
        assertEquals(expected.getLastName(),result.getLastName());
        assertEquals(expected.getFirstName(),result.getFirstName());
        assertEquals(expected.getDepartment(),result.getDepartment());
        assertEquals(expected.getSalary(),result.getSalary());

        org.assertj.core.api.Assertions.assertThat(employeeService.getAllEmployees()).contains(result);
    }
    @Test
    public void listEmployees(){
        Collection<Employee> employees = employeeService.getAllEmployees();
        org.assertj.core.api.Assertions.assertThat(employees).first().extracting(Employee::getLastName).isEqualTo("TestOne");
        org.assertj.core.api.Assertions.assertThat(employees).first().extracting(Employee::getFirstName).isEqualTo("TestOne");
    }
    @Test
    public void sumOfSalaries(){
        int sum = employeeService.getSalarySum();
        org.assertj.core.api.Assertions.assertThat(sum).isEqualTo(120_200);
    }
    @Test
    public void employeeWithMaxSum(){
        Employee employee = employeeService.getEmployeeMaxSum();
        org.assertj.core.api.Assertions.assertThat(employee).isNotNull().extracting(Employee::getLastName).isEqualTo("TestFive");
    }
    @Test
    public void employeeWithMinSum(){
        Employee employee = employeeService.getEmployeeMinSum();
        org.assertj.core.api.Assertions.assertThat(employee).isNotNull().extracting(Employee::getLastName).isEqualTo("TestOne");
    }
    @Test
    public void removeEmployee() {
        employeeService.removeEmployee(1);
        Collection<Employee> employees = employeeService.getAllEmployees();
        org.assertj.core.api.Assertions.assertThat(employees).hasSize(4);
    }
    @Test
    public void addEmployeeException(){
        assertThrows(IllegalArgumentException.class, ()->employeeService.addEmployee(new EmployeeRequest(null, "dwqwd", 1, 1000)));

    }
    @Test
    public void EmployeeSalaryMoreAverageSum(){
        Set<Employee> employees = employeeService.getEmployeeSalaryMoreAverageSum();
        Set<Employee> expected = Set.of(new Employee("TestFive", "TestFive", 2, 100000));
Assertions.assertEquals(employees, expected);
    }
}
