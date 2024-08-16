// package com.ifpb.RedisContactBook.config;
// import org.springframework.cache.CacheManager;
// import org.springframework.cache.annotation.EnableCaching;
// import org.springframework.cache.annotation.Cacheable;
// import org.springframework.cache.interceptor.CacheInterceptor;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.redis.connection.RedisConnectionFactory;
// import org.springframework.data.redis.cache.RedisCacheManager;
// import org.springframework.data.redis.cache.RedisCacheConfiguration;
// import org.springframework.data.redis.serializer.StringRedisSerializer;
// import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
// import org.springframework.data.redis.serializer.RedisSerializationContext;

// import java.time.Duration;

// @Configuration
// @EnableCaching
// public class CacheConfig {

//     @Bean
//     public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//         RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
//             .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
//             .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer()))
//             .entryTtl(Duration.ofSeconds(60)); // Tempo de vida do cache

//         return RedisCacheManager.builder(redisConnectionFactory)
//             .cacheDefaults(cacheConfig)
//             .build();
//     }

//     // Configuração de CacheInterceptor para monitorar o uso do cache
//     @Bean
//     public CacheInterceptor cacheInterceptor(CacheManager cacheManager) {
//         CacheInterceptor cacheInterceptor = new CacheInterceptor();
//         cacheInterceptor.setCacheManager(cacheManager);
//         return cacheInterceptor;
//     }
// }



