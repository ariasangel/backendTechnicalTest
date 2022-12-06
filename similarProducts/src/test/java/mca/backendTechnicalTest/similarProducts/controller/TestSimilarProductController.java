package mca.backendTechnicalTest.similarProducts.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestSimilarProductController {

    @Autowired
    private SimilarProductController similarProductController;

    @Test
    public void contextLoads() throws Exception {
        Assert.assertNotNull(similarProductController);
    }
}
