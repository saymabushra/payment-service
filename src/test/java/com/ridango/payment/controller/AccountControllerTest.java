package com.ridango.payment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridango.payment.BaseServiceTest;
import com.ridango.payment.domain.response.AccountResponse;
import com.ridango.payment.domain.request.CreateAccountRequest;
import com.ridango.payment.domain.response.ErrorResponse;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class AccountControllerTest extends BaseServiceTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void createAccount_CreatesNewAccount_WithValidInput() throws Exception {
    var mockMvcResult = mockMvc.perform(MockMvcRequestBuilders
            .post("/accounts")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(
                CreateAccountRequest.builder()
                    .name("Account 1")
                    .balance(BigDecimal.TEN)
                    .build()
            )))
        .andExpect(status().isOk())
        .andReturn();

    var contentAsString = mockMvcResult.getResponse().getContentAsString();
    var response = objectMapper.readValue(contentAsString, AccountResponse.class);
    assertEquals("Account 1", response.getName());
    assertEquals(BigDecimal.TEN, response.getBalance());
    assertNotNull(response.getAccountId());
  }

  @Test
  void createAccount_Throws_WithInValidInput() throws Exception {
    var mockMvcResult = mockMvc.perform(MockMvcRequestBuilders
            .post("/accounts")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(
                CreateAccountRequest.builder()
                    .name("Account 2")
                    .balance(BigDecimal.TEN.negate())
                    .build()
            )))
        .andExpect(status().is4xxClientError())
        .andReturn();

    var contentAsString = mockMvcResult.getResponse().getContentAsString();
    var response = objectMapper.readValue(contentAsString, ErrorResponse.class);
    assertEquals("Invalid amount", response.getErrorMessage());
  }

}
