# HttpHeaderAuthentication
Http Header based authentication in Jboss

Please do the following to test it.

1) Build it using maven (mvm clean package)
2) Configure a security-domain
~~~
<security-domain name="Header-Auth" cache-type="default">
        <authentication>
               <login-module code="com.sid.redhat.httpheaderauthenticator.HeaderLogin" flag="required"/>
         </authentication>
</security-domain>
~~~
3) Install the application in EAP.
4) The application needs to send a custom http-header with while making http request. Using curl, you can send it something like below to check the authentication status.
~~~
curl -v --header "x-username:jadmin" --header "x-password:jboss@123" http://localhost:8080/HttpHeaderAuth/
~~~

Note: Username and password can be anything, just avoid empty username and password. 
