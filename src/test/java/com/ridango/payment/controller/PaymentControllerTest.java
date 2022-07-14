package com.ridango.payment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridango.payment.BaseServiceTest;
import com.ridango.payment.db.entity.Account;
import com.ridango.payment.db.services.AccountService;
import com.ridango.payment.domain.request.CreatePaymentRequest;
import com.ridango.payment.domain.response.PaymentResponse;
import com.ridango.payment.domain.response.ErrorResponse;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class PaymentControllerTest extends BaseServiceTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  AccountService accountService;

  @Test
  void createPayment_CreatesNewPayment_WithValidInput() throws Exception {
    var sender = accountService.create(
        Account.builder()
            .name("Account 1")
            .balance(BigDecimal.valueOf(100))
            .build()
    );

    var receiver = accountService.create(
        Account.builder()
            .name("Account 2")
            .balance(BigDecimal.valueOf(100))
            .build()
    );
    var mockMvcResult = mockMvc.perform(MockMvcRequestBuilders
            .post("/payments")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(
                CreatePaymentRequest.builder()
                    .senderAccountId(sender.getId())
                    .receiverAccountId(receiver.getId())
                    .amount(BigDecimal.valueOf(100))
                    .build()
            )))
        .andExpect(status().isOk())
        .andReturn();

    var contentAsString = mockMvcResult.getResponse().getContentAsString();
    var response = objectMapper.readValue(contentAsString, PaymentResponse.class);
    assertEquals(sender.getId(), response.getSenderAccountId());
    assertEquals(receiver.getId(), response.getReceiverAccountId());
    assertEquals(0, BigDecimal.valueOf(100).compareTo(response.getAmount()));
    assertNotNull(response.getPaymentId());

    assertEquals(0, accountService.find(sender.getId()).getBalance().compareTo(BigDecimal.ZERO));
  }

  @Test
  void createPayment_Throws_InsufficientBalance() throws Exception {
    var sender = accountService.create(
        Account.builder()
            .name("Account 1")
            .balance(BigDecimal.valueOf(100))
            .build()
    );

    var receiver = accountService.create(
        Account.builder()
            .name("Account 2")
            .balance(BigDecimal.valueOf(100))
            .build()
    );
    var mockMvcResult = mockMvc.perform(MockMvcRequestBuilders
            .post("/payments")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(
                CreatePaymentRequest.builder()
                    .senderAccountId(sender.getId())
                    .receiverAccountId(receiver.getId())
                    .amount(BigDecimal.valueOf(1000))
                    .build()
            )))
        .andExpect(status().is4xxClientError())
        .andReturn();

    var contentAsString = mockMvcResult.getResponse().getContentAsString();
    var response = objectMapper.readValue(contentAsString, ErrorResponse.class);
    assertEquals("Insufficient sender balance", response.getErrorMessage());
  }

}
