package com.app.bank.mgmt.service;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.app.bank.mgmt.config.CORSFilter;
import com.app.bank.mgmt.controller.BankManagmentController;
import com.app.bank.mgmt.entity.CustomerAccount;
import com.app.bank.mgmt.service.impl.BankManagmentServiceImpl;

@WebMvcTest(BankManagmentController.class)
public class BankManagementServiceTest {

	@Autowired
    private MockMvc mockMvc;
	
/*	@MockBean
	private CustomerAccountRepository customerAccountRepository;*/

	@MockBean
private BankManagmentServiceImpl bankManagementServiceImpl;



@InjectMocks
private BankManagmentController bankManagmentController;

	
@BeforeEach
void init() {
   MockitoAnnotations.initMocks(this);
   mockMvc = MockMvcBuilders.standaloneSetup(bankManagmentController).addFilter(new CORSFilter()).build();
}

@Test
public void depositProcessPassTest() throws Exception {
	//Optional<CustomerAccount> accountOptional = customerAccountRepository.findById(1L);
	//double totalBalance = accountOptional.get().getAvailableBalance();
	//double totalBalance = account.getAvailableBalance();
	//System.out.println(accountOptional.isPresent());
    double depositAmount = 1000.00;
    when(bankManagementServiceImpl.depositProcess(1l,depositAmount)).thenReturn(depositAmount);
    mockMvc.perform(post("/customers/deposit/1/1000").header("Origin", "*").contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk()).andDo(print());

}

@Test
public void depositProcessFailTest() throws Exception {
	//Optional<CustomerAccount> accountOptional = customerAccountRepository.findById(2L);
		//double totalBalance = accountOptional.get().getAvailableBalance();
		//double totalBalance = account.getAvailableBalance();
		//System.out.println(accountOptional.isPresent());
    double depositAmount = 2000.00;
    when(bankManagementServiceImpl.depositProcess(2l,depositAmount)).thenReturn(depositAmount);
    mockMvc.perform(post("/customers/deposit/2/2000").header("Origin", "*").contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk()).andDo(print());

}

}