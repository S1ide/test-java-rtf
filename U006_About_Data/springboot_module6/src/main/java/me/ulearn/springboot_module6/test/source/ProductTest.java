package me.ulearn.springboot_module6.test.source;

import me.ulearn.springboot_module6.entity.Customer;
import me.ulearn.springboot_module6.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

public class ProductTest {
    private static final Customer CUSTOMER = new Customer();
    private static final Product PRODUCT = new Product();

    @BeforeAll
    public static void setup() {
        PRODUCT.setId(1L);
        PRODUCT.setName("First");
        PRODUCT.setIsAdult(true);

        CUSTOMER.setBirthDate(LocalDate.ofYearDay(1994, 8));
        CUSTOMER.setName("Peter");
        CUSTOMER.setSurname("Kerko");
        CUSTOMER.setEmail("PeterKerko@mail.ru");
        CUSTOMER.setId(1L);
        CUSTOMER.setPassword("123456");
        CUSTOMER.setCart(Arrays.asList(PRODUCT, PRODUCT));
    }

    @Test
    public void getId() {
        Assertions.assertEquals(1L, PRODUCT.getId());
    }

    @Test
    public void getName() {
        Assertions.assertEquals("First", PRODUCT.getName());
    }

    @Test
    public void getIsAdult() {
        Assertions.assertEquals(true, PRODUCT.getIsAdult());
    }
}