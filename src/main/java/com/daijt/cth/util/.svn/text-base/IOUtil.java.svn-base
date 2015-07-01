package com.daijt.cth.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

public class IOUtil {
	
	private static Logger log = Logger.getLogger(IOUtil.class);
	
	public boolean isFileExist(String filePath) {

		File file = new File(filePath);
		
		return file.exists();
	}
	
	public boolean generateImageFile(String fontPath, float fontSize, String message,
			String desPath, String imgFmt, int imgWidth, int imgHeight) {
		boolean result = false;
		
		BufferedImage bi = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
		
		File fontFile = new File(fontPath);
		File outputfile = new File(desPath);
		
		Graphics2D ig2 = null;
		
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
		
			Font nfont = font.deriveFont(fontSize);
			
			ig2 = bi.createGraphics();
			
			ig2.setFont(nfont);
			
			FontMetrics fontMetrics = ig2.getFontMetrics();
			int stringWidth = fontMetrics.stringWidth(message);
		    int stringHeight = fontMetrics.getAscent();
		    
		    // Adjust font size if text is too width to fix in
		    if (stringWidth > imgWidth) {
		    	float newFontSize = (float)Math.ceil(fontSize * fontSize / stringWidth);
		    	nfont = font.deriveFont(newFontSize);
		    	ig2.setFont(nfont);
		    	fontMetrics = ig2.getFontMetrics();
		    	stringWidth = fontMetrics.stringWidth(message);
			    stringHeight = fontMetrics.getAscent();
		    }
		    
//		    ig2.setColor(Color.WHITE);
//		    ig2.fillRect(0, 0, imgWidth, imgHeight);
		    ig2.setPaint(Color.BLACK);
		    ig2.drawString(message, (imgWidth - stringWidth) / 2, imgHeight / 2 + stringHeight / 4);
		    
		    ImageIO.write(bi, imgFmt, outputfile);
		    
		    bi.flush();
		    
		    result = true;
			
		}
		catch(FontFormatException ffe) {
			log.error("Failed to create font: ", ffe);
		}
		catch(IOException ioe) {
			log.error("Failed generate PNG file: ", ioe);
		}
		finally {
			if (ig2 != null) {
				ig2.dispose();
			}
		}
		
		return result;
	}
}
