/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.vng.thriftservice.cache;

/**
 *
 * @author ductn
 */
public class CacheFactory {

    public Cache getCache(String cacheType) {
        if (cacheType.isEmpty()) {
            return null;
        } else if (cacheType.contains("SpyMemcached")) {
            return SpyMemcached.getInstance();
        } else if (cacheType.contains("XMemcached")) {
            return XMemcached.getInstance();
        } else {
            return null;
        }
    }
}
