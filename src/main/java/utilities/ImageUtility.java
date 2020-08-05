package utilities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import dao.ArtworkDAO;
import objects.Artwork;

public class ImageUtility {
    //Takes in an Artwork DB object and outputs the location where it's stored.
    //Setting thumb_size to 0 or below will cause the returned path to refer to the original art asset
    //Any other size will direct you to the thumbnail of that size's location.
    public static String getImagePath(Artwork art) {
		return "C:\\collective-backend\\assets\\" + art.getId() + "." + art.getFile_type();
    }

    public static String getImagePath(long id) {
		Artwork art = null;
		art = ArtworkDAO.getInstance().get(id);
		return getImagePath(art);
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
