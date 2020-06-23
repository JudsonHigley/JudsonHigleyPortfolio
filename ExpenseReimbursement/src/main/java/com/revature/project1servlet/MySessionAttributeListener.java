package com.revature.project1servlet;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MySessionAttributeListener implements HttpSessionAttributeListener {

    public MySessionAttributeListener() {
    	// No-args
    }
    
    @Override
    public void attributeAdded(HttpSessionBindingEvent sessionBindingEvent) {

        // Get the session
        HttpSession session = sessionBindingEvent.getSession();

        // Log some information
        System.out.println("[SessionAttr] " + new java.util.Date()
                + " Attribute added, session " + session + ": " + sessionBindingEvent.getName()
                + "=" + sessionBindingEvent.getValue());
    }
    
    @Override
    public void attributeRemoved(HttpSessionBindingEvent sessionBindingEvent) {

        // Get the session
        HttpSession session = sessionBindingEvent.getSession();

        // Log some information
        System.out.println("[SessionAttr] " + new java.util.Date()
                + " Attribute removed, session " + session + ": "
                + sessionBindingEvent.getName());
    }
    
    @Override
    public void attributeReplaced(HttpSessionBindingEvent sessionBindingEvent) {

        // Get the session
        HttpSession session = sessionBindingEvent.getSession();

        // Log some information
        System.out.println("[SessionAttr] " + new java.util.Date()
                + " Attribute replaced, session " + session + ": "
                + sessionBindingEvent.getName() + "=" + sessionBindingEvent.getValue());
    }
}