package com.example.ManageStore.Junit.Controller;

import com.example.ManageStore.Controller.ProductTypeController;
import com.example.ManageStore.DAO.ProductTypeDAO;
import com.example.ManageStore.Model.ProductType;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.BindingResult;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(ProductTypeController.class)
@ExtendWith(MockitoExtension.class)
public class ProductTypeControllerTest {

    @Mock
    private ProductTypeDAO productTypeDAO;

    @InjectMocks
    private ProductTypeController productTypeController;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public  void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAllProductType() throws Exception {
        List<ProductType> list = Arrays.asList(
                new ProductType(1, "Đầm", "Quảng Châu", Date.valueOf("2024-04-02"), 3, "Kệ số 2 - Hàng số 3"),
                new ProductType(2, "Vest", "Việt Tiến", Date.valueOf("2024-03-15"), 10, "Kệ số 2 - Hàng số 1")
        );
        when(productTypeDAO.selectAll()).thenReturn(ResponseEntity.ok().body(list));
        ResponseEntity<?> responseEntity = productTypeController.getProductType();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testGetAllProductType1() throws Exception {
        List<ProductType> productTypes = Arrays.asList(
                new ProductType(1, "Đầm", "Quảng Châu", Date.valueOf("2024-04-02"), 3, "Kệ số 2 - Hàng số 3"),
                new ProductType(2, "Vest", "Việt Tiến", Date.valueOf("2024-03-15"), 10, "Kệ số 2 - Hàng số 1")
        );
        ResponseEntity<List<ProductType>> responseEntity = ResponseEntity.ok().body(productTypes);

        ResultActions resultActions = mockMvc.perform(get("/productType")
                .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].ten").value("Đầm"))
                .andExpect(jsonPath("$[0].ncc").value("Quảng Châu"))
                .andExpect(jsonPath("$[0].thoigiannhap").value("2024-04-02"))
                .andExpect(jsonPath("$[0].soluong").value(3))
                .andExpect(jsonPath("$[0].vitri").value("Kệ số 2 - Hàng số 3"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].ten").value("Vest"))
                .andExpect(jsonPath("$[1].ncc").value("Việt Tiến"))
                .andExpect(jsonPath("$[1].thoigiannhap").value("2024-03-15"))
                .andExpect(jsonPath("$[1].soluong").value(10))
                .andExpect(jsonPath("$[1].vitri").value("Kệ số 2 - Hàng số 1"));
    }
    @Test
    public void testGetProductTypeId() throws Exception {
        ProductType productType = new ProductType(1, "Bìa hồ sơ", "Stationery Inc", Date.valueOf("2024-04-02"), 3, "Kệ số 2, Hàng số 3");
        when(productTypeDAO.selectProductType(1)).thenReturn(ResponseEntity.ok().body(productType));
        ResponseEntity<?> responseEntity = productTypeController.getProductTypeId("1");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testPostProductType() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.ok().body(productType));
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testPostProductTypeEmptyVitri() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 5, "");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.ok().body(productType));
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testPutProductType() throws Exception {
        ProductType productType = new ProductType(3, "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 3, "Kệ số 1 - Hàng số 2");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.ok().body(productType));
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testPutProductTypeEmptyVitri() throws Exception {
        ProductType productType = new ProductType(3, "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 3, "");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.ok().body(productType));
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteProductType() throws Exception {
        when(productTypeDAO.deleteProductType(3)).thenReturn(ResponseEntity.ok().body("Delete Product Type"));
        ResponseEntity<?> responseEntity = productTypeController.deleteProductType("3");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testAddProductTypeEmptyAll() throws Exception {
        ProductType productType = new ProductType( "", "", null, 0, "");
        mockMvc.perform(MockMvcRequestBuilders.post("/addProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void testAddProductTypeEmptyName() throws Exception {
        ProductType productType = new ProductType( null, "Quảng Châu", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.post("/addProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'ten')].defaultMessage").value("Tên loại mặt hàng không được để trống."));
    }
    @Test
    public void testAddProductTypeEmptyNCC() throws Exception {
        ProductType productType = new ProductType( "Đầm", null, Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.post("/addProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'ncc')].defaultMessage").value("Tên nhà cung cấp không được để trống."));
    }
    @Test
    public void testAddProductTypeEmptyTime() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", null, 5, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.post("/addProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'thoigiannhap')].defaultMessage").value("Thời gian nhập không được để trống."));
    }
    @Test
    public void testAddProductTypeEmptyAmount() throws Exception {
        ProductType productType = new ProductType("Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 0, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.post("/addProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'soluong')].defaultMessage").value("Số lượng loại mặt hàng số nguyên lớn hơn hoặc bằng 1."));
    }

    @Test
    public void testEditProductTypeEmptyAll() throws Exception {
        ProductType productType = new ProductType( "", "", null, 0, "");
        mockMvc.perform(MockMvcRequestBuilders.put("/updateProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void testEditProductTypeEmptyName() throws Exception {
        ProductType productType = new ProductType( null, "Quảng Châu", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.put("/updateProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'ten')].defaultMessage").value("Tên loại mặt hàng không được để trống."));
    }
    @Test
    public void testEditProductTypeEmptyNCC() throws Exception {
        ProductType productType = new ProductType( "Đầm", null, Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.put("/updateProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'ncc')].defaultMessage").value("Tên nhà cung cấp không được để trống."));
    }
    @Test
    public void testEditProductTypeEmptyTime() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", null, 5, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.put("/updateProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'thoigiannhap')].defaultMessage").value("Thời gian nhập không được để trống."));
    }
    @Test
    public void testEditProductTypeEmptyAmount() throws Exception {
        ProductType productType = new ProductType("Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 0, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.put("/updateProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'soluong')].defaultMessage").value("Số lượng loại mặt hàng số nguyên lớn hơn hoặc bằng 1."));
    }
    @Test
    public void testAddProductTypeValidationName() throws Exception {
        ProductType productType = new ProductType( "123", "Quảng Châu", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.post("/addProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'ten')].defaultMessage").value("Tên loại mặt hàng chỉ được chứa chữ cái và khoảng trắng."));
    }
    @Test
    public void testAddProductTypeValidationNameSpecialCharacters() throws Exception {
        ProductType productType = new ProductType( "@Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.post("/addProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'ten')].defaultMessage").value("Tên loại mặt hàng chỉ được chứa chữ cái và khoảng trắng."));
    }
    @Test
    public void testAddProductTypeValidationNameSuccess() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.ok().body(productType));
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testAddProductTypeValidationNCC() throws Exception {
        ProductType productType = new ProductType( "Đầm", "123", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.post("/addProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'ncc')].defaultMessage").value("Tên nhà cung cấp chỉ được chứa chữ cái và khoảng trắng."));
    }
    @Test
    public void testAddProductTypeValidationNCCSpecialCharacters() throws Exception {
        ProductType productType = new ProductType( "Đầm", "@Quảng", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.post("/addProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'ncc')].defaultMessage").value("Tên nhà cung cấp chỉ được chứa chữ cái và khoảng trắng."));
    }
    @Test
    public void testAddProductTypeValidationNCCSuccess() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.ok().body(productType));
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testAddProductTypeValidationAmountSuccess() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.ok().body(productType));
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testAddProductTypeValidationAmountMin() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), -1, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.post("/addProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'soluong')].defaultMessage").value("Số lượng loại mặt hàng số nguyên lớn hơn hoặc bằng 1."));
    }
    @Test
    public void testAddProductTypeValidationAmountMax() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 101, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.post("/addProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'soluong')].defaultMessage").value("Số lượng loại mặt hàng số nguyên nhỏ hơn hoặc bằng 100."));
    }
    @Test
    public void testAddProductTypeValidationAmountFloat() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), (int)3.5, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.post("/addProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void testAddProductTypeValidationAmountMinSuccess() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 1, "Kệ số 1 - Hàng số 3");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.ok().body(productType));
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testAddProductTypeValidationAmountMaxSuccess() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 100, "Kệ số 1 - Hàng số 3");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.ok().body(productType));
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testEditProductTypeValidationName() throws Exception {
        ProductType productType = new ProductType( "123", "Quảng Châu", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.put("/updateProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'ten')].defaultMessage").value("Tên loại mặt hàng chỉ được chứa chữ cái và khoảng trắng."));
    }
    @Test
    public void testEditProductTypeValidationNameSpecialCharacters() throws Exception {
        ProductType productType = new ProductType( "@Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.put("/updateProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'ten')].defaultMessage").value("Tên loại mặt hàng chỉ được chứa chữ cái và khoảng trắng."));
    }
    @Test
    public void testEditProductTypeValidationNameSuccess() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.ok().body(productType));
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testEditProductTypeValidationNCC() throws Exception {
        ProductType productType = new ProductType( "Đầm", "123", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.put("/updateProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'ncc')].defaultMessage").value("Tên nhà cung cấp chỉ được chứa chữ cái và khoảng trắng."));
    }
    @Test
    public void testEditProductTypeValidationNCCSpecialCharacters() throws Exception {
        ProductType productType = new ProductType( "Đầm", "@Quảng", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.put("/updateProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'ncc')].defaultMessage").value("Tên nhà cung cấp chỉ được chứa chữ cái và khoảng trắng."));
    }
    @Test
    public void testEditProductTypeValidationNCCSuccess() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.ok().body(productType));
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testEditProductTypeValidationAmountSuccess() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.ok().body(productType));
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testEditProductTypeValidationAmountMin() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), -1, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.put("/updateProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'soluong')].defaultMessage").value("Số lượng loại mặt hàng số nguyên lớn hơn hoặc bằng 1."));
    }
    @Test
    public void testEditProductTypeValidationAmountMax() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 101, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.put("/updateProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.field == 'soluong')].defaultMessage").value("Số lượng loại mặt hàng số nguyên nhỏ hơn hoặc bằng 100."));
    }
    @Test
    public void testEditProductTypeValidationAmountMinSuccess() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 1, "Kệ số 1 - Hàng số 3");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.ok().body(productType));
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testEditProductTypeValidationAmountMaxSuccess() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), 100, "Kệ số 1 - Hàng số 3");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.ok().body(productType));
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testEditProductTypeValidationAmountFloat() throws Exception {
        ProductType productType = new ProductType( "Đầm", "Quảng Châu", Date.valueOf("2024-04-26"), (int) 3.5, "Kệ số 1 - Hàng số 3");
        mockMvc.perform(MockMvcRequestBuilders.put("/updateProductType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productType)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
