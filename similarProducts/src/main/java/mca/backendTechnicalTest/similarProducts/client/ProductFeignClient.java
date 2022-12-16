package mca.backendTechnicalTest.similarProducts.client;

import mca.backendTechnicalTest.similarProducts.model.Product;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE", url = "localhost:3001/product")
public interface ProductFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{productId}/similarids")
    @Cacheable(cacheNames = "similar-ids", key = "#productId")
    List<String> getSimilarIds(@PathVariable("productId") String productId);

    @RequestMapping(method = RequestMethod.GET, value = "/{productId}")
    @Cacheable(cacheNames = "products", key = "#productId")
    Product getProduct(@PathVariable("productId") String productId);
}
