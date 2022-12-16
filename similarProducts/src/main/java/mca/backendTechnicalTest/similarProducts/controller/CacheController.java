package mca.backendTechnicalTest.similarProducts.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@Slf4j
@RequestMapping("/check")
public class CacheController {
    private final CacheManager cacheManager;

    public CacheController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @GetMapping("/{cacheId}")
    public ConcurrentHashMap testCache(@PathVariable String cacheId){
        return (ConcurrentHashMap<?, ?>) this.cacheManager.getCache(cacheId).getNativeCache();
    }

    @GetMapping(value = "/{cacheId}/remove")
    public void clearCache(@PathVariable String cacheId){
        for(String name:cacheManager.getCacheNames()){
            this.cacheManager.getCache(cacheId).clear();
        }
    }
}
