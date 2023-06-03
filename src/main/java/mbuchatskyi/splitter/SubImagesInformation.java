package mbuchatskyi.splitter;

/**
 * Instances of this class only hold the data about the width and height of the
 * sub-image, not the sub-images themselves.
 */
public class SubImagesInformation {
	private double width;
	private double height;

	/**
	 * Constructor
	 */
	public SubImagesInformation(int row, int column, double width, double height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * @return width of the sub-image
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @return height of the sub-image
	 */
	public double getHeight() {
		return height;
	}
}
