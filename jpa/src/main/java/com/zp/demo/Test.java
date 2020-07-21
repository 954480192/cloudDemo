package com.zp.demo;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.export.styler.ExcelExportStylerColorImpl;
//import fr.opensagres.odfdom.converter.core.BasicURIResolver;
//import fr.opensagres.odfdom.converter.core.FileImageExtractor;
//import fr.opensagres.odfdom.converter.xhtml.XHTMLConverter;
//import fr.opensagres.odfdom.converter.xhtml.XHTMLOptions;
import fr.opensagres.poi.xwpf.converter.core.ImageManager;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.hibernate.engine.jdbc.ReaderInputStream;
import org.springframework.util.FileCopyUtils;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        final String path = "C:\\Users\\unimas\\Desktop\\";
        try {
//            ImportParams params = new ImportParams();
//            List<vo> vos = ExcelImportUtil.importExcel(new File("C:\\Users\\unimas\\Desktop\\111.xlsx"),vo.class,params);


//            ExportParams params = new ExportParams();
//            params.setType(ExcelType.XSSF);
//            params.setColor(HSSFColor.HSSFColorPredefined.SKY_BLUE.getIndex());
//            params.setStyle(ExcelExportStylerColorImpl.class);
//
//            List<vo> vos = new ArrayList<>();
//            vo vo = new vo();
//            vo.setZh("11111111111111111111");
//            vos.add(vo);
//            Workbook workbook = ExcelExportUtil.exportExcel(params,vo.class,vos);
//            FileOutputStream out = new FileOutputStream("C:\\\\Users\\\\unimas\\\\Desktop\\\\111.xlsx");
//            workbook.write(out);
//            out.close();


//            final String file = "111.doc";
//            InputStream input = new FileInputStream(path + file);
//            HWPFDocument wordDocument = new HWPFDocument(input);
//            WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
//                    DocumentBuilderFactory.newInstance().newDocumentBuilder()
//                            .newDocument());
//            wordToHtmlConverter.setPicturesManager(new PicturesManager() {
//                public String savePicture(byte[] content, PictureType pictureType,
//                                          String suggestedName, float widthInches, float heightInches) {
//                    return suggestedName;
//                }
//            });
//            wordToHtmlConverter.processDocument(wordDocument);
//            Document htmlDocument = wordToHtmlConverter.getDocument();
//            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//            DOMSource domSource = new DOMSource(htmlDocument);
//            StreamResult streamResult = new StreamResult(outStream);
//            TransformerFactory tf = TransformerFactory.newInstance();
//            Transformer serializer = tf.newTransformer();
//            serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
//            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
//            serializer.setOutputProperty(OutputKeys.METHOD, "html");
//            serializer.transform(domSource, streamResult);
//            outStream.close();
//            String content = new String(outStream.toByteArray());
//            FileCopyUtils.copy(outStream.toByteArray(),new File(path, "1.html"));

            final String file = "111.docx";
            InputStream input = new FileInputStream(path + file);
            XWPFDocument document = new XWPFDocument(input);
            File imageFolderFile = new File(path);
            //加载html页面时图片路径
            XHTMLOptions options = XHTMLOptions.create();
            //图片保存文件夹路径
            options.setImageManager(new ImageManager(imageFolderFile,"img"));
            OutputStream out = new FileOutputStream(new File(path, "1.docx"));
            XHTMLConverter.getInstance().convert(document, out, options);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
