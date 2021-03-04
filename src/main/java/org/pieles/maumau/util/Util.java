/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pieles.maumau.util;

import java.util.ArrayList;

/**
 *
 * @author jpi
 */
public class Util {
    
    public static String getLast(ArrayList<String> list) {
        return list.get(list.size() - 1);
    }
    
}
