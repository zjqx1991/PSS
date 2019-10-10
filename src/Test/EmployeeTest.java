import com.revanwang.utils.RevanMapUtils;
import com.revanwang.wms.domain.Department;
import com.revanwang.wms.domain.Employee;
import com.revanwang.wms.query.EmployeeQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IDepartmentService;
import com.revanwang.wms.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeTest {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IDepartmentService departmentService;

    @Test
    public void saveTest() {
        for (int i = 0; i < 20; i++) {
            Department depart = this.departmentService.get(1L);
            Employee emp = new Employee();
            emp.setName("test__" + i);
            emp.setAge(50 + i);
            emp.setEmail("test_" + i + "@163.com");
            emp.setPassword("test_" + i);
            emp.setDepartment(depart);

            this.employeeService.save(emp);
        }
    }


    @Test
    public void deleteTest() {
        this.employeeService.delete(5L);
    }

    @Test
    public void updateTest() {
        Department depart = this.departmentService.get(3L);
        Employee emp = new Employee();
        emp.setId(4L);
        emp.setName("段誉");
        emp.setAge(85);
        emp.setEmail("duanyu@163.com");
        emp.setPassword("duanyu");
        emp.setDepartment(depart);
        this.employeeService.update(emp);
    }

    @Test
    public void getDepartTest() {
        Employee emp = this.employeeService.get(2L);
        System.out.println("EmployeeTest.getDepartTest: " + emp);
    }

    @Test
    public void getListTest() {
        List<Employee> list = this.employeeService.getList();
        for (Employee emp:list) {
            System.out.println("EmployeeTest.getListTest：" + emp);
        }
    }


    @Test
    public void queryTest() {
        EmployeeQueryObject qo = new EmployeeQueryObject();
//        qo.setDepartId(4L);
//        qo.setKeyword("xuzhu");
        System.out.println("EmployeeTest.queryTest:==" + qo);
        QueryResultObject resultObject = this.employeeService.query(qo);

        for (Employee emp : resultObject.getResultList()) {
            System.out.println("EmployeeTest.queryTest:==" + emp);
        }
    }


    @Test
    public void queryListTest() {
        List<Employee> list = this.employeeService.query(1, 30,
                "(obj.name LIKE :name or obj.email LIKE :email) and obj.department.id = :departId",
                RevanMapUtils.revan_createMapObject("name", "%test%"),
                RevanMapUtils.revan_createMapObject("email"," %test%"),
                RevanMapUtils.revan_createMapObject("departId", 1L));
        System.out.println("EmployeeTest.queryListTest:======" + list.size());
    }


    @Test
    public void queryList1Test() {
        List<Employee> list = this.employeeService.query("(obj.name LIKE :name or obj.email LIKE :email)",
                RevanMapUtils.revan_createMapObject("name", "%test%"),
                RevanMapUtils.revan_createMapObject("email"," %test%"));
        System.out.println("EmployeeTest.queryListTest:======" + list.size());
    }


    @Test
    public void queryObjectTestt() {
        Employee employee = this.employeeService.queryObject("obj.id = :empId",
                RevanMapUtils.revan_createMapObject("empId",1L));
        System.out.println("EmployeeTest.queryObjectTestt:=== " + employee);
    }


    @Test
    public void deleteBatch() {
        this.employeeService.deleteBatch(Arrays.asList(25L, 24L, 23L));
    }
}
