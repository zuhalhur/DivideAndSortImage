package hw1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*Problem Description :  Divide image into ten images then calculate their intensity and then 
 * we sort their values and combine the divided image according to the sorted intensity.
 * 
 * Solution : quickSort
 * 
 * readImage() method --> this method read the image which choosen by user.
 * calculateIntensity() method --> this method calculate intensity of all pixels in image.
 * dividePixelImage() method --> this method divide the image into pixels which are 10*10.
 * writeImage() method --> this method write the image that is taken is parameter.
 * */

public class DividePixels {
	public static BufferedImage image;
	static BufferedImage [] b;  //the array which keep divided images
	double [] cal;   //the array which keep calculated intensity
	 
	 
	public BufferedImage  readImage(String file){
		try {
		      // get the BufferedImage
		    	 File imagefile = new File(file);
		        image = ImageIO.read(imagefile);
		    	
		    } 
		    catch (IOException e) {
		      System.err.println(e.getMessage());
		    }
		    System.out.println("success");
	return image;	    
		  }
	
	public double calculateIntensity(BufferedImage im2){
		double intensity = 0.0;
		for(int i=0;i<im2.getWidth();i++){
		   for(int k=0;k<im2.getHeight();k++){
			 Color c=new Color(im2.getRGB(k, i));
			 intensity+=(c.getBlue()*0.114);
			 intensity+=(c.getGreen()*0.587);
			 intensity+=(c.getRed()*0.298);
			   
			  
		   }
		   
		}
		return intensity;
		
			
		
		}
	public static void dividePixelImage(BufferedImage z){
		int count=0;
		for(int i=0;i<z.getHeight();i+=10){
			for(int j=0;j<z.getWidth();j+=10){
				b[count++]=z.getSubimage(j, i, 10, 10);
				
			}
		}
	}
		public static void writeImage() throws IOException{
		    int widthImg = 0;
			int heightImg = 0;
			int counter=0;
			 try{
				 BufferedImage img = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
			 
		     Graphics g=img.getGraphics();
		     BufferedImage [][] p=new BufferedImage[image.getHeight()/10][image.getWidth()/10];
		     for(int i=0;i<p.length;i++){
		    	 for(int j=0;j<p[i].length;j++){
		    		 p[i][j]=b[counter++];
		    		 
		    	 }
		    	
		     }
		     for(int k = 0;k<p.length;k++){
		    	 for(int l =0;l<p[k].length;l++){
		    		 g.drawImage(p[k][l], widthImg, heightImg, null);
		    		 widthImg+=10;
		    	 }
		    	 heightImg+=10;
		    	 widthImg=0;
		     }
		
			
			
		ImageIO.write(img, "png", new File("sortedImage.png"));
			
	}
		catch (IOException e) {
		      System.err.println(e.getMessage());
		    }
		    System.out.println("Image is ready..");
	
		 
	}

}