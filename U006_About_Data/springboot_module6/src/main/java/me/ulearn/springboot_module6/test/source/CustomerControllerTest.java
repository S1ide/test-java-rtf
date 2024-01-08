package me.ulearn.springboot_module6.test.source;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ulearn.springboot_module6.controller.CustomerController;
import me.ulearn.springboot_module6.entity.Customer;
import me.ulearn.springboot_module6.entity.Product;
import me.ulearn.springboot_module6.service.CustomerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService service;

    private static final Customer CUSTOMER = new Customer();

    @BeforeAll
    public static void setup() {
        Product product = new Product(1L, "J", false);
        CUSTOMER.setId(1L);
        CUSTOMER.setBirthDate(LocalDate.ofYearDay(1999, 1));
        CUSTOMER.setName("1111");
        CUSTOMER.setSurname("1111");
        CUSTOMER.setPassword("cx");
        CUSTOMER.setEmail("1@mail.ru");
        CUSTOMER.setCart(List.of(product));
    }

    @Test
    public void assertThatCustomerCreated() throws Exception {
        Mockito.when(service.createCustomer(Mockito.any(Customer.class))).thenReturn(CUSTOMER);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
                        .content(objectMapper.writeValueAsString(CUSTOMER))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(CUSTOMER)));
    }

    @Test
    public void assertThatGetReturnedRight() throws Exception {
        Mockito.when(service.getProducts(1L)).thenReturn(ResponseEntity.of(Optional.of(CUSTOMER.getCart())));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/1/cart"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(CUSTOMER.getCart())));
    }

    @Test
    public void assertThatCustomerReturned() throws Exception {
        Mockito.when(service.getCustomer(1L)).thenReturn(ResponseEntity.of(Optional.of(CUSTOMER)));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(CUSTOMER)));
    }

    @Test
    public void assertThatCustomerUpdated() throws Exception {
        Mockito.when(service.updateCustomer(Mockito.eq(1L), Mockito.any(Customer.class))).thenReturn(ResponseEntity.of(Optional.of(CUSTOMER)));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customers/1")
                        .content(objectMapper.writeValueAsString(CUSTOMER))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(CUSTOMER)));
    }

    @Test
    public void assertThatProductAdded() throws Exception {
        Product product = new Product(2L, "A", false);
        Mockito.when(service.saveProduct(Mockito.eq(1L), Mockito.any(Product.class))).thenReturn(ResponseEntity.of(Optional.of(product)));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers/1/cart")
                        .content(objectMapper.writeValueAsString(product))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(product)));
    }

    @Test
    public void assertThatProductDeleted() throws Exception {
        Product product = new Product(1L, "J", false);
        Mockito.when(service.removeProduct(Mockito.eq(1L), Mockito.any(Product.class))).thenReturn(ResponseEntity.of(Optional.of(product)));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customers/1/cart")
                        .content(objectMapper.writeValueAsString(product))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(product)));
    }
}