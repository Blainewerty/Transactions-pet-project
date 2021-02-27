//package ru.milov.transactions.service.controller;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import ru.milov.transactions.Main;
//import ru.milov.transactions.response.ResponseBill;
//import ru.milov.transactions.service.entity.Bill;
//import ru.milov.transactions.service.entity.User;
//import ru.milov.transactions.service.services.ServiceAppBill;
//
//import java.math.BigDecimal;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//@SpringBootTest(classes = Main.class, webEnvironment = WebEnvironment.DEFINED_PORT)
//@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
//@ActiveProfiles("test")
//public class ControllerBillTest {
//
//    @MockBean
//    private ServiceAppBill mockServiceAppBill;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @Test
//    public void testGetUserBill() throws Exception {
//
//        // Run the test
//                mockMvc.perform(get("/bill/getBill/{nameOfBill}", "name")
//                        .contentType("text/plain"))
//                        .andExpect(status().is2xxSuccessful())
//                        .andExpect(content().string("test"));
//    }
//
//    @Test
//    public void testCreateUserBill() throws Exception {
//        // Setup
//
//        // Configure ServiceAppBill.createUserBill(...).
//        final ResponseBill responseBill = new ResponseBill();
//        responseBill.setId(0L);
//        responseBill.setName("name");
//        responseBill.setBalance(new BigDecimal("0.00"));
//        when(mockServiceAppBill.createUserBill(any(User.class), any(Bill.class))).thenReturn(responseBill);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(post("/bill/createBill")
//                .content("content").contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertEquals(HttpStatus.OK.value(), response.getStatus());
//        assertEquals("expectedResponse", response.getContentAsString());
//    }
//
//    @Test
//    public void testGetUserBillList() throws Exception {
//        // Setup
//        when(mockServiceAppBill.getInfoAboutAllBillsOfUser(any(User.class))).thenReturn(List.of());
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("/bill/billList")
//                .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertEquals(HttpStatus.OK.value(), response.getStatus());
//        assertEquals("expectedResponse", response.getContentAsString());
//    }
//
//    @Test
//    public void testGetUserBillList_ServiceAppBillReturnsNoItems() throws Exception {
//        // Setup
//        when(mockServiceAppBill.getInfoAboutAllBillsOfUser(any(User.class))).thenReturn(Collections.emptyList());
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("/bill/billList")
//                .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertEquals(HttpStatus.OK.value(), response.getStatus());
//        assertEquals("expectedResponse", response.getContentAsString());
//    }
//
//    @Test
//    public void testDeleteBill() throws Exception {
//        // Setup
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(delete("/bill/delete/{nameOfBill}", "nameOfBill")
//                .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertEquals(HttpStatus.OK.value(), response.getStatus());
//        assertEquals("expectedResponse", response.getContentAsString());
//        verify(mockServiceAppBill).deleteUserBill(any(User.class), eq("nameOfBill"));
//    }
//}
