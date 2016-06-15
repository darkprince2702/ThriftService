/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.vng.thriftservice.buffer;

import vn.com.vng.thriftservice.GetResult;

/**
 *
 * @author ductn
 */
public class HashTable implements Buffer {
    
    private static HashTable instance = new HashTable();

    private HashTableModel hashTableModel;

    private HashTable() {
        hashTableModel = HashTableModel.getInstance();
    }

    public static HashTable getInstance() {
        return instance;
    }

    @Override
    public GetResult getData(String key) {
        return hashTableModel.getData(key);
    }

    @Override
    public boolean setData(String key, String value) {
        return hashTableModel.setData(key, value);
    }

    @Override
    public boolean removeData(String key) {
        return hashTableModel.removeData(key);
    }
}
