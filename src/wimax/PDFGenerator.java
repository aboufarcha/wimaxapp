/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wimax;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Header;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.LinkedList;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Dialogs;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 *
 * @author abouf
 */
public class PDFGenerator {
    public PDFGenerator(){
        
    }
    
    public void PDFGenerating(LinkedList<Data> data,String ChartimgTitle, String ProgressImgTitle){
        try{
    /*------------------------------- SETTING PDF TITLE -------------------------------------------------------*/
          Paragraph Title = new Paragraph("Wimax statistics",FontFactory.getFont(FontFactory.TIMES_BOLD,18,Font.BOLD,BaseColor.BLACK));
          Title.setAlignment(Element.TITLE);
          
     /*------------------------------- SETTING IMAGES FIGURES -------------------------------------------------------*/
        // Statistic image
          Image statisticsImg =Image.getInstance(ChartimgTitle);
          statisticsImg.scalePercent(60);
        // Progress Image
          Image ProgressImg = Image.getInstance(ProgressImgTitle);
          ProgressImg.scalePercent(50);
    
    /*--------------------------------ADDING A DATA TABLE-----------------------------------------*/
          PdfPTable table = new PdfPTable(4);
       /*----------------------SETTING THE COLUMNES ----------------------------------------------*/
          PdfPCell col1 = new PdfPCell(new Paragraph("User id"));
          PdfPCell col2 = new PdfPCell(new Paragraph("User Classe"));
          PdfPCell col3 = new PdfPCell(new Paragraph("User Resource needed"));
          PdfPCell col4 = new PdfPCell(new Paragraph("User State"));
          col1.setBackgroundColor(BaseColor.YELLOW);
          col2.setBackgroundColor(BaseColor.YELLOW);
          col3.setBackgroundColor(BaseColor.YELLOW);
          col4.setBackgroundColor(BaseColor.YELLOW);
          table.setWidthPercentage(100);
          table.setSpacingBefore(20f);
          table.setSpacingAfter(30f);
        /*--------------------------SETTING THE TABLE HEADER---------------------------------------------*/
          table.addCell(col1);
          table.addCell(col2);
          table.addCell(col3);
          table.addCell(col4);
          table.addCell(col1);
          table.addCell(col2);
          table.addCell(col3);
          table.addCell(col4);
          table.setHeaderRows(1);
          table.setSkipFirstHeader(true);
        /*-------------------------FETCHING DATATABLE------------------------------------------------------*/
          Document doc = new Document();
          for(Data d : data){
                table.addCell(""+d.getIDUser());
                table.addCell(""+d.getClasseUser());
                table.addCell(""+d.getUsedRes());
                table.addCell(""+d.getUserState());
          }
          PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("Wimax-statistics.pdf"));
        /*---------------------- SETTING HEADER AND FOOTER --------------------------*/
          Rectangle rect = new Rectangle(100, 100, 500, 1200);
          HeaderFooterPageEvent event = new HeaderFooterPageEvent();
          writer.setPageEvent(event);
        /*------------------------------PREPARING THE PDF -------------------------------------------*/
          doc.open();
          doc.add(Title);
          doc.addTitle(Title.toString());
          doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
          doc.add(statisticsImg);
          doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
          doc.add(ProgressImg);
          doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
          doc.add(table);
          doc.close();
          
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

  /*-----------------------------------GENERATING THE HEADER AND FOOTER-----------------------------------------------------------*/
    public class HeaderFooterPageEvent extends PdfPageEventHelper {
         Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);
    public void onEndPage(PdfWriter writer,Document document) {
        PdfContentByte cb = writer.getDirectContent();
        Phrase header = new Phrase("GENERATED AT "+new Date().toInstant().toString(), ffont);
        Phrase footer = new Phrase("Created By BOUFARCHA & ACH", ffont);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,header,(document.right() - document.left()) / 2 + document.leftMargin(),document.top() + 10, 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,footer,(document.right() - document.left()) / 2 + document.leftMargin(),document.bottom() - 10, 0);
    }
} 

}
