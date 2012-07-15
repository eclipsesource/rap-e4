package example.e4.app;

import org.eclipse.rwt.application.ApplicationConfiguration;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration<?> registration;

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		ApplicationConfiguration configuration = new ExampleConfiguration();
		registration = bundleContext.registerService(
				ApplicationConfiguration.class.getName(), configuration, null);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		registration.unregister();
	}

}
