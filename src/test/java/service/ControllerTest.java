package service;

import org.example.purchaseprocessingproject.controller.PurchaseController;
import org.example.purchaseprocessingproject.dto.PurchaseRequest;
import org.example.purchaseprocessingproject.PurchaseProcessingProjectApplication;
import org.example.purchaseprocessingproject.service.PurchaseServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PurchaseController.class)
@ContextConfiguration(classes =  PurchaseProcessingProjectApplication.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private PurchaseServiceImpl service;

    @Test
    void  testCreatePurchase() throws Exception{
        when(service.createPurchase(any()))
                .thenReturn(UUID.randomUUID());

        PurchaseRequest request = new PurchaseRequest(
                "Tickets",
                LocalDate.now(),
                BigDecimal.valueOf(100)
        );

        mockMvc.perform(post("/api/v1/purchases")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated());
    }



}
