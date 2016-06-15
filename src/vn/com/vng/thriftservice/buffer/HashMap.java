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
public class HashMap implements Buffer {

    private static HashMap instance = new HashMap();

    private HashMapModel hashMapModel;

    private HashMap() {
        hashMapModel = HashMapModel.getInstance();
    }

    public static HashMap getInstance() {
        return instance;
    }

    @Override
    public GetResult getData(String key) {
        return hashMapModel.getData(key);
    }

    @Override
    public boolean setData(String key, String value) {
        return hashMapModel.setData(key, value);
    }

    @Override
    public boolean removeData(String key) {
        return hashMapModel.removeData(key);
    }
}
