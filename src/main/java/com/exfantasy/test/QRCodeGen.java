package com.exfantasy.test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * http://crunchify.com/java-simple-qr-code-generator-example/
 * 
 * http://zxing.org/w/docs/javadoc/index.html
 * 
 * http://stackoverflow.com/questions/9835774/how-do-i-encode-characters-using-utf-8-in-a-qr-code-using-zxing-project
 */
public class QRCodeGen {

	public static void main(String[] args) {
		String myCodeText = "Ben, Jeff 說他暗戀你很久了";
		String filePath = "D:/Temp/CrunchifyQR.png";

		int size = 125;
		String fileType = "png";
		File myFile = new File(filePath);
		try {
			Hashtable hintMap = new Hashtable();
			hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
			
			int matrixWidth = bitMatrix.getWidth();
			BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
			
			image.createGraphics();

			Graphics2D graphics = (Graphics2D) image.getGraphics();

			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, matrixWidth, matrixWidth);

			graphics.setColor(Color.BLACK);
			for (int i = 0; i < matrixWidth; i++) {
				for (int j = 0; j < matrixWidth; j++) {
					if (bitMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}

			ImageIO.write(image, fileType, myFile);
		} 
		catch (WriterException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\n\nYou have successfully created QR Code.");
	}

}
