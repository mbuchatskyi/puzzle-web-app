package mbuchatskyi.model;

import java.awt.image.BufferedImage;

/**
 * An {@code ImageEntity} is an object that represents the sub-image.
 *  
 * Instances of this class hold data about the id, position, width and height of the sub-image in the original image,
 * and also the sub-image itself.
 * 
 */
public class ImageEntity {
	private final int id;
	private BufferedImage image;
	
	private int truePosition;
	private int turned;
	
	/**
	 * @param id             id of the sub-image
	 * @param image          sub-image itself
	 * @param truePosition   position in the base image but in the simple index, 
	 * for example the sub-image in row 2, column 3 will have 7 truePosition
	 */
	public ImageEntity(int id, BufferedImage image, int truePosition) {
		this.id = id;
		this.image = image;
		this.truePosition = truePosition;
	}
	
	/**
	 * if two sub-images has the same id than it's the same sub-image
	 */
	@Override
	public boolean equals(Object obj) {
			ImageEntity another = (ImageEntity) obj;
			return another.getId() == this.getId();
	}

	/**
	 * @return id the unique identifier 
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return image the sub-image itself
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @return truePosition position in the base image but in the simple index
	 */
	public int getTruePosition() {
		return truePosition;
	}

	/**
	 * @return turned coefficient that shows in which way the sub-image is turned
	 * turned is 0 or x which satisfies the next condition: x % 4 = 0 
	 * positive {@code int} if it's turned to the right
	 * negative {@code int} if it's turned to the left 
	 */
	public int getTurned() {
		return turned;
	}
}
