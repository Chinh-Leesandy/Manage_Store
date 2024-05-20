package com.example.ManageStore.Junit.Controller;

import com.example.ManageStore.Controller.EmployeeController;
import com.example.ManageStore.DAO.EmployeeDAO;
import com.example.ManageStore.Model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.BindingResult;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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

    @Autowired
    private ObjectMapper objectMapper;
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
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.ok().body(employee));
        ResponseEntity<?> responseEntity = controller.postEmployee(employee, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testAddEmployeeErrorSDT () throws Exception{
        Employee invalidEmployee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidEmployee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testAddEmployeeErrorName () throws Exception{
        Employee invalidEmployee = new Employee("", "0948266581","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidEmployee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void testAddEmployeeErrorEmail () throws Exception{
        Employee invalidEmployee = new Employee("Nguyễn Quang", "0948266581","", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidEmployee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    // Viết tiếp cho các trường hợp trống trường tiếp theo
//    @Test
//    public void testAddEmployeeErrorDiaChi () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "0948266581","quang@gmail.com", "", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeErrorChucVu () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "0948266581","quang@gmail.com", "Thanh Trì - Hà Nội", "", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeErrorUsername () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "0948266581","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeErrorPassword () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
    @Test
    public void testAddEmployeeError () throws Exception{
        Employee employee = new Employee("", "","", "", "", "", "", null);
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void testAddEmployeeErrorTime () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", null);
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    // Chinh edit như phần add
    @Test
    public void testPutEmployee() throws Exception{
        Employee employee = new Employee(3, "Nguyễn Quang", "0948256153","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-15"));
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.ok().body(employee));
        ResponseEntity<?> responseEntity = controller.putEmployee(employee, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testDeleteEmployee() throws Exception {
        when(employeeDAO.deleteEmployee(3)).thenReturn(ResponseEntity.ok().body("Employee deleted successfully"));
        ResponseEntity<?> responseEntity = controller.deleteEmployee("3");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
//    @Test
//    public void testEditEmployeeErrorSDT () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//
//    @Test
//    public void testEditEmployeeErrorName () throws Exception{
//        Employee employee = new Employee(3,"", "0948266581","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeErrorEmail () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "0948266581","", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeErrorDiaChi () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "0948266581","quang@gmail.com", "", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeErrorChucVu () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "0948266581","quang@gmail.com", "Thanh Trì - Hà Nội", "", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeErrorUsername () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "0948266581","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeErrorPassword () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeError () throws Exception{
//        Employee employee = new Employee(3,"", "","", "", "", "", "", null);
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeErrorTime () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", null);
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
    @Test
    public void testAddEmployeeValidationSDT1 () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "0123456","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].defaultMessage").value("Số điện thoại không hợp lệ."));
    }
    //tương tự cho các cái sau.
//
//    @Test
//    public void testAddEmployeeValidationSDT2 () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeValidationSDT3 () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeValidationSDT4 () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeValidationEmail () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "0948266581","", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeValidationChucVu () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "0948266581","quang@gmail.com", "Thanh Trì - Hà Nội", "", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeValidationName () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "0948266581","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeValidationPassword () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeValidationPassword1 () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeValidationPassword2 () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeValidationPassword3 () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }@Test
//    public void testAddEmployeeValidationPassword4 () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeValidationPassword5 () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeValidationPassword6 () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeValidationPassword7 () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeValidationPassword8 () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeValidationPassword9 () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeValidationPassword10 () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeValidationPassword11 () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeValidationPassword12 () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeValidationPassword13 () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testAddEmployeeValidationPassword14 () throws Exception{
//        Employee employee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.insertEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.postEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationSDT1 () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationSDT2 () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationSDT3 () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationSDT4 () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//
//    @Test
//    public void testEditEmployeeValidationName () throws Exception{
//        Employee employee = new Employee(3,"", "0948266581","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationEmail () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "0948266581","", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationChucVu () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "0948266581","quang@gmail.com", "Thanh Trì - Hà Nội", "", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationPassword () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationPassword1 () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationPassword2 () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationPassword3 () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationPassword4 () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationPassword5 () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationPassword6 () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationPassword7 () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationPassword8 () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationPassword9 () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationPassword10 () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationPassword11 () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationPassword15 () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationPassword12 () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationPassword13 () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//    @Test
//    public void testEditEmployeeValidationPassword14 () throws Exception{
//        Employee employee = new Employee(3,"Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
//        when(employeeDAO.updateEmployee(employee)).thenReturn(ResponseEntity.internalServerError().build());
//        ResponseEntity<?> responseEntity = controller.putEmployee(employee);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
}
