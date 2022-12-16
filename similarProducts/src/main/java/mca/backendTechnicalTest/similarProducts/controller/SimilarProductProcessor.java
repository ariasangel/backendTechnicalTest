package mca.backendTechnicalTest.similarProducts.controller;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import mca.backendTechnicalTest.similarProducts.client.ProductFeignClient;
import mca.backendTechnicalTest.similarProducts.model.Product;

import java.util.ArrayList;


@Slf4j
@AllArgsConstructor
public class SimilarProductProcessor {

    private ProductFeignClient productFeignClient;

    public ArrayList<Product> process(String productId) {
        ArrayList<String> similarProductIds = getSimilarIds(productId);
        return getProducts(similarProductIds);
    }

    private ArrayList<String> getSimilarIds(String productId) {
        ArrayList<String> similarIds = new ArrayList<>();
        try{
            similarIds = (ArrayList<String>) this.productFeignClient.getSimilarIds(productId);
            log.info("Retrieved ids = " + similarIds);
        } catch (FeignException e){
            log.error("Could not retrieve similar ids (" + productId + ")");
        }
        return similarIds;
    }

    private ArrayList<Product> getProducts(ArrayList<String> productIds) {
        ArrayList<Product> similarProducts = new ArrayList<>();

        for (String id : productIds) {
            Product product = getProduct(id);
            if (null != product)
                similarProducts.add(product);
        }

        return similarProducts;
    }

    private Product getProduct(String id){
        Product product = null;
        try{
            product = this.productFeignClient.getProduct(id);
        } catch (FeignException e){
            log.error("Product Not found");
        }
        return product;
    }
}
