/**
 * Tern-ui Library.
 * 
 * @author fancimage
 * @Copyright 2010 qiao_xf@163.com Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tern.ui.freemarker.directive;

import java.io.IOException;
import java.util.Map;

import com.tern.ui.freemarker.Directives;

import freemarker.core.Environment;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateScalarModel;

public class Block implements TemplateDirectiveModel
{
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException 
	{		
		String name = Directives.getStringParam(env, params, "name");		
		name = Overrides.BLOCK_NAME_PRE+name;
		
		TemplateModel tm = env.getCurrentNamespace().get(name);
		if(tm instanceof AdapterTemplateModel)
		{
			Object o = ((AdapterTemplateModel)tm).getAdaptedObject(null);
			if(o instanceof TemplateDirectiveBody)
			{
				((TemplateDirectiveBody)o).render(env.getOut());
				return;
			}
		}
		else if(tm instanceof TemplateDirectiveBody)
		{
			((TemplateDirectiveBody)tm).render(env.getOut());
			return;
		}
		else if(tm instanceof TemplateScalarModel)
		{
			String tpl = ((TemplateScalarModel)tm).getAsString();
			if(tpl!=null)
			{
				env.getOut().append(tpl);
				return;
			}
		}
		
		if(body!=null) body.render(env.getOut());
	}

}
