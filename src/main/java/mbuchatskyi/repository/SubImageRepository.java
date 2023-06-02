package mbuchatskyi.repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import mbuchatskyi.model.ImageEntity;

/**
 * This class just contains the instances of {@code ImageEntity} class to
 * provide access to them across parts of the project.
 */
public class SubImageRepository {
	private static SubImageRepository instance;
	private int flag;
	
	private List<ImageEntity> images = new ArrayList<>();

	/**
	 * Default constructor
	 */
	private SubImageRepository() {
	}

	/**
	 * @return images the {@code ArrayList} of the sub-image entities
	 */
	public List<ImageEntity> getImages() {
		return images;
	}

	/**
	 * method which ensures that only one object of its kind exists and provides a
	 * single point of access to it for any other code
	 * 
	 * @return instance of the SubImageRepository
	 */
	public static SubImageRepository getInstance() {
		if (instance == null) {
			instance = new SubImageRepository();
		}
		return instance;
	}

	public void swap(int firstposition, int secondposition) {
		flag = 0;
		// sets the actual positions of the image
		setPositions(firstposition, secondposition);
		// File with old name
		String firstOldName = ABSOLUTE_PATH + "/subimage_" + firstposition + ".jpg";
		String secondOldName = ABSOLUTE_PATH + "/subimage_" + secondposition + ".jpg";
		
		File file = new File(firstOldName);

		// File (or directory) with new name
		File file2 = new File(ABSOLUTE_PATH + "/0.jpg");

		// Rename file
		
		// first image --> 0.jpg
		// second image --> secondimage.jpg
		boolean success = file.renameTo(file2);
		System.out.println(success);

		File file3 = new File(secondOldName);
		File file4 = new File(firstOldName);

		// first image --> 0.jpg
		// second image --> firstimage.jpg
		success = file3.renameTo(file4);
		System.out.println(success);
		
		File file5 = new File(ABSOLUTE_PATH + "/0.jpg");
		File file6 = new File(secondOldName);

		// first image --> secondimage.jpg
		// second image --> firstimage.jpg
		success = file5.renameTo(file6);
		System.out.println(success);
		
		getImages().stream().forEach(e -> System.out.println(e.getTruePosition()));
	}

	private void setPositions(int firstposition, int secondposition) {
		flag++;
		// check if exists image with truePosition which equals first position
		 long i = getImages()
		           .stream()
		           .filter(e -> e.getTruePosition() == firstposition)
		           .count();
			
		// if don't exist - create it 
		if (i == 0L) {
			images.add(new ImageEntity(firstposition));
		}
		
		// find this image 
		ImageEntity img = getImages()
				.stream()
				.filter(e -> e.getTruePosition() == firstposition)
				.collect(Collectors.toList()).get(0);
		
		// set its actual position
		img.setActualPosition(secondposition);
		
		if (flag < 2)
		setPositions(secondposition, firstposition);
	}
	
	public boolean isPuzzleSolved() {
		if (getImages().stream().allMatch(e -> e.getActualPosition() == e.getTruePosition()) || images.isEmpty()) {
			return true;
		}
		System.out.println("AA");
		System.out.println(getImages().get(0).getActualPosition());
		System.out.println(getImages().get(0).getTruePosition());
		
		System.out.println(getImages().get(1).getActualPosition());
		System.out.println(getImages().get(1).getTruePosition());
		
		return false;
	}
	
	private final String ABSOLUTE_PATH = "D:/images";
	
	public String getABSOLUTE_PATH() {
		return ABSOLUTE_PATH;
	}
}
