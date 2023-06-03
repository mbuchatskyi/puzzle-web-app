package mbuchatskyi.command;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import mbuchatskyi.service.SubImageService;
import mbuchatskyi.splitter.Splitter;
import mbuchatskyi.splitter.SubImagesInformation;

/**
 * The command class that can split a given image into many (at least 16)
 * sub-images and save them into jpg-files.
 * 
 * @see Splitter
 */
public class SplitImageCommand {
	private static int counter = 0;

	public void execute(InputStream inputStream, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		BufferedImage image = null;
		try {
			File file = new File("baseimage.jpg");
			// org.apache.commons.io library
			FileUtils.copyInputStreamToFile(inputStream, file);
			// reads the base image
			image = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println(e);
		}

		// create the new instance of Splitter
		Splitter splitter = new Splitter(image.getWidth(), image.getHeight());
		int amount = splitter.getAmount();

		// create the list of sub-images' info 
		List<SubImagesInformation> subimages = splitter.split();

		int columns = splitter.getColumns();
		int rows = splitter.getRows();

		// get the instance of SubImageService
		SubImageService service = SubImageService.getInstance();

		// set the amount of the sub-images in service
		service.setAmount(amount);

		// create the files of the sub-images 
		for (int j = 0; j < image.getHeight(); j += image.getHeight() / rows) {
			for (int i = 0; i < image.getWidth(); i += image.getWidth() / columns) {
				BufferedImage subimage = image.getSubimage(i, j, (int) subimages.get(counter).getWidth(),
						(int) subimages.get(counter).getHeight());

				ImageIO.write(subimage, "jpg", new File(service.getABSOLUTE_PATH() + "/subimage_" + counter + ".jpg"));
				counter++;
			}
		}
		counter = 0;

		// mess up some sub-images in puzzle
		service.swap(0, 12);
		service.swap(3, 15);
	}
}
