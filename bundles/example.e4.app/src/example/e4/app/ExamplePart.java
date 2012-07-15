package example.e4.app;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;

public class ExamplePart {

	@PostConstruct
	public void createUi(Composite parent) {
		Layout layout = new GridLayout();
		parent.setLayout(layout);
		Label label = new Label(parent, SWT.NONE);
		label.setText("RAP on Eclipse4");
	}
}
