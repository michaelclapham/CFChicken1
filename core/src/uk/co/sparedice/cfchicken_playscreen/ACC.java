/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.co.sparedice.cfchicken_playscreen;

import java.util.ArrayList;

/**
 *
 * @author Michael
 */
public class ACC {
    
    private static ArrayList<ControlActionListener> cals = new ArrayList<ControlActionListener>();
    
    public static void broadcastAction(String actionName){
        for(ControlActionListener cal : cals){
            cal.onAction(actionName);
        }
    }
    
    public static void addActionListener(ControlActionListener cal){
        cals.add(cal);
    }
    
}
