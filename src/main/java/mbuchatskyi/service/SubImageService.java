package mbuchatskyi.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import mbuchatskyi.model.ImageEntity;

/**
 * This class contains the methods which provide to user opportunities to swap
 * puzzles and check if they make up the base image.
 */
public class SubImageService {
	private static SubImageService instance;
	private int amount;

	private List<ImageEntity> images = new ArrayList<>();

	/**
	 * Ensures that only one object of {@code SubImageService} exists
	 * and provides a single point of access to it for any other code.
	 * 
	 * @return instance of the SubImageService
	 */
	public static SubImageService getInstance() {
		if (instance == null) {
			instance = new SubImageService();
		}
		return instance;
	}

	/**
	 * The initialization of the {@code ArrayList} which contains the instances of
	 * the {@code ImageEntity}
	 * 
	 * @see ImageEntity
	 */
	private void init() {
		for (int i = 0; i < amount; i++) {
			images.add(new ImageEntity(i, i));
		}
	}

	/**
	 * Swaps the two sub-images
	 * 
	 * @param firstposition  the actual position of the first sub-image which should be swapped
	 * @param secondposition the actual position of the second sub-image
	 */
	public void swap(int firstposition, int secondposition) {
		// sets the actual positions
		setActualPositions(firstposition, secondposition);
		
		// Files name before swapping
		String firstOldName = ABSOLUTE_PATH + "/subimage_" + firstposition + ".jpg";
		String secondOldName = ABSOLUTE_PATH + "/subimage_" + secondposition + ".jpg";

		// File with old name
		File file = new File(firstOldName);

		// File (or directory) with new name
		File file2 = new File(ABSOLUTE_PATH + "/0.jpg");

		// first image --> 0.jpg
		// second image --> secondimage.jpg
		file.renameTo(file2);

		File file3 = new File(secondOldName);
		File file4 = new File(firstOldName);

		// first image --> 0.jpg
		// second image --> firstimage.jpg
		file3.renameTo(file4);

		File file5 = new File(ABSOLUTE_PATH + "/0.jpg");
		File file6 = new File(secondOldName);

		// first image --> secondimage.jpg
		// second image --> firstimage.jpg
		file5.renameTo(file6);
	}

	/**
	 * sets actual positions of the two {@code ImageEnity} instances which should be swapped
	 * 
	 * @param firstposition  the actual position of the first sub-image
	 * @param secondposition the actual position of the second sub-image
	 */
	private void setActualPositions(int firstposition, int secondposition) {
		// gets the ImageEntity with actual position which equals firstposition param
		ImageEntity firstImg = getImages().stream().filter(e -> e.getActualPosition() == firstposition)
				.collect(Collectors.toList()).get(0);
				
		// gets the ImageEntity with actual position which equals firstposition param
		ImageEntity secondImg = getImages().stream().filter(e -> e.getActualPosition() == secondposition)
				.collect(Collectors.toList()).get(0);

		// changing their actual positions
		firstImg.setActualPosition(secondposition);
		secondImg.setActualPosition(firstposition);
	}

	/**
	 * @return {@code true} if puzzle is solved, {@code false} otherwise
	 */
	public boolean isPuzzleSolved() {
		if (getImages().stream().allMatch(e -> e.getActualPosition() == e.getTruePosition())) {
			return true;
		}
		return false;
	}

	/**
	 * @param amount the amount of the sub-images in the current {@code Splitter}
	 */
	public void setAmount(int amount) {
		images.clear();
		this.amount = amount;
		init();
	}

	/**
	 * @return images the {@code ArrayList} of the sub-image entities
	 */
	public List<ImageEntity> getImages() {
		return images;
	}

	private final String ABSOLUTE_PATH = "D:/images";

	/**
	 * @return the location of the sub-images files
	 */
	public String getABSOLUTE_PATH() {
		return ABSOLUTE_PATH;
	}
}
