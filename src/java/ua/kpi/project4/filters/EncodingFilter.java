package ua.kpi.project4.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Filter sets encoding of any servlet response and request in UTF-8
 */
@WebFilter(filterName = "EncodingFilter", urlPatterns = {"/*"})
public class EncodingFilter implements Filter {

    private String encoding = "UTF-8";

    //The filter configuration object. If this value is null, 
    //this filter instance is not currently configured. 
    private FilterConfig filterConfig = null;

    public EncodingFilter() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
//            if (request.getParameter("language") != null) {
//                Locale locale = new Locale(request.getParameter("language"), request.getParameter("region"));
//                request.setAttribute("locale", locale);
//                ((HttpServletRequest) request).getSession().setAttribute("locale", locale);
//            }
            String reqEncoding = request.getCharacterEncoding();
            //Set character encoding
            if (!encoding.equalsIgnoreCase(reqEncoding)) {
                ((HttpServletRequest) request).setCharacterEncoding(encoding);
                response.setCharacterEncoding(encoding);
            }
            chain.doFilter(request, response);
        } catch (IOException | ServletException t) {
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    @Override
    public void init(FilterConfig filterConfig) {
        String encodingParam = filterConfig.getInitParameter("encoding");
        this.filterConfig = filterConfig;
        if (encodingParam != null) {
            encoding = encodingParam;
        }
    }
//
//    public static String getStackTrace(Throwable t) {
//        String stackTrace = null;
//        try {
//            StringWriter sw = new StringWriter();
//            PrintWriter pw = new PrintWriter(sw);
//            t.printStackTrace(pw);
//            pw.close();
//            sw.close();
//            stackTrace = sw.getBuffer().toString();
//        } catch (IOException ex) {
//        }
//        return stackTrace;
//    }
//
//    public void log(String msg) {
//        filterConfig.getServletContext().log(msg);
//    }

}
