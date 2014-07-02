package ua.kpi.project4.controller;

import ua.kpi.project4.Constants;

/**
 * In object-oriented programming, the command pattern is a behavioral design
 * pattern in which an object is used to represent and encapsulate all the
 * information needed to call a method at a later time.
 *
 * This information includes the method name, the object that owns the method
 * and values for the method parameters.
 */
public interface Action extends Constants {

    /**
     * Executes exact actions and returns name of page to redirect.
     *
     * @param view - request and response
     * @return name of page to redirect
     */
    String execute(View view);
}
