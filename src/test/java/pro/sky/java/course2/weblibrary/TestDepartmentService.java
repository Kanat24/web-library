package pro.sky.java.course2.weblibrary;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.weblibrary.model.Employee;
import pro.sky.java.course2.weblibrary.record.EmployeeRequest;
import pro.sky.java.course2.weblibrary.service.DepartmentService;
import pro.sky.java.course2.weblibrary.service.EmployeeService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestDepartmentService {
    private final List<Employee> employees = List.of(new Employee("TestOne", "TestOne", 1, 1000),
            new Employee("TestTwo", "TestTwo", 1, 2000),
            new Employee("TestThree", "TestThree", 1, 7000),
            new Employee("TestFour", "TestFour", 2, 10200),
            new Employee("TestFive", "TestFive", 2, 100000));
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        when(employeeService.getAllEmployees()).thenReturn(employees);
    }

    @Test
    void getEmployeesByDepartment() {
        Collection<Employee> employeesList = departmentService.getDepartmentEmployees(1);
        Assertions.assertThat(employeesList).hasSize(3).contains(employees.get(0), employees.get(1), employees.get(2));
    }

    @Test
    void getSumByDepartment() {
        int sum = departmentService.getSumOfDepartment(1);
        Assertions.assertThat(sum).isEqualTo(10000);
    }

    @Test
    void maxSalaryInDepartment() {
        int max = departmentService.getMaxSalaryByDepartment(1);
        Assertions.assertThat(max).isEqualTo(7000);

    }

    @Test
    void minSalaryInDepartment() {
int min = departmentService.getMinSalaryByDepartment(1);
    Assertions.assertThat(min).isEqualTo(1000);
    }
    @Test
    void EmployeesGroupedByDepartment(){
        Map<Integer, List<Employee>> groupedEmployees= departmentService.getEmployeesGroupedByDepartment();
        Assertions.assertThat(groupedEmployees).hasSize(2).containsEntry(1, List.of(employees.get(0),
                employees.get(1), employees.get(2))).containsEntry(2, List.of(employees.get(3), employees.get(4)));
    }
}
