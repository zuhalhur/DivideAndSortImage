package hw1;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	public static void main(String [] args) throws IOException{ 
		
		System.out.println("Enter the name of image : ");
		Scanner scan=new Scanner(System.in);
		String file=scan.nextLine();
		DividePixels d=new DividePixels();
		
		
		
		d.image=d.readImage(file);
		DividePixels.b=new BufferedImage[(DividePixels.image.getHeight()*DividePixels.image.getWidth())/100];
		
		d.cal=new double[(DividePixels.image.getHeight()*DividePixels.image.getWidth())/100];
		d.dividePixelImage(DividePixels.image);
		
	   
		for(int i=0;i<d.cal.length;i++){
	      
			d.cal[i]=d.calculateIntensity(d.b[i]);
	    
	}
	    
	       
		int low = 0;
		int high = d.cal.length-1;
 
		quickSort(d.cal, low, high);
		
		d.writeImage();
		
		radixsort(d.cal);
		//control the radixSort method 
		/**/
		for(int i=0;i<d.cal.length;i++){
			System.out.println(d.cal[i]);
		}
		
	}
	
	/*I use quickSort because the last digit of my studentID is odd.
	 *But, of course bubble sort algorithm is easier than the quickSort. 
	 * 
	 * */
	    public static void quickSort(double[] arr, int low, int high) {
			if (arr == null || arr.length == 0)
				return;
	 
			if (low >= high)
				return;
	 
			
			int middle = low + (high - low) / 2;//determine the pivot
			double pivot = arr[middle];
	 
			
			int i = low, j = high;
			while (i <= j) {
				while (arr[i] < pivot) {
					i++;
				}
	 
				while (arr[j] > pivot) {
					j--;
				}
	 
				if (i <= j) {
					double temp = arr[i];
					BufferedImage tmp = DividePixels.b[i];  //swap the images which divided.
					arr[i] = arr[j];
					DividePixels.b[i]=DividePixels.b[j];
					arr[j] = temp;
					DividePixels.b[j]=tmp;
					i++;
					j--;
				}
			}
	 
			
			if (low < j)
				quickSort(arr, low, j);
	 
			if (high > i)
				quickSort(arr, i, high);
		}
	    
	    /*I tried this sort and sorted is true.
	     * But I can not change for state of images.
	     * */
	    
	   public static void radixsort( double[] a)
	    {
	        int i;
	        double m = a[0], exp = 1.0, n = a.length;
	        int[] x = new int[DividePixels.b.length];
	        for (i = 1; i < n; i++)
	            if (a[i] > m)
	                m = a[i];
	         
	        while (m / exp > 0)
	        {
	            double[] bucket = new double[DividePixels.b.length];
	           

	            for (i = 0; i < n; i++)
	                bucket[(int) ((a[i] / exp) % 10)]++;
	            for (i = 1; i < 10; i++)
	                bucket[i] += bucket[i - 1];
	            for (i = (int) (n-1); i >= 0; i--)
	            	x[(int) --bucket[(int) ((a[i] / exp) % 10)]] = (int) a[i];
	            	
	            for (i = 0; i < n; i++)	            		
	            	 a[i] = x[i];
	            
	            exp *= 10.0;        
	        }
	        
	       
		
}
}

	

