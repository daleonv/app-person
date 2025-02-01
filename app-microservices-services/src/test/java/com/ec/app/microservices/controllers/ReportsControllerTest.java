package com.ec.app.microservices.controllers;

import com.ec.app.microservices.AccountStatusRequestVo;
import com.ec.app.microservices.AccountStatusResponseVo;
import com.ec.app.microservices.services.ReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class ReportsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportService reportService;

    @Test
    public void testFindAccountStatus_Success() throws Exception {
        List<AccountStatusResponseVo> responseList = List.of(
                AccountStatusResponseVo.builder()
                        .date(new Date())
                        .name("John Doe")
                        .accountNumber("12345")
                        .accountType("Saving")
                        .balance(1000.0)
                        .build()
        );

        when(reportService.findAccountStatus(any(AccountStatusRequestVo.class))).thenReturn(responseList);

        String requestJson = "{\"initialDate\":\"2024-07-09T10:00:00Z\", \"endDate\":\"2024-07-11T10:00:00Z\", \"customerId\":\"1\"}";

        mockMvc.perform(post("/reports/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("John Doe"))
                .andExpect(jsonPath("$.data[0].balance").value(1000.0));
    }

    @Test
    public void testFindAccountStatus_NoData() throws Exception {
        List<AccountStatusResponseVo> responseList = Collections.emptyList();

        when(reportService.findAccountStatus(any(AccountStatusRequestVo.class))).thenReturn(responseList);

        String requestJson = "{\"initialDate\":\"2024-07-09T10:00:00Z\", \"endDate\":\"2024-07-11T10:00:00Z\", \"customerId\":\"1\"}";

        mockMvc.perform(post("/reports/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());
    }

}
