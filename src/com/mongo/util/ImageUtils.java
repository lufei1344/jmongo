package com.mongo.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtils {

	/**
	 * @param file 图片文件
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @param isSmail 是否生产缩略图,保存文件名 s_前缀
	 * @return  和图片差距大于0.2,return false,否则true
	 */
	public static boolean processImage(File file,double width,double height,boolean isSmail){
		if(!file.exists()){
			return false;
		}
		Image i;
		try {
			i = ImageIO.read(file);
	        if(i == null){
	        	return false;
	        }
	        double iWidth = i.getWidth(null);  
	        double iHeight = i.getHeight(null);
	        
	        double wb = 0;
	        if(width>iWidth){
	        	wb = (width-iWidth)/width;
	        }else{
	        	wb = (iWidth-width)/iWidth;
	        }
	        if(wb>0.2){
	        	return false;
	        }
	        double hb = 0;
	        if(height > iHeight){
	        	hb = (height-iHeight)/height;
	        }else{
	        	hb = (iHeight - height)/iHeight;
	        }
	        if(hb>0.2){
	        	return false;
	        }
	        i = null;
	        if(isSmail){
	        	File out = new File(file.getParentFile().getAbsolutePath()+File.separator+"s_"+file.getName());
	        	resize(file,out,0.5f);
	        }
	        return true;
	        
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}  
          
	}
	
	 private static void resize(File originalFile, File resizedFile, float quality) throws IOException {  
	        if (quality > 1) {  
	            throw new IllegalArgumentException(  
	                    "Quality has to be between 0 and 1");  
	        }  
	  
	        
	        Image i = ImageIO.read(originalFile);  
	        Image resizedImage = null;  
	        if(i == null){
	        	return;
	        }
	        int iWidth = i.getWidth(null);  
	        int iHeight = i.getHeight(null);  
	        resizedImage = i.getScaledInstance(iWidth,  
	        		iHeight, Image.SCALE_SMOOTH); 
	  
	        // This code ensures that all the pixels in the image are loaded.  
	        Image temp = new ImageIcon(resizedImage).getImage();  
	  
	        // Create the buffered image.  
	        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),  
	                temp.getHeight(null), BufferedImage.TYPE_INT_RGB);  
	  
	        // Copy image to buffered image.  
	        Graphics g = bufferedImage.createGraphics();  
	  
	        // Clear background and paint the image.  
	        g.setColor(Color.white);  
	        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));  
	        g.drawImage(temp, 0, 0, null);  
	        g.dispose();  
	  
	        // Soften.  
	        float softenFactor = 0.05f;  
	        float[] softenArray = { 0, softenFactor, 0, softenFactor,  
	                1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };  
	        Kernel kernel = new Kernel(3, 3, softenArray);  
	        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);  
	        bufferedImage = cOp.filter(bufferedImage, null);  
	  
	  
	        // Encodes image as a JPEG data stream  
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(new FileOutputStream(resizedFile));  
	  
	        JPEGEncodeParam param = encoder  
	                .getDefaultJPEGEncodeParam(bufferedImage);  
	  
	        param.setQuality(quality, true);  
	  
	        encoder.setJPEGEncodeParam(param);  
	        encoder.encode(bufferedImage);  
	    } // Example usage  
	 
	 public static void main(String[] args) throws Exception{
		 System.out.println(processImage(new File("d:\\aa.gif"),1200,700,true));
		 
		 
	 }
}
