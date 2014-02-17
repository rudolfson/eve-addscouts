package add;

import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.SwingWorker;

public class ScoutAddingSwingWorker
    extends SwingWorker<String, String> {

    private JEditorPane pane;

    public ScoutAddingSwingWorker( JEditorPane pane ) {
        this.pane = pane;
    }

    @Override
    protected String doInBackground()
        throws Exception {
        Thread.sleep( 1000L );
        publish( "1" );
        Thread.sleep( 1000L );
        publish( "2" );
        Thread.sleep( 1000L );
        publish( "3" );
        Thread.sleep( 1000L );
        publish( "4" );
        Thread.sleep( 1000L );
        publish( "5" );
        Thread.sleep( 1000L );
        publish( "6" );
        Thread.sleep( 1000L );
        publish( "7" );
        Thread.sleep( 1000L );
        publish( "8" );

        return "finished";
    }

    @Override
    protected void process( List<String> chunks ) {
        for ( String text : chunks ) {
            this.pane.setText( text );
        }

    }

}
