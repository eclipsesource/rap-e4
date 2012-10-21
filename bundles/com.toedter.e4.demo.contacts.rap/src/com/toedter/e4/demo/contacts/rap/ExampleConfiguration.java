package com.toedter.e4.demo.contacts.rap;

import org.eclipse.e4.ui.internal.workbench.E4Workbench;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.e4.ui.workbench.rap.E4EntryPointFactory;
import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.Application.OperationMode;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.client.WebClient;
import org.eclipse.rap.rwt.lifecycle.IEntryPointFactory;


public class ExampleConfiguration implements ApplicationConfiguration {

  @Override
  public void configure( Application application ) {
    Map<String, String> properties = new HashMap<String, String>();
    properties.put( WebClient.PAGE_TITLE, "Contacts on RAP" );
    properties.put( E4Workbench.XMI_URI_ARG, "com.toedter.e4.demo.contacts.generic/Application.e4xmi" );
    IEntryPointFactory entryPointFactory = new E4EntryPointFactory( properties );
    application.addEntryPoint( "/contacts", entryPointFactory, properties );
    application.setOperationMode( OperationMode.SWT_COMPATIBILITY );
  }

}
