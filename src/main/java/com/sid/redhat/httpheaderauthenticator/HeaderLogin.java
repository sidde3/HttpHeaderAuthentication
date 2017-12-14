/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sid.redhat.httpheaderauthenticator;

import java.security.acl.Group;
import javax.security.auth.login.LoginException;
import org.jboss.logging.Logger;
import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

/**
 *
 * @author sidde
 */
public class HeaderLogin extends UsernamePasswordLoginModule{
    private static final Logger log = Logger.getLogger(HeaderLogin.class);

    @Override
    protected boolean validatePassword(String password, String username){
        if(username.length() == 0 || password.length() == 0){
            log.info("Username and password is not correct");
            return false;
        }
        log.info("username seems to be "+username);
        return true;
    }
    @Override
    protected Group[] getRoleSets() throws LoginException {        
        Group[] roleSets = {new SimpleGroup("Roles")};
        roleSets[0].addMember(new SimplePrincipal("JBossAdmin"));
        log.info(roleSets);
        return roleSets;
    }

    @Override
    protected String getUsersPassword() throws LoginException {
        return getUsername();
    }
    
}
