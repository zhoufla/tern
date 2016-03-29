/**
 * Tern Framework.
 * 
 * @author fancimage
 * @Copyright 2010 qiao_xf@163.com Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tern.util;

import javax.servlet.ServletContext;

import com.tern.web.Template;
import com.tern.web.routes.RouteSet;

abstract public class TernContext
{
	protected static TernContext current;// = new TernContext();
	protected static ServletContext context;
	
    public static TernContext current()
    {
    	return current;
    }
    
    public String getResourcePath()
    {
    	return config.getConfigurationPath();
    }
    
    public String getContextPath()
    {
    	return config.getRoot();
    }
    
    abstract public Template getTemplate();
    abstract public RouteSet getRouter();
    
    public static ServletContext getServletContext()
    {
    	return context;
    }
}
