/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.vng.thriftservice.buffer;

import java.util.Hashtable;
import vn.com.vng.thriftservice.GetResult;

/**
 *
 * @author ductn
 */
public class HashTableModel {

    private static HashTableModel instance = new HashTableModel();

    private java.util.Hashtable<String, String> hashTable;

    private HashTableModel() {
        hashTable = new Hashtable();
    }

    public static HashTableModel getInstance() {
        return instance;
    }

    public GetResult getData(String key) {
        if (hashTable.containsKey(key)) {
            return (new GetResult(false)).setValue(hashTable.get(key));
        } else {
            return new GetResult(true);
        }
    }

    public boolean setData(String key, String value) {
        if (hashTable.containsKey(key)) {
            if (!hashTable.get(key).equals(value)) {
                hashTable.replace(key, value);
            }
        } else {
            hashTable.put(key, value);
        }

        return true;
    }

    public boolean removeData(String key) {
        if (hashTable.containsKey(key)) {
            hashTable.remove(key);
        }

        return true;
    }
}
