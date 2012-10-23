/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package example.e4.app.handlers;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Shell;

import com.example.e4.rcp.todo.model.ITodoModel;
import com.example.e4.rcp.todo.model.Todo;


public class AddHandler {

  @Inject
  ITodoModel model;

  @Execute
  public void execute( IEclipseContext context, @Named( IServiceConstants.ACTIVE_SHELL ) Shell shell )
    throws InvocationTargetException, InterruptedException
  {
    InputDialog dialog = new InputDialog( shell, "New item", "Item summary:", "", null );
    if( dialog.open() == Dialog.OK ) {
      Todo todo = new Todo( 0, dialog.getValue(), "", false, null );
      model.saveTodo( todo );
    }    
  }

}
