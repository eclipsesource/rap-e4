package example.e4.app.parts;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import com.example.e4.rcp.todo.model.ITodoModel;
import com.example.e4.rcp.todo.model.Todo;

import example.e4.app.events.MyEventConstants;


public class TodoOverviewPart {

  private Button btnNewButton;
  private TableViewer viewer;
  @Inject
  UISynchronize sync;
  @Inject
  ESelectionService service;
  @Inject
  ITodoModel model;

  @Inject
  public TodoOverviewPart( Composite parent ) {
    parent.setLayout( new GridLayout( 1, false ) );
    createLoadButton( parent );
    createSearchField( parent );
    createViewer( parent );
  }

  private void createLoadButton( Composite parent ) {
    btnNewButton = new Button( parent, SWT.NONE );
    btnNewButton.addSelectionListener( new SelectionAdapter() {

      @Override
      public void widgetSelected( SelectionEvent e ) {
        viewer.setInput( model.getTodos() );
      }
    } );
    btnNewButton.setText( "Load Data" );
  }

  private void createSearchField( Composite parent ) {
    Text search = new Text( parent, SWT.SEARCH | SWT.CANCEL | SWT.ICON_SEARCH );
    search.setLayoutData( new GridData( SWT.FILL, SWT.CENTER, false, false, 1, 1 ) );
    search.setMessage( "Filter" );
    search.addSelectionListener( new SelectionAdapter() {

      public void widgetDefaultSelected( SelectionEvent e ) {
        if( e.detail == SWT.CANCEL ) {
          Text text = ( Text )e.getSource();
          text.setText( "" );
          //
        }
      }
      // MORE...
    } );
  }

  private void createViewer( Composite parent ) {
    viewer = new TableViewer( parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION );
    Table table = viewer.getTable();
    table.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, true, 1, 1 ) );
    table.setHeaderVisible( true );
    table.setLinesVisible( true );
    createSummaryColumn();
    createDetailsColumn();
    viewer.setContentProvider( ArrayContentProvider.getInstance() );
    viewer.addSelectionChangedListener( new ISelectionChangedListener() {
      @Override
      public void selectionChanged( SelectionChangedEvent event ) {
        IStructuredSelection selection = ( IStructuredSelection )viewer.getSelection();
        service.setSelection( selection.getFirstElement() );
      }
    } );
  }

  private void createSummaryColumn() {
    TableViewerColumn column = new TableViewerColumn( viewer, SWT.NONE );
    column.getColumn().setWidth( 200 );
    column.getColumn().setText( "Summary" );
    column.setLabelProvider( new ColumnLabelProvider() {
      @Override
      public String getText( Object element ) {
        Todo todo = ( Todo )element;
        return todo.getSummary();
      }
    } );
    column.setEditingSupport( new EditingSupport( viewer ) {

      @Override
      protected void setValue( Object element, Object value ) {
        Todo todo = ( Todo )element;
        todo.setSummary( String.valueOf( value ) );
        viewer.refresh();
      }

      @Override
      protected Object getValue( Object element ) {
        Todo todo = ( Todo )element;
        return todo.getSummary();
      }

      @Override
      protected CellEditor getCellEditor( Object element ) {
        return new TextCellEditor( viewer.getTable(), SWT.NONE );
      }

      @Override
      protected boolean canEdit( Object element ) {
        return true;
      }
    } );
  }

  private void createDetailsColumn() {
    TableViewerColumn column = new TableViewerColumn( viewer, SWT.NONE );
    column.getColumn().setWidth( 300 );
    column.getColumn().setText( "Description" );
    column.setLabelProvider( new ColumnLabelProvider() {
      @Override
      public String getText( Object element ) {
        Todo todo = ( Todo )element;
        return todo.getDescription();
      }
    } );
  }

  @Inject
  @Optional
  private void getNotified( @UIEventTopic( MyEventConstants.TOPIC_TODO_DATA_UPDATE ) String topic )
  {
    if( viewer != null ) {
      viewer.setInput( model.getTodos() );
    }
  }

  @Focus
  private void setFocus() {
    btnNewButton.setFocus();
  }

}
