package mca.backendTechnicalTest.similarProducts.controller;

import feign.FeignException;
import mca.backendTechnicalTest.similarProducts.client.ProductFeignClient;
import mca.backendTechnicalTest.similarProducts.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestSimilarProductProcessor {

    private final ProductFeignClient productFeignClient = mock(ProductFeignClient.class);
    private SimilarProductProcessor similarProductProcessor;
    @BeforeEach
    void init(){
        similarProductProcessor = new SimilarProductProcessor(productFeignClient);
    }

    @Test
    void testProcessOk() {
        //given
        String id = "4";

        //when
        ArrayList<Product> expected = new ArrayList<>(
                Arrays.asList(new Product("1", "Trousers", 10, true),
                        new Product("2", "Shirt", 20, true),
                        new Product("3", "Skirt", 30, true)));

        when(productFeignClient.getSimilarIds("4")).thenReturn(new ArrayList<>(Arrays.asList("1", "2", "3")));
        when(productFeignClient.getProduct("1")).thenReturn(new Product("1", "Trousers", 10, true));
        when(productFeignClient.getProduct("2")).thenReturn(new Product("2", "Shirt", 20, true));
        when(productFeignClient.getProduct("3")).thenReturn(new Product("3", "Skirt", 30, true));

        ArrayList<Product> actual = similarProductProcessor.process(id);

        // this.productFeignClient.getSimilarIds(productId); // 4 --> ["1","2","3"]
        //then
        assertEquals(expected, actual);
    }


    @Test
    void testProcessMissingOneValue() {
        //given
        String id = "4";

        //when
        ArrayList<Product> expected = new ArrayList<>(
                Arrays.asList(new Product("1", "Trousers", 10, true),
                        //new Product("2", "Shirt", 20, true),
                        new Product("3", "Skirt", 30, true)));

        when(productFeignClient.getSimilarIds("4")).thenReturn(new ArrayList<>(Arrays.asList("1", "2", "3")));
        when(productFeignClient.getProduct("1")).thenReturn(new Product("1", "Trousers", 10, true));
        when(productFeignClient.getProduct("2")).thenThrow(FeignException.class);
        when(productFeignClient.getProduct("3")).thenReturn(new Product("3", "Skirt", 30, true));

        ArrayList<Product> actual = similarProductProcessor.process(id);

        // this.productFeignClient.getSimilarIds(productId); // 4 --> ["1","2","3"]
        //then
        assertEquals(expected, actual);
    }


    @Test
    void testProcessNoSimilarIds() {
        //given
        String id = "4";

        //when

        when(productFeignClient.getSimilarIds("4")).thenThrow(FeignException.class);

        ArrayList<Product> actual = similarProductProcessor.process(id);

        //then
        assertEquals(new ArrayList<>(), actual);
    }
}
