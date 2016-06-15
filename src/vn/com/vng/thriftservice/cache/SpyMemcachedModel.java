/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.vng.thriftservice.cache;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;
import vn.com.vng.thriftservice.GetResult;

/**
 * Handle connection to the Memcached
 *
 * @author ductn
 */
public class SpyMemcachedModel {

    private static SpyMemcachedModel instance = new SpyMemcachedModel();

    private MemcachedClient mcc;

    private SpyMemcachedModel() {
        try {
            mcc = new MemcachedClient(new InetSocketAddress("localhost", 11211));
            mcc.flush();
        } catch (IOException ex) {
            System.out.println("Error while connecting to memcached: " + ex.toString());
        }
    }

    public static SpyMemcachedModel getInstance() {
        return instance;
    }

    /**
     * *
     * Perform a GET with given key
     *
     * @param key
     * @return value of the given key
     */
    public GetResult getData(String key) {
        Object result = mcc.get(key);

        if (result == null) {
            return new GetResult(true);
        } else {
            return (new GetResult(false)).setValue(result.toString());
        }
    }

    /**
     * Perform a SET task, with given key and value
     *
     * @param key
     * @param value
     * @return true if successful, otherwise false
     */
    public boolean setData(String key, String value) {
        try {
            OperationFuture<Boolean> result = mcc.set(key, 0, value);
            return result.get();
        } catch (InterruptedException | ExecutionException ex) {
            return false;
        }
    }

    /**
     * Perform a DELETE task with given key
     *
     * @param key
     * @return true if successful, otherwise false
     */
    public boolean removeData(String key) {
        try {
            OperationFuture<Boolean> result = mcc.delete(key);
            return result.get();
        } catch (InterruptedException | ExecutionException ex) {
            return false;
        }
    }
}
