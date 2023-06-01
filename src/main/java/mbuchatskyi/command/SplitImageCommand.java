package mbuchatskyi.command;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import mbuchatskyi.model.ImageEntity;
import mbuchatskyi.repository.SubImageRepository;
import mbuchatskyi.splitter.Splitter;
import mbuchatskyi.splitter.SubImagesInformation;

/**
 * 
 */
public class SplitImageCommand {
	public static void main(String[] args) throws IOException {
		new SplitImageCommand().execute();
	}
	/**
	 * simple counter for giving the file-names of the sub-images
	 */
	private static int counter = 0;

	public void execute() throws IOException {
		BufferedImage image = null;
		try {
			// read the given image into BufferedImage
			image = ImageIO.read(new File("src/baseimage.jpg"));
		} catch (IOException e) {
			System.out.println(e);
		}

		// create the new instance of Splitter
		Splitter splitter = new Splitter(image.getWidth(), image.getHeight());

		// create the list of sub-images' info by calling split() method
		List<SubImagesInformation> subimages = splitter.split();

		int columns = splitter.getColumns();
		int rows = splitter.getRows();
		
		// get the instance of SubImageRepository
		SubImageRepository repo = SubImageRepository.getInstance();
		
		// create the files of the sub-images and write them into sub-image repo's list
		for (int j = 0; j < image.getHeight(); j += image.getHeight()/rows) {
		 for (int i = 0; i < image.getWidth(); i += image.getWidth()/columns) {
			 	BufferedImage subimage = image.getSubimage(i, j, (int) subimages.get(counter).getWidth(), (int) subimages.get(counter).getHeight());
				ImageIO.write(subimage, "jpg", new File("src/subimages/subimage_" + counter + ".png")); ;
				repo.getImages().add(new ImageEntity(counter, subimage, counter));
				counter++;
		    }
		}
	}
}
