package com.ifpb.RedisContactBook.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class CheckCache {

    private static final Logger logger = LoggerFactory.getLogger(CheckCache.class);
    private final CacheManager cacheManager;

    public CheckCache(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void checkCacheForAll() {
        var cache = cacheManager.getCache("contacts");
        assert cache != null;
        var value = cache.get(SimpleKey.EMPTY); //O SimpleKey.EMPTY é utilizado para métodos sem parâmetros
        if (value != null) {
            logger.info("Usando cache para findAll");
        } else {
            logger.info("Nenhum cache encontrado para findAll");
        }
    }

    public void checkCacheForId(String id) {
        var cache = cacheManager.getCache("contacts");
        assert cache != null;
        var value = cache.get(id); // Usa o id como chave para buscar o valor no cache
        if (value != null) {
            logger.info("Usando cache para findById");
        } else {
            logger.info("Nenhum cache encontrado para findById");
        }
    }
}