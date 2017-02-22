/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagep;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

/**
 *
 * @author Divya Prakash
 */
public class JavaApplication10 {

   
    	    private byte[] encode_text(byte[] image, byte[] addition, int offset)

    {

        //check that the data + offset will fit in the image

        if(addition.length + offset > image.length)

        {

            throw new IllegalArgumentException("File not long enough!");

       }

        //loop through each addition byte

        for(int i=0; i<addition.length; ++i)

        {

            //loop through the 8 bits of each byte

            int add = addition[i];

            for(int bit=7; bit>=0; --bit, ++offset) //ensure the new offset value carries on through both loops

            {

                //assign an integer to b, shifted by bit spaces AND 1

                //a single bit of the current byte

                int b = (add >>> bit) & 1;

                //assign the bit by taking: [(previous byte value) AND 0xfe] OR bit to add

                //changes the last bit of the byte in the image to be the bit of addition

                image[offset] = (byte)((image[offset] & 0xFE) | b );

            }

        }

        return image;

    }


           

       
        
        public static void main(String[] args) {

            
            Scanner s=new Scanner(System.in);
            
            System.out.println("strt");
        BufferedImage sourceImage = null,sourceImage2=null;
        try {
            System.out.println("enter the file name in desktop");
            String fl=s.nextLine();
            sourceImage = ImageIO.read(new File("C:\\Users\\Divya Prakash\\Desktop\\"+fl+".bmp"));

        } catch (IOException e) {
        }

       int type = sourceImage.getType();
        int w = sourceImage.getWidth();
        int h = sourceImage.getHeight();
        byte[] arr = null;
        if (type == BufferedImage.TYPE_3BYTE_BGR) {
            System.out.println("type.3byte.bgr");
            arr = (byte[]) sourceImage.getData().getDataElements(0, 0, w, h, null);
        }
        
        
        
        
    String a=s.nextLine();  //string to be encrypted
    
    byte text[]=a.getBytes();
    
      
        
    int i=a.length();
        byte byte3 = (byte)((i & 0xFF000000) >>> 24); //0

        byte byte2 = (byte)((i & 0x00FF0000) >>> 16); //0

        byte byte1 = (byte)((i & 0x0000FF00) >>> 8 ); //0

        byte byte0 = (byte)((i & 0x000000FF)       );
    
        byte len[]=new byte[]{byte3,byte2,byte1,byte0};

        JavaApplication10 p=new JavaApplication10();
        

        
       
         p.encode_text(arr, len,  0); //encoding the lenght of text
          p.encode_text(arr, text, 32);//encoding the text
 
        try {
            BufferedImage edgesImage = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
            edgesImage.getWritableTile(0, 0).setDataElements(0, 0, w, h, arr);
            
            System.out.println("enter new file name");
            String nm=s.nextLine();
            ImageIO.write(edgesImage, "bmp", new File("C:\\Users\\Divya Prakash\\Desktop\\"+nm+".bmp"));
            
            System.out.println("done");
        } catch (IOException e) {
        }
        
       
        System.out.println(w);
        System.out.println(h);
        
       

      
        
    }
        
        
    }
    
    
    

