package utilities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import dao.ArtworkDAO;
import dao.SqlSessionContainer;
import interfaces.Mapper;
import objects_table.Artwork;

public class ImageUtility {
	//base path is the project directory
	
	public static void iterateOverFiles(String path) {
		File dir = new File(path);
		File[] directoryListing = dir.listFiles();
		
		String query = "SELECT id FROM public.artworks ORDER BY id ASC;";
		
		long[] ids = SqlSessionContainer.getSession().getMapper(Mapper.class).executeImmediate(query);
		
		ArrayList<Artwork> art_list = (ArrayList)ArtworkDAO.getInstance().getSet(ids);

		if (directoryListing != null) {
			for (int i = 0; i < directoryListing.length; i++) {
				String new_name = directoryListing[i].getPath();
				new_name = new_name.substring(new_name.lastIndexOf("\\") + 1);
				String[] toks = new_name.split("\\.");
				Artwork a = art_list.get(i);
				String base_name = getUrlName(a.getUrl());
				String qualified_name = base_name + "." + toks[1];
				a.setFile_type("." + toks[1]);
				a.setUrl(qualified_name);
				File new_name_file = null;
				try {
					new_name_file = new File(dir.getCanonicalPath() + "\\" + qualified_name);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Path new_path = new_name_file.toPath();
				Path source = directoryListing[i].toPath();
				try {
				     Files.move(source, source.resolveSibling(new_path));
				} catch (IOException e) {
				     e.printStackTrace();
				}
			}
		} else {
			// Handle the case where dir is not really a directory.
			// Checking dir.isDirectory() above would not be sufficient
			// to avoid race conditions with another process that deletes
			// directories.
		}
	}
	
	/*
    public static String getImagePath(Artwork art) {
		return "/var/ftp/pub/collective-assets/assets/" + art.getUrl();
    }

    public static String getImagePath(long id) {
		Artwork art = null;
		art = ArtworkDAO.getInstance().get(id);
		return getImagePath(art);
    }
	 */

	public static String getImageUrl(Artwork art) {
		return "http://the-collective.cc/images/" + art.getUrl();
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

	private static String getUrlName(String fileName) {
		if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(0, fileName.lastIndexOf("."));
		else return "";
	}
	
	private static String getFileExtension(String fileName) {
		if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".")+1);
		else return "";
	}
}
