package ua.kpi.project4.dao.applications;

import java.util.List;
import ua.kpi.project4.model.Applications;

/**
 * General interface for getting DAOs of
 */
public interface ApplicationsDAO {

    List<Applications> getAllApplications();

    Applications getApplicationsById(int id);

    int insertApplications(Applications applications);

    void updateApplications(Applications applications);

    void deleteApplications(Applications applications);
}
