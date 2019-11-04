package resources;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;

import model.Board;

/**
 * This class provides a simple way to access graphical resources used by the
 * game.
 * 
 * @author Samuel Gamelin
 * @version 2.0
 */
public final class Resources {
	/**
	 * Making the constructor private, preventing any instantiation of this class.
	 */
	private Resources() {
	}

	/**
	 * A percentage (75%) of the current display's height, which will be used in
	 * calculations to determine appropriate scaling of icons.
	 */
	public static final double SIDE_LENGTH = 0.75 * Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	// Incorrect move sound
	public static final File INVALID_MOVE = loadFile("wrong.wav");

	// JFrame icon
	public static final ImageIcon WINDOW_ICON = loadIcon("window-icon.png", 4, 5);

	// Rabbit icons
	public static final ImageIcon RABBIT1 = loadIcon("rabbit1.png", 0.6, 0.75);
	public static final ImageIcon RABBIT2 = loadIcon("rabbit2.png", 0.6, 0.75);

	// Fox head icons
	public static final ImageIcon FOX_HEAD_UP = loadIcon("fox-head-up.png", 0.75, 1);
	public static final ImageIcon FOX_HEAD_DOWN = loadIcon("fox-head-down.png", 0.75, 1);
	public static final ImageIcon FOX_HEAD_LEFT = loadIcon("fox-head-left.png", 1, 0.75);
	public static final ImageIcon FOX_HEAD_RIGHT = loadIcon("fox-head-right.png", 1, 0.75);

	// Fox tail icons
	public static final ImageIcon FOX_TAIL_UP = loadIcon("fox-tail-up.png", 0.7, 1);
	public static final ImageIcon FOX_TAIL_DOWN = loadIcon("fox-tail-down.png", 0.75, 1);
	public static final ImageIcon FOX_TAIL_LEFT = loadIcon("fox-tail-left.png", 1, 0.7);
	public static final ImageIcon FOX_TAIL_RIGHT = loadIcon("fox-tail-right.png", 1, 0.7);

	// Mushroom icon
	public static final ImageIcon MUSHROOM = loadIcon("mushroom.png", 0.75, 0.75);

	// Main menu icon
	public static final ImageIcon MAIN_MENU_BACKGROUND = loadIcon("mainmenu.png", 5, 5);

	// Board icon
	public static final ImageIcon BOARD = loadIcon("board.png", 5, 5);

	/**
	 * Returns a scaled version of the icon based on the primary display's size. A
	 * scale value of 1 represents 1/5 of the width/height of the viewing area.
	 * 
	 * @param icon   The icon to scale
	 * @param xScale The percentage to scale the icon in the x direction
	 * @param yScale The percentage to scale the icon in the y direction
	 * @return A scaled version of the icon
	 */
	private static ImageIcon loadIcon(String path, double xScale, double yScale) {
		return new ImageIcon(new ImageIcon(Resources.class.getClassLoader().getResource(path)).getImage()
				.getScaledInstance((int) (xScale * SIDE_LENGTH / Board.SIZE), (int) (yScale * SIDE_LENGTH / Board.SIZE),
						Image.SCALE_SMOOTH));
	}

	/**
	 * Load and return the requested file. Typically used to load in music and sound
	 * files.
	 * 
	 * @param path The path at which the resource is located
	 * @return The file at the specified location
	 */
	private static File loadFile(String path) {
		try {
			return new File(Resources.class.getClassLoader().getResource(path).toURI());
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return null;
	}
}
