package mbuchatskyi.splitter;

import java.util.ArrayList;
import java.util.List;

/**
 * A splitter that can split a given image into many (at least 16) sub-images.
 * 
 * It splits the image by fixed width and height. All sub-images that creates by
 * {@code Splitter} have the same size.
 * 
 */
public class Splitter {
	private int width;
	private int height;

	private int rows;
	private int columns;
	/**
	 * amount of wanted sub-images
	 */
	final private int AMOUNT = 16;

	/**
	 * Constructor which requirements array of image, width and height
	 * 
	 * @param image  array which holds information about image
	 * @param width  the width of a given image
	 * @param height the height of a given image
	 */
	public Splitter(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Splits the image by fixed width and height
	 * 
	 * @return the list of the sub-image bounds
	 **/
	public List<SubImagesInformation> split() {
		if (width < 0 || height < 0) {
			throw new IllegalStateException("Width or height of the given image is < 0. "
					+ "Please, call the setters of Splitter class and input correct data.");
		}

		// calculate how many parts a given image will be divided
		columns = (int) Math.sqrt(AMOUNT);
		rows = (int) Math.sqrt(AMOUNT);

		// create the list of sub-images
		ArrayList<SubImagesInformation> subimages = new ArrayList<>();

		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < columns; x++) {
				SubImagesInformation img = new SubImagesInformation(x, y, width/columns, height/columns);
				subimages.add(img);
			}
		}

		return subimages;
	}
	
	/**
	 * width of a given image
	 * 
	 * @param width must be greater than 0 to correct splitting
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * height of a given image
	 * 
	 * @param height must be greater than 0 to correct splitting
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
}
