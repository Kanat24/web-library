package pro.sky.java.course2.weblibrary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.weblibrary.model.Employee;
import pro.sky.java.course2.weblibrary.record.EmployeeRequest;
import pro.sky.java.course2.weblibrary.service.EmployeeService;

import java.util.Collection;
import java.util.Map;
import java.util.Set;


@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees(){
        return this.employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest){
       return this.employeeService.addEmployee(employeeRequest);
    }
    @GetMapping("/employees/salary/sum")
    public int getSalarySum(){
       return this.employeeService.getSalarySum();
    }
    @GetMapping("/employee/salary/min")
    public Employee getEmployeeMinSum() {

        return this.employeeService.getEmployeeMinSum();
    }
    @GetMapping("/employee/salary/max")
    public Employee getEmployeeMaxSum(){
        return this.employeeService.getEmployeeMaxSum();
    }
    @GetMapping("/employee/high-salary")
    public Set<Employee> getEmployeeSalaryMoreAverageSum(){
        return this.employeeService.getEmployeeSalaryMoreAverageSum();
    }
}
