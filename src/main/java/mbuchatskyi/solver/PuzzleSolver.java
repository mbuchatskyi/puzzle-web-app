package mbuchatskyi.solver;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mbuchatskyi.model.SubImage;
import mbuchatskyi.service.SubImageService;

public class PuzzleSolver {
	private static List<SubImage> subimages = new ArrayList<>();
	private List<SubImage> solvedPuzzle = new ArrayList<>();

	private static SubImageService service = SubImageService.getInstance();

	private void init() {
		for (int i = 0; i < 16; i++) {
			subimages.add(new SubImage(service.getABSOLUTE_PATH() + "/subimage_" + i + ".jpg"));
		}
	}

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

	private void mainAlgo() {
		init();
		// add first element to solved puzzle
		SubImage first = subimages.get(0);
		subimages.remove(first);
		solvedPuzzle.add(first);

		SubImage fitImage = calculateFitImageLeftBorder(first);
		subimages.remove(fitImage);
		solvedPuzzle.add(fitImage);

		for (int i = 0; i < 2; i++) {
			fitImage = calculateFitImageLeftBorder(fitImage);
			subimages.remove(fitImage);
			solvedPuzzle.add(fitImage);
		}

		fitImage = calculateFitImageTopBorder(first);
		subimages.remove(fitImage);
		solvedPuzzle.add(fitImage);

		for (int i = 0; i < 3; i++) {
			fitImage = calculateFitImageLeftBorder(fitImage);
			subimages.remove(fitImage);
			solvedPuzzle.add(fitImage);
		}

		for (int j = 4; j < 9; j += 4) {
			fitImage = calculateFitImageTopBorder(solvedPuzzle.get(j));
			subimages.remove(fitImage);
			solvedPuzzle.add(fitImage);

			for (int i = 0; i < 3; i++) {
				fitImage = calculateFitImageLeftBorder(fitImage);
				subimages.remove(fitImage);
				solvedPuzzle.add(fitImage);
			}
		}

		for (int i = 0; i < 16; i++) {
			System.out.println(solvedPuzzle.get(i).getFile().getName());
		}
	}

	public static void main(String[] args) {
		PuzzleSolver puzzleSolver = new PuzzleSolver();
		puzzleSolver.mainAlgo();
	}
}
