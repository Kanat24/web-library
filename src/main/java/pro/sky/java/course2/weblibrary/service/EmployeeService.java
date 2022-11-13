package pro.sky.java.course2.weblibrary.service;

import org.apache.logging.log4j.util.PropertySource;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.weblibrary.model.Employee;
import pro.sky.java.course2.weblibrary.record.EmployeeRequest;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();


    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getLastName()==null || employeeRequest.getFirstName()==null){
            throw new IllegalArgumentException("Некорректный ввод");
        }
        Employee employee = new Employee(employeeRequest.getFirstName(), employeeRequest.getLastName(),
                employeeRequest.getDepartament(), employeeRequest.getSalary());
        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum() {
        return employees.values().stream().mapToInt(Employee::getSalary).sum();
    }

    public Employee getEmployeeMinSum() {
        return employees.values().stream().filter(Objects::nonNull).filter(s->s.getSalary()!=0)
                .min(Comparator.comparingInt(Employee::getSalary)).get();
    }

    public Employee getEmployeeMaxSum() {
        return  employees.values().stream().filter(Objects::nonNull).filter(s->s.getSalary()!=0)
                .max(Comparator.comparingInt(Employee::getSalary)).get();
    }

    public Set<Employee> getEmployeeSalaryMoreAverageSum() {
        if (employees.size() == 0){
            throw new RuntimeException("В списке сотрудников нет ниодного сотрудника");
        }
        int averageSum = getSalarySum()/employees.size();
        return employees.values().stream().filter(s->s.getSalary()>averageSum).collect(Collectors.toSet());
    }
}