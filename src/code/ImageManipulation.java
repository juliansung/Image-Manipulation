package code;

import image.APImage;
import image.Pixel;

import java.awt.*;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {

            rotateImage("cyberpunk2077.jpg");
    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String path) {

        APImage image = new APImage(path);
        int h = image.getHeight();
        int w = image.getWidth();

        for(int i=0; i<w; i++){
            for(int j=0; j<h; j++){
                int r = image.getPixel(i,j).getRed();
                int b = image.getPixel(i,j).getBlue();
                int g = image.getPixel(i,j).getGreen();
                int grey = (r+b+g)/3;
                Pixel greyPixel = new Pixel(grey, grey, grey);
                image.setPixel(i,j,greyPixel);

            }
        }
        image.draw();

    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        int r = pixel.getRed();
        int b = pixel.getBlue();
        int g = pixel.getGreen();
        int grey = (r+b+g)/3;
        return grey;
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String path) {

        APImage image = new APImage(path);
        int h = image.getHeight();
        int w = image.getWidth();

        for(int i=0; i<w; i++){
            for(int j=0; j<h; j++){
                int r = image.getPixel(i,j).getRed();
                int b = image.getPixel(i,j).getBlue();
                int g = image.getPixel(i,j).getGreen();
                int grey = (r+b+g)/3;
                Pixel blackPixel = new Pixel(0,0,0);
                Pixel whitePixel = new Pixel(255,255,255);
                if(grey<128){
                    image.setPixel(i,j,blackPixel);
                }else{
                    image.setPixel(i,j,whitePixel);
                }


            }
        }
        image.draw();

    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String path, int threshold) {
        APImage image = new APImage(path);

        int h = image.getHeight();
        int w = image.getWidth();

        for (int i = w-1; i > 0; i--){
            for (int j = h -1; j > 0; j--){

                int avg = getAverageColour(image.getPixel(i,j));
                int avgL = getAverageColour(image.getPixel(i-1,j));
                int avgD = getAverageColour(image.getPixel(i,j-1));
                int lDiff = avg-avgL;
                int dDiff = avg-avgD;

                int red = 255;
                int blue = 255;
                int green = 255;
                if(lDiff >= threshold || dDiff >= threshold) {
                    red = blue = green = 0;
                }
                Pixel pixel= new Pixel(red, blue, green);
                image.setPixel(i, j, pixel);
            }
        }
        image.draw();
    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String path) {

        APImage image = new APImage(path);
        reflect(image);
        image.draw();


    }

    public static void reflect(APImage image){
        int h = image.getHeight();
        int w = image.getWidth();
        int half = (w-1)/2;
        for(int i=0; i<half; i++){
            for(int j = 0; j<h-1; j++){
                Pixel temp = image.getPixel(i,j);
                image.setPixel(i,j,image.getPixel(w-i,j));
                image.setPixel(w-i,j,temp);
            }
        }
    }



    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String path) {
        APImage image = new APImage(path);
        int h = image.getHeight();
        int w = image.getWidth();
        APImage image2 = new APImage(h,w);

        for(int i=0; i<w-1; i++){
            for(int j = 0; j<h-1; j++){
                image2.setPixel(j,i,image.getPixel(i,j));
            }
        }

        reflect(image2);
        image2.draw();
    }



}
