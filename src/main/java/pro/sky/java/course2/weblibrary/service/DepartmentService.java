package pro.sky.java.course2.weblibrary.service;

import pro.sky.java.course2.weblibrary.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    public Collection<Employee> getDepartmentEmployees(int department){
        return employeeService.getAllEmployees().stream().filter(e->e.getDepartment()==department)
                .collect(Collectors.toList());
    }
    public int getSumOfDepartment (int department){
        return employeeService.getAllEmployees().stream().filter(e->e.getDepartment()==department)
                .mapToInt(Employee::getSalary).sum();

    }
    public int getMaxSalaryByDepartment(int department){
        return employeeService.getAllEmployees().stream().filter(e->e.getDepartment()==department)
                .mapToInt(Employee::getSalary).max().orElseThrow(IllegalArgumentException::new);
    }
    public int getMinSalaryByDepartment(int department) {
        return employeeService.getAllEmployees().stream().filter(e->e.getDepartment()==department)
                .mapToInt(Employee::getSalary).min().orElseThrow(IllegalArgumentException::new);
    }
    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment(){
        return employeeService.getAllEmployees().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
