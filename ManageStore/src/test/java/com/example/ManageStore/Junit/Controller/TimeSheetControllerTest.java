package com.example.ManageStore.Junit.Controller;

import com.example.ManageStore.Controller.TKTimeSheetController;
import com.example.ManageStore.DAO.TimeKeepingDAO;
import com.example.ManageStore.Model.TimeKeeping;
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

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(TKTimeSheetController.class)
@ExtendWith(MockitoExtension.class)
public class TimeSheetControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private TimeKeepingDAO keepingDAO;
    @InjectMocks
    private TKTimeSheetController controller;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetEmployeeTimeKeeping () throws Exception {
        List<TimeKeeping> list = Arrays.asList(
                new TimeKeeping(1,1, Timestamp.valueOf("2024-03-10 01:30:00"), Timestamp.valueOf("2024-03-10 10:45:00"), "Nguyễn Chinh", "Quản lý mặt hàng"),
                new TimeKeeping(2,2, Timestamp.valueOf("2024-03-20 02:15:00"), Timestamp.valueOf("2024-03-20 11:00:00"), "Nguyễn Cường", "Quản lý nhân viên"),
                new TimeKeeping(3,1, Timestamp.valueOf("2024-03-25 03:00:00"), Timestamp.valueOf("2024-03-25 09:30:00"), "Nguyễn Chinh", "Quản lý mặt hàng")
        );
        when(keepingDAO.selectEmployeeTime()).thenReturn(ResponseEntity.ok().body(list));
        ResponseEntity<?> responseEntity = controller.getEmployeeTimeKeeping();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
