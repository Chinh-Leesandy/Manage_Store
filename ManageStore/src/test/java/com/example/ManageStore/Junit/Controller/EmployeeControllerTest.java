package com.example.ManageStore.Junit.Controller;

import com.example.ManageStore.Controller.EmployeeController;
import com.example.ManageStore.DAO.EmployeeDAO;
import com.example.ManageStore.Model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@WebMvcTest(EmployeeController.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private EmployeeDAO employeeDAO;

    @InjectMocks
    private EmployeeController controller;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testAllEmployee() throws Exception{
        List<Employee> list = Arrays.asList(
                new Employee(1, "Nguyễn Chinh" ,"0948266581", "chinh@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Chinh", "Chinh@123", Date.valueOf("2023-03-01")),
                new Employee(2, "Nguyễn Cường" ,"0315914471", "cuonng@gmail.com", "Hà Đông - Hà Nội", "Quản lý nhân viên", "Cuong", "Cuong@234", Date.valueOf("2023-04-01"))
        );
        when(employeeDAO.selectAllEmployee()).thenReturn(ResponseEntity.ok().body(list));
        ResponseEntity<?> responseEntity = controller.getEmployee();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testEmployeeByID() throws Exception{
        Employee employee = new Employee(1, "Nguyễn Chinh" ,"0948266581", "chinh@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Chinh", "Chinh@123", Date.valueOf("2023-03-01"));
        when(employeeDAO.selectEmployee(1)).thenReturn(ResponseEntity.ok().body(employee));
        ResponseEntity<?> responseEntity = controller.getEmployeeByID("1");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testPostEmployee() throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "0948256153","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.ok().body(employee));
        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testAddEmployeeErrorSDT () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void testAddEmployeeErrorName () throws Exception{
        Employee employee = new Employee("", "0948266581","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddEmployeeErrorEmail () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "0948266581","", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddEmployeeErrorDiaChi () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "0948266581","quang@gmail.com", "", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddEmployeeErrorChucVu () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "0948266581","quang@gmail.com", "Thanh Trì - Hà Nội", "", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddEmployeeErrorUsername () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "0948266581","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "", "Quang@256", Date.valueOf("2024-05-01"));
        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddEmployeeErrorPassword () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddEmployeeError () throws Exception{
        Employee employee = new Employee("", "","", "", "", "", "", null);
        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddEmployeeErrorTime () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", null);
        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testPutEmployee() throws Exception{
        Employee employee = new Employee(3, "Nguyễn Quang", "0948256153","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-15"));
        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.ok().body(employee));
        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testDeleteEmployee() throws Exception {
        when(employeeDAO.deleteEmployee(3)).thenReturn(ResponseEntity.ok().body("Employee deleted successfully"));
        ResponseEntity<?> responseEntity = controller.deleteEmployee("3");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testEditEmployeeErrorSDT () throws Exception{
        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void testEditEmployeeErrorName () throws Exception{
        Employee employee = new Employee(3,"", "0948266581","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditEmployeeErrorEmail () throws Exception{
        Employee employee = new Employee(3,"Nguyễn Quang", "0948266581","", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditEmployeeErrorDiaChi () throws Exception{
        Employee employee = new Employee(3,"Nguyễn Quang", "0948266581","quang@gmail.com", "", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditEmployeeErrorChucVu () throws Exception{
        Employee employee = new Employee(3,"Nguyễn Quang", "0948266581","quang@gmail.com", "Thanh Trì - Hà Nội", "", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditEmployeeErrorUsername () throws Exception{
        Employee employee = new Employee(3,"Nguyễn Quang", "0948266581","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "", "Quang@256", Date.valueOf("2024-05-01"));
        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditEmployeeErrorPassword () throws Exception{
        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditEmployeeError () throws Exception{
        Employee employee = new Employee(3,"", "","", "", "", "", "", null);
        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditEmployeeErrorTime () throws Exception{
        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", null);
        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}
