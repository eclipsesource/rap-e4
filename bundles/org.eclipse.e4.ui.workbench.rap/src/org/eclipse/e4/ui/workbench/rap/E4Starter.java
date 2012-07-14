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

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.ui.internal.workbench.swt.E4Application;
import org.eclipse.equinox.app.IApplication;
import org.osgi.framework.Bundle;


public class E4Starter {

	private static final String EXT_PT_PRODUCTS = "org.eclipse.core.runtime.products";

	public static void createAndRunApplication(String productName) throws Exception {
		IConfigurationElement element = findProductExtension(productName);
		if (element == null) {
			throw new IllegalArgumentException("Product not found: " + productName);
		}
		Bundle bundle = getContributingBundle(element);
		Map<String, String> properties = readProperties(element);
		E4ApplicationContext context = new E4ApplicationContext(bundle,properties);
		IApplication application = new E4Application();
		application.start( context );
	}

	private static Bundle getContributingBundle(IConfigurationElement element) {
		String contributorName = element.getContributor().getName();
        return Platform.getBundle( contributorName );
	}

	private static IConfigurationElement findProductExtension(String productName) {
		for (IExtension extension : getAllProductExtensions()) {
			for (IConfigurationElement element : extension.getConfigurationElements()) {
				if ("product".equals(element.getName())) {
					String name = element.getAttribute("name");
					if (productName.equals(name)) {
						return element;
					}
				}
			}
		}
		return null;
	}
	
	private static IExtension[] getAllProductExtensions() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = registry
				.getExtensionPoint(EXT_PT_PRODUCTS);
		return extensionPoint.getExtensions();
	}

	private static Map<String, String> readProperties(IConfigurationElement element) {
		IConfigurationElement[] propertyElements = element.getChildren("property");
		Map<String, String> properties = new HashMap<String, String>();
		for (IConfigurationElement childElement : propertyElements) {
			String name = childElement.getAttribute("name");
			String value = childElement.getAttribute("value");
			if (name != null && value != null) {
				properties.put(name, value);
			}
		}
		return properties;
	}

}
