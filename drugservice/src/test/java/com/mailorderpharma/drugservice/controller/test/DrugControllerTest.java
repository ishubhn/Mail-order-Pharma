package com.mailorderpharma.drugservice.controller.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailorderpharma.drugservice.controller.DrugController;
import com.mailorderpharma.drugservice.dao.DrugDetailsRepository;
import com.mailorderpharma.drugservice.dao.DrugLocationRepository;
import com.mailorderpharma.drugservice.entity.DrugDetails;
import com.mailorderpharma.drugservice.entity.DrugLocationDetails;
import com.mailorderpharma.drugservice.entity.ExceptionResponse;
import com.mailorderpharma.drugservice.entity.Stock;
import com.mailorderpharma.drugservice.entity.TokenValid;
import com.mailorderpharma.drugservice.exception.DrugNotFoundException;
import com.mailorderpharma.drugservice.restclients.AuthFeign;
import com.mailorderpharma.drugservice.service.DrugDetailsService;

@AutoConfigureMockMvc
@SpringBootTest
class DrugControllerTest {

	@InjectMocks
	DrugController drugController;

	@Mock
	DrugDetailsService drugDetailsService;

	@Mock
	DrugDetailsRepository drugDetailsRepository;

	@Mock
	DrugLocationRepository drugLocationRepository;

	@MockBean
	AuthFeign authFeign;

	@Autowired
	MockMvc mockMvc;

	List<DrugLocationDetails> list = new ArrayList<DrugLocationDetails>();
	@Test
	public void testGetDrugById() throws Exception {
		list.add(new DrugLocationDetails("D1", "Chennai", 30, null));
		DrugDetails expected = new DrugDetails("D1", "Drug1", "manu1", new Date(), new Date(), list);
		ObjectMapper objectMapper = new ObjectMapper();
		String expectedValue = objectMapper.writeValueAsString(expected).substring(11, 13);
		TokenValid tokenValid = new TokenValid("uid", "uname", true);
		ResponseEntity<TokenValid> response = new ResponseEntity<TokenValid>(tokenValid, HttpStatus.OK);
		when(authFeign.getValidity("Bearer Token")).thenReturn(response);
		when(drugDetailsService.getDrugById("D1", "Bearer Token")).thenReturn(expected);
		MvcResult result = mockMvc.perform(get("/searchDrugsById/D1").header("Authorization", "Bearer Token"))
				.andReturn();
		String actualValue = result.getResponse().getContentAsString().substring(11, 13);
		assertEquals(expectedValue, actualValue);
	}
	@Test
	public void testGetDrugByName() throws Exception {
		list.add(new DrugLocationDetails("D1", "Chennai", 30, null));
		DrugDetails expected = new DrugDetails("D1", "Drug1", "manu1", new Date(), new Date(), list);
		ObjectMapper objectMapper = new ObjectMapper();
		String expectedValue = objectMapper.writeValueAsString(expected).substring(27, 32);
		TokenValid tokenValid = new TokenValid("uid", "uname", true);
		ResponseEntity<TokenValid> response = new ResponseEntity<TokenValid>(tokenValid, HttpStatus.OK);
		when(authFeign.getValidity("Bearer Token")).thenReturn(response);
		MvcResult result = mockMvc.perform(get("/searchDrugsByName/Drug1").header("Authorization", "Bearer Token"))
				.andReturn();
		String actualValue = result.getResponse().getContentAsString().substring(27, 32);
		System.out.println(actualValue);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testDispatchableDrugStock() throws Exception {
		Stock expected = new Stock("D1","Drug1",new Date(),30);
		ObjectMapper objectMapper = new ObjectMapper();
		String expectedValue = objectMapper.writeValueAsString(expected).substring(69, 71);
		TokenValid tokenValid = new TokenValid("uid", "uname", true);
		ResponseEntity<TokenValid> response = new ResponseEntity<TokenValid>(tokenValid, HttpStatus.OK);
		when(authFeign.getValidity("Bearer Token")).thenReturn(response);
		MvcResult result = mockMvc.perform(get("/getDispatchableDrugStock/D1/Chennai").header("Authorization", "Bearer Token"))
				.andReturn();
		String actualValue = result.getResponse().getContentAsString().substring(87, 89);
		assertEquals(expectedValue,actualValue);
	}
	
	@Test
	public void updateQuantity() throws Exception {
		TokenValid tokenValid = new TokenValid("uid", "uname", true);
		ResponseEntity<TokenValid> response = new ResponseEntity<TokenValid>(tokenValid, HttpStatus.OK);
		when(authFeign.getValidity("Bearer Token")).thenReturn(response);
		MvcResult result = mockMvc.perform(put("/updateDispatchableDrugStock/D1/Chennai/1").header("Authorization", "Bearer Token"))
				.andReturn();
		assertEquals(200,result.getResponse().getStatus());
	}
}
