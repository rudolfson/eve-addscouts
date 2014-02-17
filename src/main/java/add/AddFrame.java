package add;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkEvent.EventType;
import javax.swing.event.HyperlinkListener;

public class AddFrame {

    private Executor executor = Executors.newSingleThreadExecutor();

    private JFrame frmEuniScoutadder;

    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main( String[] args ) {
        EventQueue.invokeLater( new Runnable() {
            @Override
            public void run() {
                try {
                    AddFrame window = new AddFrame();
                    window.frmEuniScoutadder.setVisible( true );
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        } );
    }

    /**
     * Create the application.
     */
    public AddFrame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        this.frmEuniScoutadder = new JFrame();
        this.frmEuniScoutadder.setTitle( "E-Uni ScoutAdder by Leonty Alkaev" );
        this.frmEuniScoutadder.setBounds( 100, 100, 746, 838 );
        this.frmEuniScoutadder.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        SpringLayout springLayout = new SpringLayout();
        this.frmEuniScoutadder.getContentPane().setLayout( springLayout );

        JLabel lblLogibros = new JLabel( "Logibros and Scouts" );
        lblLogibros.setFont( new Font( "Tahoma", Font.BOLD, 11 ) );
        springLayout.putConstraint( SpringLayout.NORTH, lblLogibros, 10, SpringLayout.NORTH,
            this.frmEuniScoutadder.getContentPane() );
        springLayout.putConstraint( SpringLayout.WEST, lblLogibros, 10, SpringLayout.WEST,
            this.frmEuniScoutadder.getContentPane() );
        this.frmEuniScoutadder.getContentPane().add( lblLogibros );

        JLabel lblEnterOneName = new JLabel( "<html><i>Enter one name on each line</i></html>" );
        springLayout.putConstraint( SpringLayout.NORTH, lblEnterOneName, 5, SpringLayout.SOUTH, lblLogibros );
        springLayout.putConstraint( SpringLayout.WEST, lblEnterOneName, 0, SpringLayout.WEST, lblLogibros );
        lblEnterOneName.setVerticalAlignment( SwingConstants.TOP );
        this.frmEuniScoutadder.getContentPane().add( lblEnterOneName );

        JScrollPane scrollLogibros = new JScrollPane();
        springLayout.putConstraint( SpringLayout.SOUTH, lblEnterOneName, 0, SpringLayout.SOUTH, scrollLogibros );
        springLayout.putConstraint( SpringLayout.EAST, lblEnterOneName, -6, SpringLayout.WEST, scrollLogibros );
        springLayout.putConstraint( SpringLayout.WEST, scrollLogibros, 150, SpringLayout.WEST,
            this.frmEuniScoutadder.getContentPane() );
        springLayout.putConstraint( SpringLayout.SOUTH, scrollLogibros, 200, SpringLayout.NORTH,
            this.frmEuniScoutadder.getContentPane() );
        springLayout.putConstraint( SpringLayout.NORTH, scrollLogibros, 10, SpringLayout.NORTH,
            this.frmEuniScoutadder.getContentPane() );
        springLayout.putConstraint( SpringLayout.EAST, scrollLogibros, -10, SpringLayout.EAST,
            this.frmEuniScoutadder.getContentPane() );
        this.frmEuniScoutadder.getContentPane().add( scrollLogibros );

        JLabel lblBattleReportsAnd = new JLabel( "Battle reports and Kills" );
        lblBattleReportsAnd.setFont( new Font( "Tahoma", Font.BOLD, 11 ) );
        springLayout.putConstraint( SpringLayout.NORTH, lblBattleReportsAnd, 10, SpringLayout.SOUTH, scrollLogibros );
        springLayout.putConstraint( SpringLayout.WEST, lblBattleReportsAnd, 10, SpringLayout.WEST,
            this.frmEuniScoutadder.getContentPane() );
        this.frmEuniScoutadder.getContentPane().add( lblBattleReportsAnd );

        JScrollPane scrollReports = new JScrollPane();
        springLayout.putConstraint( SpringLayout.NORTH, scrollReports, 10, SpringLayout.SOUTH, scrollLogibros );
        springLayout.putConstraint( SpringLayout.SOUTH, scrollReports, 200, SpringLayout.SOUTH, scrollLogibros );
        springLayout.putConstraint( SpringLayout.EAST, lblBattleReportsAnd, -6, SpringLayout.WEST, scrollReports );
        springLayout.putConstraint( SpringLayout.WEST, scrollReports, 0, SpringLayout.WEST, scrollLogibros );

        JTextArea txtrLogibros = new JTextArea();
        scrollLogibros.setViewportView( txtrLogibros );

        JLabel lblenterLinksTo =
            new JLabel( "<html><i>Enter links to individual kills or complete battle reports, one per line</i></html>" );
        springLayout.putConstraint( SpringLayout.NORTH, lblenterLinksTo, 5, SpringLayout.SOUTH, lblBattleReportsAnd );
        springLayout.putConstraint( SpringLayout.WEST, lblenterLinksTo, 0, SpringLayout.WEST, lblBattleReportsAnd );
        springLayout.putConstraint( SpringLayout.SOUTH, lblenterLinksTo, 0, SpringLayout.SOUTH, scrollReports );
        springLayout.putConstraint( SpringLayout.EAST, lblenterLinksTo, -10, SpringLayout.WEST, scrollReports );
        lblenterLinksTo.setVerticalAlignment( SwingConstants.TOP );
        this.frmEuniScoutadder.getContentPane().add( lblenterLinksTo );
        springLayout.putConstraint( SpringLayout.EAST, scrollReports, -10, SpringLayout.EAST,
            this.frmEuniScoutadder.getContentPane() );
        this.frmEuniScoutadder.getContentPane().add( scrollReports );

        JTextArea txtrReports = new JTextArea();
        scrollReports.setViewportView( txtrReports );

        JLabel lblNewLabel = new JLabel( "Password" );
        lblNewLabel.setFont( new Font( "Tahoma", Font.BOLD, 11 ) );
        springLayout.putConstraint( SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, lblLogibros );
        this.frmEuniScoutadder.getContentPane().add( lblNewLabel );

        this.passwordField = new JPasswordField();
        springLayout.putConstraint( SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, this.passwordField );
        springLayout.putConstraint( SpringLayout.NORTH, this.passwordField, 10, SpringLayout.SOUTH, scrollReports );
        springLayout.putConstraint( SpringLayout.WEST, this.passwordField, 0, SpringLayout.WEST, scrollReports );
        springLayout.putConstraint( SpringLayout.EAST, this.passwordField, 150, SpringLayout.WEST, scrollReports );
        this.frmEuniScoutadder.getContentPane().add( this.passwordField );

        JButton btnAddThem = new JButton( "Add them" );
        springLayout.putConstraint( SpringLayout.NORTH, btnAddThem, 10, SpringLayout.SOUTH, this.passwordField );
        springLayout.putConstraint( SpringLayout.WEST, btnAddThem, 0, SpringLayout.WEST, scrollReports );
        this.frmEuniScoutadder.getContentPane().add( btnAddThem );

        JLabel lblOutput = new JLabel( "Output" );
        springLayout.putConstraint( SpringLayout.NORTH, lblOutput, 20, SpringLayout.SOUTH, btnAddThem );
        springLayout.putConstraint( SpringLayout.WEST, lblOutput, 10, SpringLayout.WEST,
            this.frmEuniScoutadder.getContentPane() );
        springLayout.putConstraint( SpringLayout.EAST, lblOutput, 74, SpringLayout.WEST,
            this.frmEuniScoutadder.getContentPane() );
        this.frmEuniScoutadder.getContentPane().add( lblOutput );

        JScrollPane panel = new JScrollPane();
        springLayout.putConstraint( SpringLayout.NORTH, panel, 10, SpringLayout.SOUTH, lblOutput );
        springLayout.putConstraint( SpringLayout.WEST, panel, 10, SpringLayout.WEST,
            this.frmEuniScoutadder.getContentPane() );
        springLayout.putConstraint( SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH,
            this.frmEuniScoutadder.getContentPane() );
        springLayout.putConstraint( SpringLayout.EAST, panel, -10, SpringLayout.EAST,
            this.frmEuniScoutadder.getContentPane() );
        this.frmEuniScoutadder.getContentPane().add( panel );

        final JEditorPane dtrpnOutput = new JEditorPane();
        dtrpnOutput.addHyperlinkListener( new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate( HyperlinkEvent e ) {
                if ( e.getEventType() == EventType.ACTIVATED ) {
                    try {
                        Desktop.getDesktop().browse( e.getURL().toURI() );
                    } catch ( IOException e1 ) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch ( URISyntaxException e1 ) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        } );
        dtrpnOutput.setEditable( false );
        dtrpnOutput.setContentType( "text/html" );
        panel.setViewportView( dtrpnOutput );

        btnAddThem.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                ScoutAddingSwingWorker worker = new ScoutAddingSwingWorker( dtrpnOutput );
                AddFrame.this.executor.execute( worker );
            }
        } );

    }
}
