package add;

import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.SwingWorker;

public class ScoutAddingSwingWorker
    extends SwingWorker<String, String> {

    private JEditorPane paneToPublish;
    private List<String> pilots;
    private List<String> urls;
    private String password;

    public ScoutAddingSwingWorker( 
            JEditorPane paneToPublish, List<String> pilots, List<String> urls, String password ) {
        this.paneToPublish = paneToPublish;
        this.pilots = pilots;
        this.urls = urls;
        this.password = password;
    }

    @Override
    protected String doInBackground() {

        try {
            Publisher publisher = new Publisher() {
                public void publish(String msg) {
                    ScoutAddingSwingWorker.this.publish(msg);
                }
            };
            ScoutAdder adder = new ScoutAdder(publisher, pilots, urls, password);
            adder.start();
        } catch (Exception ex) {
            publish(String.format("Received %s: %s.<br>Stopping.", ex.getClass().getName(), ex.getMessage()));
        }
        return "finished";
    }

    @Override
    protected void process( List<String> chunks ) {
        for ( String text : chunks ) {
            StringBuilder content = new StringBuilder(this.paneToPublish.getText());
            int insertPos = content.lastIndexOf("</body>");
            content.insert(insertPos, "<br />");
            content.insert(insertPos, text);
            this.paneToPublish.setText( content.toString() );
        }

    }
}
