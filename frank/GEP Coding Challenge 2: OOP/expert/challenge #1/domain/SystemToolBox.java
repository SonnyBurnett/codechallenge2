package domain;

import javax.swing.*;
import java.awt.*;



public class SystemToolBox {


    public static Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }


    public static double getScreenHeight() {
        return getScreenSize().height;
    }


    public static double getScreenWidth() {
        return getScreenSize().width;
    }


    public static Point getScreenCenter() {
        double height           =   getScreenHeight();
        double width            =   getScreenWidth();
        return new Point( ( int) ( height / 2 ), ( int) ( width / 2 ) );
    }


    public static Point getFrameStartPoint( Dimension frameDimension ) {
        int y                   =   ( ( int ) getScreenHeight() - frameDimension.height ) /2 ;
        int x                   =   ( ( int ) getScreenWidth() - frameDimension.width ) / 2 ;
        return new Point( x, y );
    }


    public static Point getFrameStartPoint( int width, int height ) {
        int x                   =   ( ( int ) getScreenWidth() - width ) / 2  ;
        int y                   =   ( ( int ) getScreenHeight() - height ) / 2 ;
        return new Point( x, y );
    }


    public static void main( String[] args ) {
        int width               =   800;
        int height              =   600;
        JFrame frame            =   new JFrame();
        frame.setTitle( "Test of centering frame" );
        frame.setSize( width, height );
        System.out.println( SystemToolBox.getScreenCenter().x + "," + SystemToolBox.getScreenCenter().y  );
        frame.setLocation( new Point( SystemToolBox.getFrameStartPoint( width, height ) ) );
        frame.setVisible( true );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

}
