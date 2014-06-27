/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.kpi.project4.dao.applications;

import java.util.List;
import ua.kpi.project4.model.Applications;

/**
 *
 * @author User
 */
public interface ApplicationsDAO {
    List<Applications> getAllApplications();
    Applications getApplicationsById(int id);
    int insertApplications(Applications applications);
    void updateApplications(Applications applications);
    void deleteApplications(Applications applications);
}
