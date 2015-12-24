package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Drone {

    public static final BufferedImage FULL = CanvasPrinter
	    .readfile("drone.png");
    public Color color;
    public int id;
    public ArrayList<Scann> scans = new ArrayList<Scann>();

    public Drone(Color color, int i) {
	id = i;
	this.color = color;
    }

    public BufferedImage getButton() {
	BufferedImage temp = new BufferedImage(FULL.getWidth(),
		FULL.getHeight(), BufferedImage.TYPE_INT_ARGB);
	Graphics2D g2d = (Graphics2D) temp.getGraphics();
	g2d.drawImage(FULL, 0, 0, null);
	g2d.setColor(color);
	g2d.drawRect(107, 33, 41, 13);
	if (Main.placer == id - 1)
	    g2d.fillRect(109, 35, 38, 10);
	g2d.setColor(Color.BLACK);
	g2d.drawString("" + id, 68, 22);
	g2d.drawString("" + scans.size(), 68, 42);
	return temp;
    }

    public void manageClick(int x, int y) {
	if (x > 100)
	    scans = new ArrayList<Scann>();
	else
	    Main.placer = id - 1;
    }

    public void reset() {

    }
}
