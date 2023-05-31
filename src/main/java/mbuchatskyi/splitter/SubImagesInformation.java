package mbuchatskyi.splitter;

/**
 * Instances of this class only hold data about the position, width and height of the sub-image in the original image
 */

public class SubImagesInformation {
	/**
	 *  the position of the sub-image in the base image
	 */
	private int row;
	private int column;
	
	private double width;
	private double height;
	
	/**
	 * Constructor
	 */
	public SubImagesInformation(int row, int column, double width, double height) {
		this.row = row;
		this.column = column;
		this.width = width;
		this.height = height;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
}
