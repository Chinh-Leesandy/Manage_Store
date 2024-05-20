package com.example.ManageStore.Junit.Controller;

import com.example.ManageStore.Controller.ProductTypeController;
import com.example.ManageStore.DAO.ProductTypeDAO;
import com.example.ManageStore.Model.ProductType;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
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

    @BeforeEach
    public  void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAllProductType() throws Exception {
        List<ProductType> list = Arrays.asList(
                new ProductType( 1,"Bìa hồ sơ","Stationery Inc", Date.valueOf("2024-04-02"),3,"Kệ số 2, Hàng số 3"),
                new ProductType(2, "Bút","Deli", Date.valueOf("2024-03-15"),10,"Kệ số 2, Hàng số 1")
        );
        when(productTypeDAO.selectAll()).thenReturn(ResponseEntity.ok().body(list));
        ResponseEntity<?> responseEntity = productTypeController.getProductType();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testGetAllProductType1() throws Exception {
        List<ProductType> productTypes = Arrays.asList(
                new ProductType(1, "Đầm", "Quảng Chau", Date.valueOf("2024-04-02"), 3, "Kệ số 2 - Hàng số 3"),
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
        ProductType productType = new ProductType( "Sổ", "Stationery Inc", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.ok().body(productType));
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testPutProductType() throws Exception {
        ProductType productType = new ProductType(3, "Sổ", "Stationery Inc", Date.valueOf("2024-04-26"), 3, "Kệ số 1 - Hàng số 2");
        when(productTypeDAO.updateProductTyppe(productType)).thenReturn(ResponseEntity.ok().body(productType));
        ResponseEntity<?> responseEntity = productTypeController.putProductType(productType);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteProductType() throws Exception {
        when(productTypeDAO.deleteProductType(3)).thenReturn(ResponseEntity.ok().body("Delete Product Type"));
        ResponseEntity<?> responseEntity = productTypeController.deleteProductType("3");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testAddProductTypeError() throws Exception {
        ProductType productType = new ProductType( "", "", null, 0, "");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddProductTypeErrorName() throws Exception {
        ProductType productType = new ProductType( "", "Stationery Inc", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddProductTypeErrorNCC() throws Exception {
        ProductType productType = new ProductType( "Sổ", "", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddProductTypeErrorTime() throws Exception {
        ProductType productType = new ProductType( "Sổ", "Stationery Inc", null, 5, "Kệ số 1 - Hàng số 3");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddProductTypeErrorAmount() throws Exception {
        ProductType productType = new ProductType( "Sổ", "Stationery Inc", Date.valueOf("2024-04-26"), 0, "Kệ số 1 - Hàng số 3");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditProductTypeError() throws Exception {
        ProductType productType = new ProductType( "", "", null,0 , "");
        when(productTypeDAO.updateProductTyppe(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.putProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditProductTypeErrorName() throws Exception {
        ProductType productType = new ProductType( "", "Stationery Inc", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        when(productTypeDAO.updateProductTyppe(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.putProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditProductTypeErrorNCC() throws Exception {
        ProductType productType = new ProductType( "Sổ", "", Date.valueOf("2024-04-26"), 5, "Kệ số 1 - Hàng số 3");
        when(productTypeDAO.updateProductTyppe(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.putProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditProductTypeErrorTime() throws Exception {
        ProductType productType = new ProductType( "Sổ", "Stationery Inc", null, 5, "Kệ số 1 - Hàng số 3");
        when(productTypeDAO.updateProductTyppe(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.putProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditProductTypeErrorAmount() throws Exception {
        ProductType productType = new ProductType( "Sổ", "Stationery Inc", Date.valueOf("2024-04-26"), 0, "Kệ số 1 - Hàng số 3");
        when(productTypeDAO.updateProductTyppe(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.putProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddProductTypeValidationName() throws Exception {
        ProductType productType = new ProductType( "", "", null, 0, "");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddProductTypeValidationName1() throws Exception {
        ProductType productType = new ProductType( "", "", null, 0, "");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddProductTypeValidationNCC() throws Exception {
        ProductType productType = new ProductType( "", "", null, 0, "");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddProductTypeValidationNCC1() throws Exception {
        ProductType productType = new ProductType( "", "", null, 0, "");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddProductTypeValidationAmount() throws Exception {
        ProductType productType = new ProductType( "", "", null, 0, "");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddProductTypeValidationAmount1() throws Exception {
        ProductType productType = new ProductType( "", "", null, 0, "");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddProductTypeValidationAmount3() throws Exception {
        ProductType productType = new ProductType( "", "", null, 0, "");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testAddProductTypeValidationAmount4() throws Exception {
        ProductType productType = new ProductType( "", "", null, 0, "");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditProductTypeValidationName() throws Exception {
        ProductType productType = new ProductType( "", "", null, 0, "");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditProductTypeValidationName1() throws Exception {
        ProductType productType = new ProductType( "", "", null, 0, "");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditProductTypeValidationNCC() throws Exception {
        ProductType productType = new ProductType( "", "", null, 0, "");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditProductTypeValidationNCC1() throws Exception {
        ProductType productType = new ProductType( "", "", null, 0, "");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditProductTypeValidationAmount() throws Exception {
        ProductType productType = new ProductType( "", "", null, 0, "");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditProductTypeValidationAmount1() throws Exception {
        ProductType productType = new ProductType( "", "", null, 0, "");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditProductTypeValidationAmount3() throws Exception {
        ProductType productType = new ProductType( "", "", null, 0, "");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testEditProductTypeValidationAmount4() throws Exception {
        ProductType productType = new ProductType( "", "", null, 0, "");
        when(productTypeDAO.insertProductType(productType)).thenReturn(ResponseEntity.internalServerError().build());
        ResponseEntity<?> responseEntity = productTypeController.postProductType(productType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}
