package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CanvasPrinter {

    public static final BufferedImage BACKGROUND = readfile("aurora.png");

    public static BufferedImage readfile(String filepath) {
	try {
	    BufferedImage start = ImageIO.read(new File(filepath));
	    return start;
	} catch (IOException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public static void print(Graphics2D g2d) {
	if (Main.my > 258) {
	    Main.frame.setTitle("Coordinates : " + (int) (Main.mx / 3.4) + "/"
		    + (int) ((Main.my - 258) / 3.2));
	} else
	    Main.frame.setTitle(Main.NAME);
	g2d.drawImage(BACKGROUND, 0, 0, null);
	for (int i = 0; i < 6; i++) {
	    g2d.setColor(Main.scans[i].color);
	    for (int j = 0; j < Main.scans[i].scans.size(); j++) {
		Scann current = Main.scans[i].scans.get(j);
		int centerx = (int) (current.x * 3.4);
		int centery = (int) (current.y * 3.2) + 258;
		int radius = (int) (current.dist / 38.5);
		// Hardcore trigonometry
		g2d.drawOval(centerx - radius, centery - radius, radius * 2,
			radius * 2);
	    }
	}
	g2d.drawImage(BACKGROUND.getSubimage(0, 0, 369, 258), 0, 0, null);
	for (int i = 0; i < 3; i++)
	    for (int j = 0; j < 2; j++) {
		int id = j + (i * 2);
		g2d.drawImage(Main.scans[id].getButton(), 10 + (j * 170),
			20 + (i * 65), null);
	    }
    }
}
