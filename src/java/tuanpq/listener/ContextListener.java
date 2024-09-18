/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanpq.listener;

import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import tuanpq.utils.PropertiesFileHelper;

/**
 * Web application lifecycle listener.
 *
 * @author LENOVO
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        //get paramter context scope
        String siteMapLocation = 
                context.getInitParameter(
                        "SITEMAP_PROPERTIES_FILE_LOCATION");
        String authenticationPropertyLocation =
                context.getInitParameter(
                        "AUTHENTICATION_PROPERTIES_FILE_LOCATION");
        //load file properties
        Properties siteMapProperty =
                PropertiesFileHelper.getProperties(context,siteMapLocation);
        Properties authenticationProperty = 
                PropertiesFileHelper.getProperties(context, authenticationPropertyLocation);
        //save intro attibute
        context.setAttribute("SITE_MAP", siteMapProperty);
        context.setAttribute("AUTHENTICATION_LIST",authenticationProperty);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       
    }
}
