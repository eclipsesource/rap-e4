package example.e4.app;

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
	public void configure(Application application) {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put(WebClient.PAGE_TITLE, "E4 on RAP");
		IEntryPointFactory entryPointFactory = new E4EntryPointFactory("example.e4.app");
		application.addEntryPoint("/e4example", entryPointFactory, properties);
		application.setOperationMode( OperationMode.SWT_COMPATIBILITY);
	}

}
