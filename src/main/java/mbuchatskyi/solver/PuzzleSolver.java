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
		for (int i = 4; i < 8; i++) {
			subimages.add(new SubImage(service.getABSOLUTE_PATH() + "/subimage_" + i + ".jpg"));
		}
		for (int i = 0; i < 4; i++) {
			subimages.add(new SubImage(service.getABSOLUTE_PATH() + "/subimage_" + i + ".jpg"));
		}
	}
	
	private double calculateDistanceToRightBorder(SubImage subimage1, SubImage subimage2) {
		double coef = 0;

		Iterator<Color> i1 = subimage1.getRightBorer().iterator();
		Iterator<Color> i2 = subimage2.getLeftBorder().iterator();
		
		while(i1.hasNext() && i2.hasNext()) 
		{
			coef += Math.sqrt(Math.pow(Math.abs(i1.next().getRed() - i2.next().getRed()), 2)
					+ Math.pow(Math.abs(i1.next().getGreen() - i2.next().getGreen()), 2)
					+ Math.pow(Math.abs(i1.next().getBlue() - i2.next().getBlue()), 2)
					);
		} 
	
		return coef/subimage1.getLeftBorder().stream().count();
	}
	
	private SubImage calculateFitImageRightBorder(SubImage subImage) {
		double coef = 100;
		
		SubImage fitImage = null; 
		
		for (SubImage s : subimages) {
			if (calculateDistanceToRightBorder(subImage, s) < coef) {
				coef = calculateDistanceToRightBorder(subImage, s);
                fitImage = s;			
			} 
		}
		
		return fitImage;
	}
	
	private void puzzleAlgo() {
		// add first element to solved puzzle
		SubImage first = subimages.get(0);
		subimages.remove(0);

		solvedPuzzle.add(first);
		
		SubImage fitImage = calculateFitImageRightBorder(first);
		subimages.remove(fitImage);
		solvedPuzzle.add(fitImage);
		
		fitImage =  calculateFitImageRightBorder(fitImage);
		subimages.remove(fitImage);
		solvedPuzzle.add(fitImage);
	
		fitImage = calculateFitImageRightBorder(fitImage);
		subimages.remove(fitImage);
		solvedPuzzle.add(fitImage);

	}
	
	public static void main(String[] args) {
		PuzzleSolver puzzleSolver = new PuzzleSolver();
		puzzleSolver.init();
		puzzleSolver.puzzleAlgo();
	}
}
