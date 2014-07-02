/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.controller;

/**
 * Represents null action.
 */
public class ActionNoAction implements Action {

    @Override
    public String execute(View view) {
        return Pages.INDEX;
    }

}
