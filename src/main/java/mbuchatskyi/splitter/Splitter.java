package mbuchatskyi.splitter;

/**
 * A splitter that can split a given image into many (at least 16) sub-images.
 * 
 * It splits the image by fixed width and height. All sub-images that creates by
 * {@code Splitter} have the same size.
 * 
 */
public class Splitter {
	private int[] image;
	private int width;
	private int height;

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
	public Splitter(int[] image, int width, int height) {
		this.image = image;
		this.width = width;
		this.height = height;
	}

	/**
	 * Splits the image by fixed width and height
	 * 
	 * @return the list of the sub-image bounds
	 **/
	public SubImagesRepository split() {
		if (width < 0 || height < 0) {
			throw new IllegalStateException("Width or height of the given image is < 0. "
					+ "Please, call the setters of Splitter class and input correct data.");
		}

		if (image == null) {
			throw new NullPointerException(
					"The array you given is empty! " + "Call the setImage(int[] array) and try to call split() again.");
		}

		// calculate how many parts a given image will be divided
		final int columns = (int) Math.sqrt(AMOUNT);
		final int rows = (int) Math.sqrt(AMOUNT);

		// create a new instance of the sub-image repository
		SubImagesRepository subimages = new SubImagesRepository();

		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < columns; x++) {
				SubImagesInformation img = new SubImagesInformation(x, y, width/columns, height/columns);
				subimages.add(img);
			}
		}

		return subimages;
	}

	/**
	 * image is the array to hold information about a given image, each value of
	 * this array is the color
	 * 
	 * @param image just must not be the null to correct splitting
	 */
	public void setImage(int[] image) {
		this.image = image;
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
}
