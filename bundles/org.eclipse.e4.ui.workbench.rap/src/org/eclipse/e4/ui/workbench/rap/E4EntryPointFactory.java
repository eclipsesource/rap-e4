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

import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.internal.workbench.WorkbenchLogger;
import org.eclipse.rwt.lifecycle.IEntryPoint;
import org.eclipse.rwt.lifecycle.IEntryPointFactory;
import org.eclipse.swt.widgets.Display;

public class E4EntryPointFactory implements IEntryPointFactory {

	private static final String PLUGIN_ID = "org.eclipse.e4.ui.workbench.rap";

	private String productName;

	public E4EntryPointFactory(String productName) {
		this.productName = productName;
	}

	public IEntryPoint create() {
		return new IEntryPoint() {

			public int createUI() {
				new Display();
				try {
					E4Starter.createAndRunApplication(productName);
				} catch (Exception exception) {
					logProblem(exception);
				}
				return 0;
			}
		};
	}

	private void logProblem(Throwable problem) {
		String message = "Error when starting application, productName="
				+ productName;
		Logger logger = new WorkbenchLogger(PLUGIN_ID);
		logger.error(new RuntimeException(message, problem));
	}

}
