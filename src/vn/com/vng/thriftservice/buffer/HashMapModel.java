/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.vng.thriftservice.buffer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import vn.com.vng.thriftservice.GetResult;

/**
 *
 *
 * @author ductn
 */
public class HashMapModel {

    private static HashMapModel instance = new HashMapModel();

    private ConcurrentHashMap<String, String> hashMap;

    private HashMapModel() {
        hashMap = new ConcurrentHashMap();
    }

    public static HashMapModel getInstance() {
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
        if (hashMap.containsKey(key)) {
            String value = hashMap.get(key);
            GetResult result = new GetResult(false);
            return result.setValue(value);
        } else {
            return new GetResult(true);
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
        if (hashMap.containsKey(key)) {
            if (!hashMap.get(key).equals(value))
                hashMap.replace(key, value);
        } else {
            hashMap.put(key, value);
        }

        return true;
    }

    /**
     * Perform a DELETE task with given key
     *
     * @param key
     * @return true if successful, otherwise false
     */
    public boolean removeData(String key) {
        if (hashMap.containsKey(key)) {
            hashMap.remove(key);
        }

        return true;
    }
}
