package mca.backendTechnicalTest.similarProducts.controller;

import lombok.extern.slf4j.Slf4j;
import mca.backendTechnicalTest.similarProducts.client.ProductFeignClient;
import mca.backendTechnicalTest.similarProducts.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/product")
public class SimilarProductController {

    @Autowired
    ProductFeignClient productFeignClient;

    @GetMapping("/{productId}/similar")
    public ArrayList<Product> similarProducts(@PathVariable String productId){
        SimilarProductProcessor similarProductProcessor = new SimilarProductProcessor(productFeignClient);
        return similarProductProcessor.process(productId);
    }

}