package mbuchatskyi.model;

/**
 * An {@code ImageEntity} is an object that represents the sub-image.
 * 
 * Instances of this class hold data about the id, position, width and height of
 * the sub-image in the original image, and also the sub-image itself.
 * 
 */
public class ImageEntity {
	private int truePosition;
	private int actualPosition;

	/**
	 * @param id           id of the sub-image
	 * @param image        sub-image itself
	 * @param truePosition position in the base image but in the simple index, for
	 *                     example the sub-image in row 2, column 3 will have 7
	 *                     truePosition
	 */
	public ImageEntity(int truePosition) {
		this.truePosition = truePosition;
	}

	/**
	 * if two sub-images has the same truePosition than it's the same sub-image
	 */
	@Override
	public boolean equals(Object obj) {
		ImageEntity another = (ImageEntity) obj;
		return another.getTruePosition() == this.getTruePosition();
	}

	/**
	 * @return truePosition position in the base image but in the simple index
	 */
	public int getTruePosition() {
		return truePosition;
	}

	/**
	 * @return actualPosition actual position in the puzzle
	 */
	public int getActualPosition() {
		return actualPosition;
	}
	
	public void setActualPosition(int actualPosition) {
		this.actualPosition = actualPosition;
	}
}
