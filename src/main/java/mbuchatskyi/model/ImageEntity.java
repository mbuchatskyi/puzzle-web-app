package mbuchatskyi.model;

/**
 * Instances of this class hold data about true position and actual position of
 * the sub-image in the puzzle, not the sub-image itself.
 */
public class ImageEntity {
	private int truePosition;
	private int actualPosition;

	/**
	 * Constructor
	 * 
	 * @param actualPosition the actual position in the puzzle 
	 * @param truePosition   position in the base image but in the simple index, for
	 *                       example the sub-image in row 2, column 3 will have 
	 *                       truePosition which equals 7
	 */
	public ImageEntity(int truePosition, int actualPosition) {
		this.truePosition = truePosition;
		this.actualPosition = actualPosition;
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

	/**
	 * @param actualPosition actual position in the puzzle
	 */
	public void setActualPosition(int actualPosition) {
		this.actualPosition = actualPosition;
	}
	
	/**
	 * if two sub-images has the same truePosition than it's the same sub-image
	 */
	@Override
	public boolean equals(Object obj) {
		ImageEntity another = (ImageEntity) obj;
		return another.getTruePosition() == this.getTruePosition();
	}
}
