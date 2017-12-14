/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sid.redhat.httpheaderauthenticator;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.Realm;
import org.apache.catalina.authenticator.AuthenticatorBase;
import org.apache.catalina.connector.Request;
import org.apache.catalina.deploy.LoginConfig;
import org.jboss.logging.Logger;
/**
 *
 * @author sidde
 */
public class HTTPAuthenticator extends AuthenticatorBase{
    protected static Logger log = Logger.getLogger(HTTPAuthenticator.class);
    @Override
    protected boolean authenticate(Request rqst, HttpServletResponse hsr, LoginConfig lc) throws IOException {
        Principal principal = rqst.getUserPrincipal();
        if (principal != null) {
            log.info("Already authenticated '" + principal.getName() + "'");
            return true;
        }
        String username = rqst.getHeader("x-username");
        log.info(username);
        String password = rqst.getHeader("x-password");
        log.info(password.hashCode());
        Realm realm = context.getRealm();
        principal = realm.authenticate(username,password);
        
        if (principal == null) {
            hsr.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        log.info(principal);
        rqst.setUserPrincipal(principal);
        register(rqst, hsr, principal,"HTTP-Header",username,password);
        
        return true;
    }
    
}
