package presentation;

import data.ProductException;
import domain.ProductMain;
import domain.SystemToolBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class ProductGUI extends JFrame {


    private static final Color ING_COLOR        =	new Color( 254, 122, 34 );
    private static final String FRAME_TITLE     =   "2nd GEP Coding Challenge: OOP";
    private static final int FRAME_WIDTH        =   800;
    private static final int FRAME_HEIGHT       =   220;
    private static final String ING_LOGO_PATH   =   System.getProperty( "user.dir" ) + "\\src\\data\\ING_LOGO.png";
    private static final String ING_LION_PATH   =   System.getProperty( "user.dir" ) + "\\src\\data\\lion.png";

    private ProductMain pm                      =   null;
    private File inFile                         =   null;
    private File outFile                        =   null;
    private JTextField inFileFld                =   null;
    private JTextField outFileFld               =   null;


    public ProductGUI() {
        super();
        init();
    }   //  End-of constructor


    /**
     * Method init
     * Used to initialize the GUI Frame, container for 3 panels.
     */
    private void init() {
        ImageIcon img                           =   new ImageIcon( ING_LION_PATH );
        this.setIconImage( img.getImage() );
        this.setTitle( this.FRAME_TITLE );
        this.setPreferredSize( new Dimension( this.FRAME_WIDTH, this.FRAME_HEIGHT ) );
        //  Center the frame on the screen
        this.setLocation( SystemToolBox.getFrameStartPoint( this.FRAME_WIDTH, this.FRAME_HEIGHT ) );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setVisible( true );
        Container frame                         =   this.getContentPane();
        frame.setLayout( new BorderLayout() );
        frame.add( northPanel(), BorderLayout.NORTH );
        frame.add( centerPanel(), BorderLayout.CENTER );
        frame.add( southPanel(), BorderLayout.SOUTH );
        this.pack();
    }   //  End-of method init


    /**
     * Method northPanel generates a JPanel object, to be added to the frame on the BorderLayout.NORTH position
     * @return The panel object created.
     */
    private JPanel northPanel() {
        JPanel panel                            =   new JPanel();
        panel.setLayout( new SpringLayout() );
        JLabel	lblImg					        =	new JLabel( new ImageIcon( ING_LOGO_PATH ) );
        lblImg.setHorizontalAlignment( JLabel.LEFT );
        panel.add( lblImg );
        JLabel lblTitle                         =   new JLabel( "Challenge #1" );
        lblTitle.setForeground( this.ING_COLOR );
        lblTitle.setFont( new Font(	lblTitle.getFont().getFontName(),
                lblTitle.getFont().getStyle(),
                lblTitle.getFont().getSize() + 12 ) );
        panel.add( lblTitle );
        SpringUtilities.makeCompactGrid( panel,
                1, 2, //rows, cols
                6, 6,        //initX, initY
                140, 6);       //xPad, yPad
        return panel;
    }   //  End-of method northPanel


    /**
     * Method soutthPanel generates a JPanel object, to be added to the frame on the BorderLayout.SOUTH position
     * @return The panel object created.
     */
    private JPanel southPanel() {
        JPanel panel                            =   new JPanel();
        panel.setLayout( new FlowLayout() );
        JButton submitButton                    =   new JButton( "Submit" );
        submitButton.addActionListener( new ButtonListener() );
        JButton clearButton                     =   new JButton( "Clear" );
        clearButton.addActionListener( new ButtonListener() );
        JButton exitButton                      =   new JButton( "Exit" );
        exitButton.addActionListener( new ButtonListener() );
        panel.add( submitButton );
        panel.add( clearButton );
        // panel.add( exitButton );
        return panel;
    }   //  End-of method southPanel


    /**
     * Method centerPanel generates a JPanel object, to be added to the frame on the BorderLayout.CENTER position
     * @return The panel object created.
     */
    private JPanel centerPanel() {
        JPanel panel                            =   new JPanel();
        panel.setLayout( new SpringLayout() );
        JLabel inFileLbl                        =   new JLabel( "Input file", JLabel.TRAILING );
        inFileFld                               =   new JTextField( 20 );
        JButton inFileBtn                       =   new JButton( "Pick infile");
        inFileBtn.addActionListener( new ButtonListener() );
        JLabel outFileLbl                       =   new JLabel( "Output file", JLabel.TRAILING );
        outFileFld                              =   new JTextField( 20 );
        JButton outFileBtn                      =   new JButton( "Pick outfile");
        outFileBtn.addActionListener( new ButtonListener() );
        panel.add( inFileLbl );
        panel.add( inFileFld );
        panel.add( inFileBtn );
        panel.add( outFileLbl );
        panel.add( outFileFld );
        panel.add( outFileBtn );
        //Lay out the panel.
        SpringUtilities.makeCompactGrid( panel,
                2, 3, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
        return panel;
    }   //  End-of method centerPanel


    /**
     * Method pickFile is used as file explorer to select a file.
     * @param isInFile When true the file selected is the input file or else the output file.
     */
    private void pickFile( boolean isInFile ) {
        JFileChooser chooser                    =   new JFileChooser();
        int chosen                              =   chooser.showDialog( this, "Open/Create" );
        if ( chosen == JFileChooser.APPROVE_OPTION ) {
            if ( isInFile ) {
                this.inFile                     =   chooser.getSelectedFile();
                this.inFileFld.setText( inFile.toString() );
            }   //  End-of if-branch
            else {
                this.outFile                    =   chooser.getSelectedFile();
                this.outFileFld.setText( outFile.toString() );
            }   //  End-of else-branch
        }   //  End-of if-branch
    }   //  End-of method pickFile


    /**
     * Method called by inner class ButtonListener
     * @param e ActionEvent object, provided by method actionPerformed from ButtonListener
     *          and used to determine the source button clicked
     */
    private void buttonListenerAction( ActionEvent e ) {
        String txt                              =   ( ( JButton) e.getSource() ).getText();
        switch( txt ) {
            case "Submit":
                System.out.println( "Submit pressed" );
                // Run ProductMain
                String inFile                   =   this.inFileFld.getText();
                String outFile                  =   this.outFileFld.getText();
                boolean isOK                    =   ( inFile != null) &&
                                                    ( !inFile.equals( "" ) ) &&
                                                    ( outFile != null) &&
                                                    ( !outFile.equals( "" ));
                if ( isOK ) {
                    pm                          =   new ProductMain( inFile );
                    try {
                        pm.readData();
                        pm.writeData( outFile );
                    }   //  End-of try-clause
                    catch( ProductException pe ) {
                        JOptionPane.showMessageDialog(null,
                                pe.getMessage(),
                                "ProductException",
                                JOptionPane.ERROR_MESSAGE);
                        // System.out.println( pe.getMessage() );
                    }   //  End-of catch-clause
                }
                break;
            case "Clear":
                this.inFile                     =   null;
                this.outFile                    =   null;
                this.inFileFld.setText( "" );
                this.outFileFld.setText( "" );
                break;
            case "Pick infile":
                System.out.println( "Infile clicked" );
                this.pickFile( true);
                break;
            case "Pick outfile":
                System.out.println( "Outfile clicked" );
                this.pickFile( false);
                break;
        }   //  End-of switch-block
    }   //  End-of method buttonListenerAction


    /**
     * Inner listener class for event handling button click events.
     */
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed( ActionEvent e ) {
            buttonListenerAction( e );
        }   //  End-of method actionPerformed
    }   //  End-of inner class ButtonListener


    /**
     * Main class, GUI starting point of application.
     * @param args Not used.
     */
    public static void main( String[] args ) {
        new ProductGUI();
    }   //  End-of method main


}   //  End-of class ProductGUI
