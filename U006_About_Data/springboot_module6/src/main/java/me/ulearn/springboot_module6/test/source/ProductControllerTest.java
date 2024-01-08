package me.ulearn.springboot_module6.test.source;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ulearn.springboot_module6.controller.ProductController;
import me.ulearn.springboot_module6.entity.Product;
import me.ulearn.springboot_module6.service.ProductService;
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

import java.util.List;
import java.util.Optional;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService service;

    private static final Product PRODUCT = new Product(1L, "J", false);

    @Test
    public void assertThatProductCreated() throws Exception {
        Mockito.when(service.createProduct(Mockito.any(Product.class))).thenReturn(PRODUCT);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products")
                        .content(objectMapper.writeValueAsString(PRODUCT))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(PRODUCT)));
    }

    @Test
    public void assertThatCustomerUpdated() throws Exception {
        Mockito.when(service.updateProduct(Mockito.eq(1L), Mockito.any(Product.class))).thenReturn(ResponseEntity.of(Optional.of(PRODUCT)));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/products/1")
                        .content(objectMapper.writeValueAsString(PRODUCT))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(PRODUCT)));
    }

    @Test
    public void assertThatProductReturned() throws Exception {
        Mockito.when(service.getProduct(1L)).thenReturn(ResponseEntity.of(Optional.of(PRODUCT)));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(PRODUCT)));
    }

    @Test
    public void assertThatProductsReturned() throws Exception {
        Mockito.when(service.getProducts()).thenReturn(List.of(PRODUCT));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(List.of(PRODUCT))));


    }


    @Test
    public void assertThatProductDeleted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/products/1")
                        .content(objectMapper.writeValueAsString(PRODUCT))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}