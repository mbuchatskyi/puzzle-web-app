package mbuchatskyi.splitter;

/**
 * Instances of this class only hold data about the position, width and height of the sub-image in the original image
 */

public class SubImagesInformation {
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
}
