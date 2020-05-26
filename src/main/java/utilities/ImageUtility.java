package utilities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import dao.ArtworkDAO;
import objects.Artwork;

public class ImageUtility {
	 
    /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     * @throws IOException
     */
    public static void resize(String inputImagePath,
            String outputImagePath, int scaledWidth, int scaledHeight){
        // reads input image
        BufferedImage inputImage = getImageFile(inputImagePath);
 
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);
 
        // writes to output file
        try {
			ImageIO.write(outputImage, formatName, new File(outputImagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    /**
     * Resizes an image by a percentage of original size (proportional).
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param percent a double number specifies percentage of the output image
     * over the input image.
     * @throws IOException
     */
    public static void resize(String inputImagePath,
            String outputImagePath, double percent){
        BufferedImage inputImage = getImageFile(inputImagePath);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
    }
    
    public static void generateThumbnail(String inputImagePath, String outputImagePath, int size){
        BufferedImage inputImage = getImageFile(inputImagePath);

        int width = (int) (inputImage.getWidth());
        int height = (int) (inputImage.getHeight());
        
        double percent = size;
        
        if(width < height) {
        	percent = percent/height;
        }else {
        	percent = percent/width;
        }
        resize(inputImagePath, outputImagePath, percent);
    }
    
    
    public static String getThumbnail(int id, int thumb_size) {
    	File f = new File(getImagePath(id, thumb_size));
    	if (!f.exists()) {
    		generateThumbnail(getImagePath(id, 0), getImagePath(id, thumb_size), thumb_size);
    	}
    	return getImagePath(id, thumb_size);
    }
    
    //Takes in an Artwork DB object and outputs the location where it's stored.
    //Setting thumb_size to 0 or below will cause the returned path to refer to the original art asset
    //Any other size will direct you to the thumbnail of that size's location.
    public static String getImagePath(Artwork art, int thumb_size) {
		if(thumb_size <= 0) {
			return "C:\\collective-backend\\assets\\" + art.getArtwork_id() + "\\" + art.getArtwork_id() + "." + art.getFile_type();
		}else {
			return "C:\\collective-backend\\assets\\" + art.getArtwork_id() + "\\"+ art.getArtwork_id() + "_" + thumb_size +  "px." + art.getFile_type();
		}
    }
    
    public static String getImagePath(int id, int thumb_size) {
		Artwork art = null;
		art = ArtworkDAO.get(id);
		return getImagePath(art, thumb_size);
    }
    
    public static BufferedImage getImageFile(String path) {
        File file = new File(path);
        BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
    }
}
