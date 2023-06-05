package mbuchatskyi.solver;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import mbuchatskyi.model.SubImage;
import mbuchatskyi.service.SubImageService;

/**
 * The puzzle solver that can solve the puzzle if it knows the first sub-image.
 */
public class PuzzleSolver {
	private static List<SubImage> subimages = new ArrayList<>();
	private List<SubImage> solvedPuzzle = new ArrayList<>();

	private static SubImageService service = SubImageService.getInstance();

	/**
	 * Adds the sub-images to the {@code ArrayList} in order they came
	 */
	private void init() {
		for (int i = 0; i < 16; i++) {
			subimages.add(new SubImage(service.getABSOLUTE_PATH() + "/subimage_" + i + ".jpg"));
		}
	}

	/**
	 * Calculates the Euclid's distance between {@code subimage1}'s bottom border
	 * and {@code subimage2}'s top border
	 * 
	 * @param subimage1 first sub-image
	 * @param subimage2 second sub-image
	 * @return the Euclid's distance
	 */
	private double calculateDistanceToTopBorder(SubImage subimage1, SubImage subimage2) {
		double coef = 0;

		Iterator<Color> i1 = subimage1.getBottomBorder().iterator();
		Iterator<Color> i2 = subimage2.getTopBorder().iterator();

		while (i1.hasNext() && i2.hasNext()) {
			coef += Math.sqrt(Math.pow(Math.abs(i1.next().getRed() - i2.next().getRed()), 2)
					+ Math.pow(Math.abs(i1.next().getGreen() - i2.next().getGreen()), 2)
					+ Math.pow(Math.abs(i1.next().getBlue() - i2.next().getBlue()), 2));
		}

		return coef / subimage1.getBottomBorder().stream().count();
	}

	/**
	 * Detect which sub-image fits {@code subImage} on the bottom border by
	 * calculating Euclid's distance
	 * 
	 * @param subImage the initial sub-image
	 * @return sub-image which fits the initial sub-image
	 */
	private SubImage calculateFitImageTopBorder(SubImage subImage) {
		double coef = 100;

		SubImage fitImage = null;

		for (SubImage s : subimages) {
			if (calculateDistanceToTopBorder(subImage, s) < coef) {
				coef = calculateDistanceToTopBorder(subImage, s);
				fitImage = s;
			}
		}

		return fitImage;
	}

	/**
	 * Calculates the Euclid's distance between {@code subimage1}'s right border
	 * and {@code subimage2}'s left border
	 * 
	 * @param subimage1 first sub-image
	 * @param subimage2 second sub-image
	 * @return the Euclid's distance
	 */
	private double calculateDistanceToLeftBorder(SubImage subimage1, SubImage subimage2) {
		double coef = 0;

		Iterator<Color> i1 = subimage1.getRightBorder().iterator();
		Iterator<Color> i2 = subimage2.getLeftBorder().iterator();

		while (i1.hasNext() && i2.hasNext()) {
			coef += Math.sqrt(Math.pow(Math.abs(i1.next().getRed() - i2.next().getRed()), 2)
					+ Math.pow(Math.abs(i1.next().getGreen() - i2.next().getGreen()), 2)
					+ Math.pow(Math.abs(i1.next().getBlue() - i2.next().getBlue()), 2));
		}

		return coef / subimage1.getLeftBorder().stream().count();
	}

	/**
	 * Detect which sub-image fits {@code subImage} on the right border by
	 * calculating Euclid's distance
	 * 
	 * @param subImage the initial sub-image
	 * @return sub-image which fits the initial sub-image
	 */
	private SubImage calculateFitImageLeftBorder(SubImage subImage) {
		double coef = 100;

		SubImage fitImage = null;

		for (SubImage s : subimages) {
			if (calculateDistanceToLeftBorder(subImage, s) < coef) {
				coef = calculateDistanceToLeftBorder(subImage, s);
				fitImage = s;
			}
		}

		return fitImage;
	}

	private int counter = 8;

	/**
	 * The algorithm
	 * 
	 * @throws IOException if it's impossible to write the sub-image into a given path
	 */
	public void mainAlgo() throws IOException {
		init();
		// add first element to solved puzzle
		SubImage first = subimages.get(0);

		ImageIO.write(first.getImage(), "jpg", new File(service.getABSOLUTE_PATH() + "/subimage_" + 0 + ".jpg"));

		subimages.remove(first);
		solvedPuzzle.add(first);

		SubImage fitImage = calculateFitImageLeftBorder(first);
		subimages.remove(fitImage);
		solvedPuzzle.add(fitImage);

		ImageIO.write(fitImage.getImage(), "jpg", new File(service.getABSOLUTE_PATH() + "/subimage_" + 1 + ".jpg"));

		for (int i = 2; i < 4; i++) {
			fitImage = calculateFitImageLeftBorder(fitImage);
			subimages.remove(fitImage);
			solvedPuzzle.add(fitImage);

			ImageIO.write(fitImage.getImage(), "jpg", new File(service.getABSOLUTE_PATH() + "/subimage_" + i + ".jpg"));
		}

		fitImage = calculateFitImageTopBorder(first);
		subimages.remove(fitImage);
		solvedPuzzle.add(fitImage);

		ImageIO.write(fitImage.getImage(), "jpg", new File(service.getABSOLUTE_PATH() + "/subimage_" + 4 + ".jpg"));

		for (int i = 5; i < 8; i++) {
			fitImage = calculateFitImageLeftBorder(fitImage);
			subimages.remove(fitImage);
			solvedPuzzle.add(fitImage);

			ImageIO.write(fitImage.getImage(), "jpg", new File(service.getABSOLUTE_PATH() + "/subimage_" + i + ".jpg"));
		}

		for (int j = 4, k = 8; j < 9; j += 4, k += 4) {
			fitImage = calculateFitImageTopBorder(solvedPuzzle.get(j));
			subimages.remove(fitImage);
			solvedPuzzle.add(fitImage);

			ImageIO.write(fitImage.getImage(), "jpg", new File(service.getABSOLUTE_PATH() + "/subimage_" + k + ".jpg"));

			counter++;
			for (int i = 0; i < 3; i++) {
				fitImage = calculateFitImageLeftBorder(fitImage);
				subimages.remove(fitImage);
				solvedPuzzle.add(fitImage);
				ImageIO.write(fitImage.getImage(), "jpg",
						new File(service.getABSOLUTE_PATH() + "/subimage_" + counter + ".jpg"));
				counter++;
			}
		}
	}
}
