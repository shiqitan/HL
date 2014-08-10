/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helper;

/**
 *
 * @author weiyi.ngow.2012
 */
public class Validation {
    
    public static boolean checkIfFieldBlank (String fieldName) {
        if (fieldName == null || fieldName.equals("")) {
            return true;
        }
        return false;
    }
}
