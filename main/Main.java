package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

    public final static String NAME = "Pirate Galaxy Stash Finder";
    protected static JFrame frame = new JFrame(NAME);
    protected static Canvas canvas = new Canvas();

    public static Drone[] scans = new Drone[] { new Drone(Color.BLUE, 1),
	    new Drone(Color.RED, 2), new Drone(Color.GREEN, 3),
	    new Drone(Color.WHITE, 4), new Drone(Color.ORANGE, 5),
	    new Drone(Color.MAGENTA, 6) };

    public static int mx;
    public static int my;

    public static int placer;

    public static void main(String[] args) {
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(369, 624);
	canvas.setSize(369, 624);
	canvas.addMouseListener(new MouseListener() {

	    @Override
	    public void mouseReleased(MouseEvent e) {
		if (e.getY() > 258) {
		    int mapx = ((int) (Main.mx / 3.4)), mapy = ((int) ((Main.my - 258) / 3.2));
		    String dist = JOptionPane
			    .showInputDialog("Enter the distance.");
		    int d = 0;
		    try {
			d = Integer.parseInt(dist);
		    } catch (Exception e2) {
		    }
		    scans[placer].scans.add(new Scann(mapx, mapy, d));// FIXME
		} else
		    for (int i = 0; i < 3; i++)
			for (int j = 0; j < 2; j++) {
			    int dx = 10 + (j * 170), dy = 20 + (i * 65);
			    if (e.getX() > dx && e.getX() < dx + 158
				    && e.getY() > dy && e.getY() < dy + 57) {
				int id = j + (i * 2);
				scans[id].manageClick(mx - dx, my - dy);
			    }
			}
	    }

	    @Override
	    public void mousePressed(MouseEvent e) {

	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
		mx = 0;
		my = 0;
	    }

	    @Override
	    public void mouseEntered(MouseEvent e) {
	    }

	    @Override
	    public void mouseClicked(MouseEvent e) {
	    }
	});
	canvas.addMouseMotionListener(new MouseMotionListener() {

	    @Override
	    public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	    }

	    @Override
	    public void mouseDragged(MouseEvent e) {
	    }
	});
	frame.setResizable(false);
	frame.add(canvas);
	frame.setVisible(true);
	Thread updater = new Thread(new Runnable() {
	    @Override
	    public void run() {
		for (;;) {
		    BufferedImage buffer = new BufferedImage(canvas.getWidth(),
			    canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
		    CanvasPrinter.print((Graphics2D) buffer.getGraphics());
		    canvas.getGraphics().drawImage(buffer, 0, 0, null);
		    try {
			Thread.sleep(20);
		    } catch (Exception e) {
		    }
		}
	    }
	});
	updater.start();
    }
}
