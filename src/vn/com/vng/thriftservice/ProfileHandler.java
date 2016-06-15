/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.vng.thriftservice;

import org.apache.thrift.TException;
import vn.com.vng.thriftservice.database.*;
import vn.com.vng.thriftservice.cache.*;
import vn.com.vng.thriftservice.buffer.*;

/**
 *
 * @author ductn
 */
public class ProfileHandler implements ProfileService.Iface {

    private Database database;
    private Cache cache;
    private Buffer buffer;

    public ProfileHandler() {
        DatabaseFactory databaseFactory = new DatabaseFactory();
        database = databaseFactory.getDatabase("LevelDB");

        CacheFactory cacheFactory = new CacheFactory();
        cache = cacheFactory.getCache("SpyMemcached");

        BufferFactory bufferFactory = new BufferFactory();
        buffer = bufferFactory.getCache("HashTable");
    }

    @Override
    public GetResult getData(String key) throws TException {
        GetResult bufferResult, cacheResult, dbResult;
        bufferResult = buffer.getData(key);
        if (bufferResult.isNull) {    // Not found in buffer
            cacheResult = cache.getData(key);
            if (cacheResult.isNull) {    // Not found in cache
                dbResult = database.getData(key);
                if (dbResult.isNull) {    // Not found in database
                    return dbResult;
                } else {    // Found in database, update cache and buffer
                    cache.setData(key, dbResult.getValue());
                    buffer.setData(key, dbResult.getValue());
                    return dbResult;
                }
            } else {    // Found in cache, update buffer
                buffer.setData(key, cacheResult.getValue());
                return cacheResult;
            }
        } else {
            return bufferResult;
        }
    }

    @Override
    public boolean setData(String key, String value) throws TException {
        boolean r1 = database.setData(key, value);
        boolean r2 = cache.setData(key, value);
        boolean r3 = buffer.setData(key, value);
        return r1 && r2 && r3;
    }

    @Override
    public boolean removeData(String key) throws TException {
        boolean r1 = database.removeData(key);
        boolean r2 = cache.removeData(key);
        boolean r3 = buffer.removeData(key);
        return r1 && r2 && r3;
    }
}
