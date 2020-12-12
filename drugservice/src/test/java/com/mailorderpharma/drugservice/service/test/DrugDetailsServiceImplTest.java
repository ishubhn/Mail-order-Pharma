//package com.mailorderpharma.drugservice.service.test;
//
//import static org.junit.Assert.assertThrows;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import com.mailorderpharma.drugservice.dao.DrugDetailsRepository;
//import com.mailorderpharma.drugservice.dao.DrugLocationRepository;
//import com.mailorderpharma.drugservice.entity.DrugLocationDetails;
//import com.mailorderpharma.drugservice.entity.TokenValid;
//import com.mailorderpharma.drugservice.exception.DrugNotFoundException;
//import com.mailorderpharma.drugservice.exception.InvalidTokenException;
//import com.mailorderpharma.drugservice.restclients.AuthFeign;
//import com.mailorderpharma.drugservice.service.DrugDetailsServiceImpl;
//
//@SpringBootTest
//class DrugDetailsServiceImplTest {
//
//	@InjectMocks
//	DrugDetailsServiceImpl drugDetailsServiceImpl;
//
//	@MockBean
//	AuthFeign authFeign;
//
//	@MockBean
//	private DrugDetailsRepository drugRepo;
//
//	@MockBean
//	private DrugLocationRepository locationRepo;
//
//	List<DrugLocationDetails> list = new ArrayList<DrugLocationDetails>();
//
//	@Test
//	public void testGetDrugById() throws InvalidTokenException, DrugNotFoundException {
//		Throwable exception = assertThrows(DrugNotFoundException.class, () -> drugDetailsServiceImpl.getDrugById("D5", "Bearer Token")
//                ,"Message"); 
//	}
//}
