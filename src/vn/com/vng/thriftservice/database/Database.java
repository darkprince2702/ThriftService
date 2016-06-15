/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.vng.thriftservice.database;

import vn.com.vng.thriftservice.GetResult;

/**
 *
 * @author ductn
 */
public interface Database {

    public GetResult getData(String key);

    public boolean setData(String key, String value);

    public boolean removeData(String key);
}
