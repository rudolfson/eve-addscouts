package add;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class AddFrame {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main( String[] args ) {
        EventQueue.invokeLater( new Runnable() {
            @Override
            public void run() {
                try {
                    AddFrame window = new AddFrame();
                    window.frame.setVisible( true );
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
        this.frame = new JFrame();
        this.frame.setBounds( 100, 100, 746, 838 );
        this.frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        SpringLayout springLayout = new SpringLayout();
        this.frame.getContentPane().setLayout( springLayout );

        JLabel lblLogibros = new JLabel( "Logibros" );
        springLayout.putConstraint( SpringLayout.NORTH, lblLogibros, 10, SpringLayout.NORTH,
            this.frame.getContentPane() );
        springLayout.putConstraint( SpringLayout.WEST, lblLogibros, 10, SpringLayout.WEST, this.frame.getContentPane() );
        this.frame.getContentPane().add( lblLogibros );

        JScrollPane scrollLogibros = new JScrollPane();
        springLayout.putConstraint( SpringLayout.WEST, scrollLogibros, 130, SpringLayout.WEST,
            this.frame.getContentPane() );
        springLayout.putConstraint( SpringLayout.SOUTH, scrollLogibros, 200, SpringLayout.NORTH,
            this.frame.getContentPane() );
        springLayout.putConstraint( SpringLayout.NORTH, scrollLogibros, 10, SpringLayout.NORTH,
            this.frame.getContentPane() );
        springLayout.putConstraint( SpringLayout.EAST, scrollLogibros, -10, SpringLayout.EAST,
            this.frame.getContentPane() );
        this.frame.getContentPane().add( scrollLogibros );

        JLabel lblBattleReportsAnd = new JLabel( "Battle reports and Kills" );
        springLayout.putConstraint( SpringLayout.NORTH, lblBattleReportsAnd, 10, SpringLayout.SOUTH, scrollLogibros );
        springLayout.putConstraint( SpringLayout.WEST, lblBattleReportsAnd, 10, SpringLayout.WEST,
            this.frame.getContentPane() );
        this.frame.getContentPane().add( lblBattleReportsAnd );

        JScrollPane txtrReports = new JScrollPane();
        springLayout.putConstraint( SpringLayout.EAST, lblBattleReportsAnd, -6, SpringLayout.WEST, txtrReports );
        springLayout.putConstraint( SpringLayout.NORTH, txtrReports, 6, SpringLayout.SOUTH, scrollLogibros );
        springLayout.putConstraint( SpringLayout.WEST, txtrReports, 0, SpringLayout.WEST, scrollLogibros );
        springLayout.putConstraint( SpringLayout.SOUTH, txtrReports, 306, SpringLayout.SOUTH, scrollLogibros );

        JTextArea textArea = new JTextArea();
        scrollLogibros.setViewportView( textArea );
        springLayout
            .putConstraint( SpringLayout.EAST, txtrReports, -10, SpringLayout.EAST, this.frame.getContentPane() );
        this.frame.getContentPane().add( txtrReports );

        JButton btnAddThem = new JButton( "Add them" );
        springLayout.putConstraint( SpringLayout.NORTH, btnAddThem, 10, SpringLayout.SOUTH, txtrReports );
        springLayout.putConstraint( SpringLayout.WEST, btnAddThem, 0, SpringLayout.WEST, txtrReports );

        JTextArea textArea_1 = new JTextArea();
        txtrReports.setViewportView( textArea_1 );
        this.frame.getContentPane().add( btnAddThem );

        JLabel lblOutput = new JLabel( "Output" );
        springLayout.putConstraint( SpringLayout.NORTH, lblOutput, 20, SpringLayout.SOUTH, btnAddThem );
        springLayout.putConstraint( SpringLayout.WEST, lblOutput, 10, SpringLayout.WEST, this.frame.getContentPane() );
        springLayout.putConstraint( SpringLayout.EAST, lblOutput, 74, SpringLayout.WEST, this.frame.getContentPane() );
        this.frame.getContentPane().add( lblOutput );

        JScrollPane panel = new JScrollPane();
        springLayout.putConstraint( SpringLayout.NORTH, panel, 10, SpringLayout.SOUTH, lblOutput );
        springLayout.putConstraint( SpringLayout.WEST, panel, 10, SpringLayout.WEST, this.frame.getContentPane() );
        springLayout.putConstraint( SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, this.frame.getContentPane() );
        springLayout.putConstraint( SpringLayout.EAST, panel, -10, SpringLayout.EAST, this.frame.getContentPane() );
        this.frame.getContentPane().add( panel );
    }
}
