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
    @Test
    public void testAddEmployeeEmptyAll () throws Exception{
        Employee employee = new Employee("", "","", "", "", "", "", null);
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void testAddEmployeeEmptyName () throws Exception{
        Employee invalidEmployee = new Employee("", "0948266581","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidEmployee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void testAddEmployeeEmptySDT () throws Exception{
        Employee invalidEmployee = new Employee("Nguyễn Quang", "","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidEmployee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void testAddEmployeeEmptyEmail () throws Exception{
        Employee invalidEmployee = new Employee("Nguyễn Quang", "0948266581","", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidEmployee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void testAddEmployeeEmptyDiaChi () throws Exception{
        Employee invalidEmployee = new Employee("Nguyễn Quang", "0948266581","Quangnp@gmail.com", "", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidEmployee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testAddEmployeeEmptyChucVu () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "0948256153","quang@gmail.com", "Thanh Trì - Hà Nội", "", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void testAddEmployeeEmptyUsername () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "0948256153","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void testAddEmployeeEmptyPassword () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "0948256153","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void testAddEmployeeErrorTime () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "0948256153","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", null);
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void testAddEmployeeValidateNameNumber () throws Exception{
        Employee employee = new Employee("123", "0948256153","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'hoten')].defaultMessage").value("Tên nhân viên chỉ được chứa chữ cái và khoảng trắng."));
    }
    @Test
    public void testAddEmployeeValidateNameNumberChar () throws Exception{
        Employee employee = new Employee("abc123", "0948256153","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'hoten')].defaultMessage").value("Tên nhân viên chỉ được chứa chữ cái và khoảng trắng."));
    }
    @Test
    public void testAddEmployeeValidateNameSpecialCharacters () throws Exception{
        Employee employee = new Employee("@abc", "0948256153","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'hoten')].defaultMessage").value("Tên nhân viên chỉ được chứa chữ cái và khoảng trắng."));
    }

    @Test
    public void testAddEmployeeValidateSDTChar () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "abc","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'sdt')].defaultMessage").value("Số điện thoại không hợp lệ."));
    }
    @Test
    public void testAddEmployeeValidateSDTCharNumber () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "abc1234567","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'sdt')].defaultMessage").value("Số điện thoại không hợp lệ."));
    }
    @Test
    public void testAddEmployeeValidateSDTSpecialCharacters () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "@123456789","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'sdt')].defaultMessage").value("Số điện thoại không hợp lệ."));
    }
    @Test
    public void testAddEmployeeValidateSDTLenghtLessThan10 () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "123456789","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'sdt')].defaultMessage").value("Số điện thoại không hợp lệ."));
    }
    @Test
    public void testAddEmployeeValidateSDTLenghtMoreThan10 () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "12345678910","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'sdt')].defaultMessage").value("Số điện thoại không hợp lệ."));
    }
    @Test
    public void testAddEmployeeValidateSDTLenghtEquals10 () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "1234567891","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'sdt')].defaultMessage").value("Số điện thoại không hợp lệ."));
    }
    @Test
    public void testAddEmployeeValidateSDTWrongFormat () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "0123 456 789","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'sdt')].defaultMessage").value("Số điện thoại không hợp lệ."));
    }
    @Test
    public void testAddEmployeeValidateSDTNotStart0 () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "1234567899","quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'sdt')].defaultMessage").value("Số điện thoại không hợp lệ."));
    }
    @Test
    public void testAddEmployeeValidateEmailWrongFormat () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "0123456789","quanggmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'email')].defaultMessage").value("Email không hợp lệ."));
    }
    @Test
    public void testAddEmployeeValidateChuVuNumber () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "0948256153","quang@gmail.com", "Thanh Trì - Hà Nội", "123", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'chucvu')].defaultMessage").value("Chức vụ chỉ được chứa chữ cái và khoảng trắng."));
    }
    @Test
    public void testAddEmployeeValidateChuVuNumberChar () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "0948256153","quang@gmail.com", "Thanh Trì - Hà Nội", "123abc", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'chucvu')].defaultMessage").value("Chức vụ chỉ được chứa chữ cái và khoảng trắng."));
    }
    @Test
    public void testAddEmployeeValidateChuVuSpecialCharacters () throws Exception{
        Employee employee = new Employee("Nguyễn Quang", "0948256153","quang@gmail.com", "Thanh Trì - Hà Nội", "@abc", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'chucvu')].defaultMessage").value("Chức vụ chỉ được chứa chữ cái và khoảng trắng."));
    }
}
