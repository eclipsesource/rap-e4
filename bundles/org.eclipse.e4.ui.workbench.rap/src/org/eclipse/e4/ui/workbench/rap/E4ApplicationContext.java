/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ralf Sternberg - initial API and implementation
 *******************************************************************************/
package org.eclipse.e4.ui.workbench.rap;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.Bundle;


final class E4ApplicationContext implements IApplicationContext {

	private final Map<String, Object> arguments;
	private Bundle bundle;
	private Map<String, String> properties;

	public E4ApplicationContext(Bundle bundle, Map<String, String> properties) {
		this.bundle = bundle;
		this.properties = properties;
		arguments = new HashMap<String, Object>(2);
		String[] appArguments = Platform.getApplicationArgs();
		arguments.put(IApplicationContext.APPLICATION_ARGS, appArguments);
	}

	public void applicationRunning() {
		// do nothing
	}

	@SuppressWarnings("rawtypes")
	public Map getArguments() {
		return arguments;
	}

	public String getBrandingApplication() {
		return null;
	}

	public Bundle getBrandingBundle() {
		return bundle;
	}

	public String getBrandingDescription() {
		return null;
	}

	public String getBrandingId() {
		return null;
	}

	public String getBrandingName() {
		return null;
	}

	public String getBrandingProperty(String key) {
		return properties.get(key);
	}

	public void setResult(Object result, IApplication application) {
		// do nothing
	}
}