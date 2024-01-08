package me.ulearn.springboot_module6.test.source;

import me.ulearn.springboot_module6.entity.Customer;
import me.ulearn.springboot_module6.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

public class CustomerTest {
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
    public void isAdult() {
        Assertions.assertEquals(true, CUSTOMER.isAdult());
    }

    @Test
    public void getId() {
        Assertions.assertEquals(1L, CUSTOMER.getId());
    }

    @Test
    public void getName() {
        Assertions.assertEquals("Peter", CUSTOMER.getName());
    }

    @Test
    public void getSurname() {
        Assertions.assertEquals("Kerko", CUSTOMER.getSurname());
    }

    @Test
    public void getBirthDate() {
        Assertions.assertEquals(LocalDate.ofYearDay(1994, 8), CUSTOMER.getBirthDate());
    }

    @Test
    public void getEmail() {
        Assertions.assertEquals("PeterKerko@mail.ru", CUSTOMER.getEmail());
    }

    @Test
    public void getPassword() {
        Assertions.assertEquals("123456", CUSTOMER.getPassword());
    }

    @Test
    public void getCart() {
        Assertions.assertEquals(Arrays.asList(PRODUCT, PRODUCT), CUSTOMER.getCart());
    }
}