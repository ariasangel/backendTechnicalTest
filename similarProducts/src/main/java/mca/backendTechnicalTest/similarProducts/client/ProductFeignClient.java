package mca.backendTechnicalTest.similarProducts.client;

import mca.backendTechnicalTest.similarProducts.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE", url = "localhost:3001/product")
public interface ProductFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{productId}/similarids")
    List<String> getSimilarIds(@PathVariable("productId") String productId);

    @RequestMapping(method = RequestMethod.GET, value = "/{productId}")
    Product getProduct(@PathVariable("productId") String productId);
}
