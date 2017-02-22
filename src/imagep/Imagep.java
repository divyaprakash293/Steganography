/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagep;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;

/**
 *
 * @author Divya Prakash
 */


public class Imagep {

    
     private byte[] decode_text(byte[] image)
	{
		int length = 0;
		int offset  = 32;
		//loop through 32 bytes of data to determine text length
		for(int i=0; i<32; ++i) //i=24 will also work, as only the 4th byte contains real data
		{
			length = (length << 1) | (image[i] & 1);
		}
		
		byte[] result = new byte[length];
		
		//loop through each byte of text
		for(int b=0; b<result.length; ++b )
		{
			//loop through each bit within a byte of text
			for(int i=0; i<8; ++i, ++offset)
			{
				//assign bit: [(new byte value) << 1] OR [(text byte) AND 1]
                result[b] = (byte)((result[b] << 1) | (image[offset] & 1));
			}
		}
		return result;
	}

    
    public static void main(String[] args) {
        
        Imagep p=new Imagep();
        Scanner s=new Scanner(System.in);
         BufferedImage sourceImage1 = null;
        try {
            System.out.println("enter the file name in desktop");
            String fl=s.nextLine();
            sourceImage1 = ImageIO.read(new File("C:\\Users\\Divya Prakash\\Desktop\\"+fl+".bmp"));

        } catch (IOException e) {
        }
        
         int type1 = sourceImage1.getType();
        int w1 = sourceImage1.getWidth();
        int h1 = sourceImage1.getHeight();
        byte[] arr1 = null;
        if (type1 == BufferedImage.TYPE_3BYTE_BGR) {
            arr1 = (byte[]) sourceImage1.getData().getDataElements(0, 0, w1, h1, null);
       
        }
        
        byte[] dec=p.decode_text(arr1);
        String te=new String(dec);
        System.out.println(te);
        
        
    }
    
}
