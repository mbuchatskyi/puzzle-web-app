package mbuchatskyi.model;

import java.awt.image.BufferedImage;

/**
 * An {@code ImageEntity} is an object that represents the sub-image.
 *  
 * Instances of this class hold data about the id, position, width and height of the subimage in the original image,
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
}
