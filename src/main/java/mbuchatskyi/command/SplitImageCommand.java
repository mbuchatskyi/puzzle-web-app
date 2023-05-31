package mbuchatskyi.command;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import mbuchatskyi.splitter.Splitter;
import mbuchatskyi.splitter.SubImagesInformation;

/**
 * 
 */
public class SplitImageCommand {
	public static void main(String[] args) throws IOException {
		new SplitImageCommand().execute();
	}

	private static int counter = 0;

	public void execute() throws IOException {
		BufferedImage image = null;
		try {
			// read the given image
			image = ImageIO.read(new File("src/baseimage.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// create the new instance of Splitter
		Splitter splitter = new Splitter(image.getWidth(), image.getHeight());

		// list of sub-images' info
		List<SubImagesInformation> subimages = splitter.split();

		int columns = splitter.getColumns();
		int rows = splitter.getRows();
		
		for (int j = 0; j < image.getHeight(); j += image.getHeight()/rows) {
		 for (int i = 0; i < image.getWidth(); i += image.getWidth()/columns) {
			 	
				ImageIO.write(image.getSubimage(i, j, (int) subimages.get(counter).getWidth(), (int) subimages.get(counter).getHeight()), "jpg", new File("src/subimages/subimage_" + counter + ".png")); ;
				counter++;
		    }
		}
		
	}
}
