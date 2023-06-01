package mbuchatskyi.command;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import mbuchatskyi.model.ImageEntity;
import mbuchatskyi.repository.SubImageRepository;
import mbuchatskyi.splitter.Splitter;
import mbuchatskyi.splitter.SubImagesInformation;

/**
 * The command class that can split a given image into many (at least 16) sub-images and save them into jpg-files.
 *
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
	    	image = ImageIO.read(file);
		//  image = ImageIO.read(new File("src/main/webapp/WEB-INF/baseimage.jpg"));
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
		for (int j = 0; j < image.getHeight(); j += image.getHeight() / rows) {
			for (int i = 0; i < image.getWidth(); i += image.getWidth() / columns) {
				BufferedImage subimage = image.getSubimage(i, j, (int) subimages.get(counter).getWidth(),
						(int) subimages.get(counter).getHeight());

				ImageIO.write(subimage, "jpg", new File(request.getServletContext().getRealPath("/subimage") + counter + ".jpg"));

			 // ImageIO.write(subimage, "jpg", new File("src/main/webapp/WEB-INF/subimages/subimage_" + counter + ".jpg"));
			 counter++;
			}
		}
		counter = 0;
	}
}
