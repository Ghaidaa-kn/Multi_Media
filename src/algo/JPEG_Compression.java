package algo;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.RenderedImage;
import java.io.*;


public class JPEG_Compression extends Compression {

    public JPEG_Compression(){}

    public static void compress(String input , String out1) throws IOException {

        DataInputStream read = new DataInputStream(new BufferedInputStream(
                new FileInputStream(input)));

        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
                new FileOutputStream(out1)));

        float quality = 0.1f;

        RenderedImage image = ImageIO.read(read) ;
        ImageWriter jpegwriter = ImageIO.getImageWritersByFormatName("jpg").next();
        ImageWriteParam jpegWriteParam = jpegwriter.getDefaultWriteParam();
        jpegWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpegWriteParam.setCompressionQuality(quality);

        try(ImageOutputStream output = ImageIO.createImageOutputStream(out)){
            jpegwriter.setOutput(output);
            IIOImage outputimage = new IIOImage(image , null ,null);
            jpegwriter.write(null , outputimage , jpegWriteParam );
        }
        jpegwriter.dispose();
        read.close();
        out.close();
    }

    @Override
    public void DirectoryCompression(String var1, String var2) throws IOException {

    }

    @Override
    public int[] compression(String var1, String var2) {
        return new int[0];
    }

    @Override
    public void decompression(String var1, String var2) {

    }



    /*public static void main(String[] args){

        File in = new File("C:\\Users\\ASUS\\Desktop\\gh.jpg");
        File ou = new File("C:\\Users\\ASUS\\Desktop\\ghaidaa.jpeg");

        try{
            compress(in , ou , 0.1f);
            System.out.println("Done!");

        }catch (IOException e){

        }

    }*/

}
