package cn.asilentboy.blog.utils.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@SuppressWarnings({"unchecked", "rawtypes"})
public class RedisCache {

    @Autowired
    @Qualifier("myRedisTemplate")
    public RedisTemplate template;

    public <T> void setCacheObject(final String key, final T obj) {
        template.opsForValue().set(key, obj);
    }

    public <T> void setCacheObject(final String key, final T obj, long time, TimeUnit unit) {
        template.opsForValue().set(key, obj, time, unit);
    }

    public void expire(final String key, long time, TimeUnit unit) {
        template.expire(key, time, unit);
    }

    public <T> T getCacheObject(final String key) {
        return (T) template.opsForValue().get(key);
    }

    public void deleteObject(final String key) {
        template.delete(key);
    }

    public <T> long setCacheList(final String key, final List<T> data) {
        Long count = template.opsForList().rightPushAll(key, data);
        return count == null ? 0 : count;
    }

    public <T> List<T> getCacheList(final String key) {
        return template.opsForList().range(key, 0, -1);
    }

    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> data) {
        BoundSetOperations<String, T> ops = template.boundSetOps(key);
        for(T t : data)
            ops.add(t);
        return ops;
    }

    public <T> Set<T> getCacheSet(final String key) {
        return template.opsForSet().members(key);
    }

    public <T> void setCacheMap(final String key, final Map<String, T> data) {
        if(data != null)
            template.opsForHash().putAll(key, data);
    }

    public <T> Map<String, T> getCacheMap(final String key) {
        return template.opsForHash().entries(key);
    }

    public <T> void setCacheMapValue(final String key, final String hkey, final T value) {
        template.opsForHash().put(key, hkey, value);
    }

    public <T> T getCacheMapValue(final String key, final String hkey) {
        HashOperations<String, String, T> ops = template.opsForHash();
        return ops.get(key, hkey);
    }

    public void delCacheMapValue(final String key, final String hkey) {
        template.opsForHash().delete(key, hkey);
    }

    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hkeys) {
        return template.opsForHash().multiGet(key, hkeys);
    }

    public Collection<String> keys(final String pattern) {
        return template.keys(pattern);
    }
}
