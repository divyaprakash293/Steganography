
package imagep;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.*;
import javax.imageio.ImageIO;


public class JavaApplication10 {

   
 private byte[] encode_text(byte[] image, byte[] addition, int offset)

    {
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
public char[] encrypt()
    {
        
     int temp = 0;
     String ptext;
    String key;
    int s[] = new int[128];
    int k[] = new int[128];
    
    Scanner  in =new Scanner(System. in );
    System.out.print("\nENTER PLAIN TEXT\t");
    ptext = in.nextLine();
    System.out.print("\n\nENTER KEY TEXT\t\t");
    key = in.nextLine();
    
    char ptextc[] = ptext.toCharArray();
    char keyc[] = key.toCharArray();
    int cipher[] = new int[ptext.length()];
    int decrypt[] = new int[ptext.length()];
    int ptexti[] = new int[ptext.length()];
    int keyi[] = new int[key.length()];
    
    for (int i=0; i<ptext.length(); i++)
    {
        ptexti[i] = (int) ptextc[i];
    }
    for (int i = 0; i<key.length(); i++)
    {
        keyi[i] = (int) keyc[i];
    }
    for (int i = 0; i < 127; i++)
    {
        s[i] = i;
        k[i] = keyi[i % key.length()];
    }
    int j = 0;
    for (int i = 0; i < 127; i++)
    {
        j = (j + s[i] + k[i]) % 128;
        temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
    int i = 0;
    j = 0;
    int z = 0;
for (int l = 0; l < ptext.length(); l++)
    {
        i = (l + 1) % 128;
        j = (j + s[i]) % 128;
        temp = s[i];
        s[i] = s[j];
        s[j] = temp;
        z = s[(s[i] + s[j]) % 128];
        cipher[l] = z ^ ptexti[l];
    }
   
    char arr[]=new char[cipher.length];
    for(int y=0;y<cipher.length;y++)
    arr[y]=(char)cipher[y];



    return arr;
    }
           


        public static void main(String[] args) {

            
            Scanner s=new Scanner(System.in);
            
            System.out.println("strt");
            BufferedImage sourceImage = null,sourceImage2=null;
        try {
            System.out.println("enter the file name in desktop");
            String fl=s.nextLine();
            sourceImage = ImageIO.read(new File("C:\\Users\\Divya Prakash\\Desktop\\"+fl+".bmp"));

            } catch (IOException e)
            {
            }

        int type = sourceImage.getType();
        int w = sourceImage.getWidth();
        int h = sourceImage.getHeight();
        byte[] arr = null;
        if (type == BufferedImage.TYPE_3BYTE_BGR)
        {
            System.out.println("type.3byte.bgr");
            arr = (byte[]) sourceImage.getData().getDataElements(0, 0, w, h, null);
        }
        
        
        JavaApplication10 qwe=new JavaApplication10();
        
        char arr1[]=qwe.encrypt();
        
        byte [] by=new byte[arr1.length];
        for(int i=0;i<arr1.length;i++)
            by[i]=(byte)(arr1[i]);
        
        String a=new String(by);
    
        System.out.println(a);
    
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
    }    
 }
    
    
    

