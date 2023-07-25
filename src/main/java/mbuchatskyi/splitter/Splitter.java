package mbuchatskyi.splitter;

import java.util.ArrayList;
import java.util.List;

/**
 * A splitter that generate information about the future sub-images. It splits the
 * image by fixed width and height.
 * 
 * All sub-images that creates by {@code Splitter} have the same size.
 */
public class Splitter {
	private int width;
	private int height;

	private int rows;
	private int columns;

	/**
	 * Amount of wanted sub-images
	 */
	final private int AMOUNT = 16;

	/**
	 * Constructor which requirements the width and height of the base image in
	 * order to calculate the optimal grid.
	 *
	 * @param width  the width of a given image
	 * @param height the height of a given image
	 */
	public Splitter(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Splits the image by given width and height
	 * 
	 * @return the list of the {@code SubImageInformation} class. These instances
	 *         hold the information about the width and height of the sub-images
	 *
	 * @see SubImageInformation
	 **/
	public List<SubImagesInformation> split() {
		if (width < 0 || height < 0) {
			throw new IllegalStateException("Width or height of the given image is < 0. "
					+ "Please, call the setters of Splitter class and input correct data.");
		}

		// calculate how many parts a given image will be divided
		columns = (int) Math.sqrt(AMOUNT);
		rows = (int) Math.sqrt(AMOUNT);

		// create the list of the instances of SubImageInformation class
		ArrayList<SubImagesInformation> subimages = new ArrayList<>();

		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < columns; x++) {
				SubImagesInformation img = new SubImagesInformation(width / columns, height / columns);
				subimages.add(img);
			}
		}

		return subimages;
	}

	/**
	 * Width of a given image
	 * 
	 * @param width must be greater than 0 to correct splitting
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Height of a given image
	 * 
	 * @param height must be greater than 0 to correct splitting
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return amount of the sub-images
	 */
	public int getAmount() {
		return AMOUNT;
	}

	/**
	 * @return the rows in the grid
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @return the columns in the grid
	 */
	public int getColumns() {
		return columns;
	}
}
