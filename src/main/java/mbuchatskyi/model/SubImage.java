package mbuchatskyi.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * The instances of this class contains the pixels array of every border of the
 * sub-image and sub-image itself.
 */
public class SubImage {
	private List<Color> leftBorder = new ArrayList<>();
	private List<Color> rightBorder = new ArrayList<>();
	private List<Color> bottomBorder = new ArrayList<>();
	private List<Color> topBorder = new ArrayList<>();

	private File file;
	private BufferedImage image = new BufferedImage(320, 180, BufferedImage.TYPE_INT_ARGB);

	/**
	 * Constructor
	 * 
	 * @param file the file of the sub-image
	 */
	public SubImage(String file) {
		this.file = new File(file);
		try {
			this.image = ImageIO.read(this.file);
			init();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Calculates all pixels arrays
	 */
	private void init() {
		calculateBottomBorderPixels();
		calculateTopBorderPixels();
		calculateLeftBorderPixels();
		calculateRightBorderPixels();
	}

	/**
	 * Calculates pixel array of the left border
	 */
	private void calculateLeftBorderPixels() {
		int height = image.getHeight();

		int[] borderPixels = new int[height];

		for (int y = 0; y < height; y++) {
			int rgb = image.getRGB(0, y);
			borderPixels[y] = rgb;
		}

		for (int y = 0; y < height; y++) {
			Color color = new Color(borderPixels[y]);
			leftBorder.add(color);
		}
	}

	/**
	 * Calculates pixel array of the right border
	 */
	private void calculateRightBorderPixels() {
		int height = image.getHeight();
		int width = image.getWidth();

		int[] borderPixels = new int[height];

		for (int y = 0; y < height; y++) {
			int rgb = image.getRGB(width - 1, y);
			borderPixels[y] = rgb;
		}

		for (int y = 0; y < height; y++) {
			Color color = new Color(borderPixels[y]);
			rightBorder.add(color);
		}
	}

	/**
	 * Calculates pixel array of the bottom border
	 */
	private void calculateBottomBorderPixels() {
		int height = image.getHeight();
		int width = image.getWidth();

		int[] borderPixels = new int[width];

		for (int x = 0; x < height; x++) {
			borderPixels[x] = image.getRGB(x, height - 1);
		}

		for (int y = 0; y < height; y++) {
			Color color = new Color(borderPixels[y]);
			bottomBorder.add(color);
		}
	}

	/**
	 * Calculates pixel array of the top border
	 */
	private void calculateTopBorderPixels() {
		int height = image.getHeight();
		int width = image.getWidth();

		int[] borderPixels = new int[width];

		for (int x = 0; x < height; x++) {
			borderPixels[x] = image.getRGB(x, 0);
		}

		for (int y = 0; y < height; y++) {
			Color color = new Color(borderPixels[y]);
			topBorder.add(color);
		}
	}

	/**
	 * @return pixel array of the left border
	 */
	public List<Color> getLeftBorder() {
		return leftBorder;
	}

	/**
	 * @return pixel array of the right border
	 */
	public List<Color> getRightBorder() {
		return rightBorder;
	}

	/**
	 * @return pixel array of the bottom border
	 */
	public List<Color> getBottomBorder() {
		return bottomBorder;
	}

	/**
	 * @return pixel array of the top border
	 */
	public List<Color> getTopBorder() {
		return topBorder;
	}

	/**
	 * @return file of the sub-image
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @return sub-image itself
	 */
	public BufferedImage getImage() {
		return image;
	}
}
