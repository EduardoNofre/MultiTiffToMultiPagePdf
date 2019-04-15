/**
 * 
 */
package com.br.tiff.to.Single.jpeg;

import java.io.File;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.RandomAccessFileOrArray;
import com.lowagie.text.pdf.codec.TiffImage;

/**
 * @author eduardo.sa
 *
 */
public class TifMultiPageToSingleJPG {
	
	public static void main(String[] args) throws Exception {

		Document document = new Document();

		File file = new File("C:\\2019\\04\\11\\5673\\sBBB14.tif");

		TifMultiPageToSingleJPG.convertTIFFToPDF(file);
	}
	
	public static File convertTIFFToPDF(File tiffFile)
	{
	    File pdfFile = new File("C:\\2019\\04\\11\\5673\\output.pdf");
	    try
	    {
	        RandomAccessFileOrArray myTiffFile = new RandomAccessFileOrArray(tiffFile.getCanonicalPath());
	        
	        int numberOfPages = TiffImage.getNumberOfPages(myTiffFile);
	        
	        Document TifftoPDF = new Document();
	        
	        PdfWriter pdfWriter = PdfWriter.getInstance(TifftoPDF, new FileOutputStream(pdfFile));
	        
	        pdfWriter.setStrictImageSequence(true);
	        
	        TifftoPDF.open();

	        Image tempImage;

	        for (int i = 1; i <= numberOfPages; i++) {
	 
	            tempImage = TiffImage.getTiffImage(myTiffFile, i);
	            
	            Rectangle pageSize = new Rectangle(tempImage.getWidth(), tempImage.getHeight());
	            
	            TifftoPDF.setPageSize(pageSize);
	            
	            TifftoPDF.newPage();
	            
	            TifftoPDF.add(tempImage);
	        }
	        TifftoPDF.close();
	    }
	    catch(Exception ex)
	    {
	        ex.printStackTrace();
	    }
	        
	    return pdfFile;
	}
}
