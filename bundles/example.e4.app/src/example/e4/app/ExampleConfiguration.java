package example.e4.app;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.e4.ui.internal.workbench.E4Workbench;
import org.eclipse.e4.ui.workbench.rap.E4EntryPointFactory;
import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.Application.OperationMode;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.client.WebClient;


public class ExampleConfiguration implements ApplicationConfiguration {

  @Override
  public void configure( Application application ) {
    Map<String, String> properties = new HashMap<String, String>();
    properties.put( WebClient.PAGE_TITLE, "E4 on RAP" );
    properties.put( E4Workbench.XMI_URI_ARG, "example.e4.app/Application.e4xmi" );
    properties.put( E4Workbench.CSS_URI_ARG, "platform:/plugin/example.e4.app/css/default.css" );
    application.addEntryPoint( "/e4example", new E4EntryPointFactory( properties ), properties );
    application.setOperationMode( OperationMode.SWT_COMPATIBILITY );
  }

}
