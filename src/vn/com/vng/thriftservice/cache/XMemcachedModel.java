/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.vng.thriftservice.cache;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.rubyeye.xmemcached.*;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;
import vn.com.vng.thriftservice.GetResult;

/**
 *
 * @author ductn
 */
public class XMemcachedModel {

    private static XMemcachedModel instance = new XMemcachedModel();

    private XMemcachedClient client;

    private XMemcachedModel() {
        XMemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("localhost:11211"));
        try {
            client = (XMemcachedClient) builder.build();
            client.setPrimitiveAsString(true);
        } catch (IOException ex) {
            Logger.getLogger(XMemcachedModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static XMemcachedModel getInstance() {
        return instance;
    }

    public GetResult getData(String key) {
        String result;
        try {
            result = client.get(key);
        } catch (TimeoutException | InterruptedException | MemcachedException ex) {
            return new GetResult(true);
        }
        return (result == null || result.isEmpty()) ? new GetResult(true) : (new GetResult(false)).setValue(result);
    }

    public boolean setData(String key, String value) {
        try {
            return client.set(key, 0, value);
        } catch (TimeoutException | InterruptedException | MemcachedException ex) {
            return false;
        }
    }

    public boolean removeData(String key) {
        try {
            return client.delete(key);
        } catch (TimeoutException | InterruptedException | MemcachedException ex) {
            return false;
        }
    }
}
