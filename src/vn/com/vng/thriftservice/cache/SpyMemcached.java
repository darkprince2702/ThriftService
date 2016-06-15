/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.vng.thriftservice.cache;

import vn.com.vng.thriftservice.GetResult;

/**
 *
 * @author ductn
 */
public class SpyMemcached implements Cache {

    private static SpyMemcached instance = new SpyMemcached();

    private SpyMemcachedModel memcachedModel;

    private SpyMemcached() {
        memcachedModel = SpyMemcachedModel.getInstance();
    }

    public static SpyMemcached getInstance() {
        return instance;
    }

    @Override
    public GetResult getData(String key) {
        return memcachedModel.getData(key);
    }

    @Override
    public boolean setData(String key, String value) {
        return memcachedModel.setData(key, value);
    }

    @Override
    public boolean removeData(String key) {
        return memcachedModel.removeData(key);
    }
}
