package mbuchatskyi.repository;

import java.util.ArrayList;
import java.util.List;

import mbuchatskyi.model.ImageEntity;

/**
 * This class just contains the instances of {@code ImageEntity} class to
 * provide access to them across parts of the project.
 */
public class SubImageRepository {
	private static SubImageRepository instance;
	List<ImageEntity> images = new ArrayList<>();
	
	/**
	 * Default constructor
	 */
	public SubImageRepository() {
	}
	
	/**
	 * @return images the {@code ArrayList} of the sub-image entities 
	 */
	public List<ImageEntity> getImages() {
		return images;
	}
	
	/**
	 * method which ensures that only one object of its kind exists and provides a single point of access to it for any other code
	 * 
	 * @return instance of the SubImageRepository
	 */
	public static SubImageRepository getInstance() {
		if (instance == null) {
			instance = new SubImageRepository();
		}
		return instance;
	}
}
