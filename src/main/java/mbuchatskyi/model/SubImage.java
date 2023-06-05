package mbuchatskyi.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class SubImage {
	private List<Color> leftBorder = new ArrayList<>();
	private List<Color> rightBorder = new ArrayList<>();
	private List<Color> bottomBorder = new ArrayList<>();
	private List<Color> topBorder = new ArrayList<>();
	
	private File file;
	private BufferedImage image = new BufferedImage(320, 180, BufferedImage.TYPE_INT_ARGB);
	
	public SubImage(String file)  {
	    this.file = new File(file);
	    try {
			this.image = ImageIO.read(this.file);
			init();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	private void init() {
		calculateBottomBorderPixels();
		calculateTopBorderPixels();
		calculateLeftBorderPixels();
		calculateRightBorderPixels();
	}
	
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
	
	public List<Color> getLeftBorder() {
		return leftBorder;
	}

	public List<Color> getRightBorder() {
		return rightBorder;
	}

	public List<Color> getBottomBorder() {
		return bottomBorder;
	}

	public List<Color> getTopBorder() {
		return topBorder;
	}

	public File getFile() {
		return file;
	}

	public BufferedImage getImage() {
		return image;
	}	
}
