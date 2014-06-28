/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.controller;

import javax.servlet.http.HttpServletRequest;
import ua.kpi.project4.Constants;


/**
 * In object-oriented programming, the command pattern is a behavioral design
 * pattern in which an object is used to represent and encapsulate all the
 * information needed to call a method at a later time. 
 * 
 * This information
 * includes the method name, the object that owns the method and values for the
 * method parameters.
 */
public interface Action extends Constants {

    String execute(View view);
}
