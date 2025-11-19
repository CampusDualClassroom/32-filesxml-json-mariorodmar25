package com.campusdual.classroom;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLFileCreator {
    public static void main(String[] args) {
        createDocument();
    }

    public static void createDocument(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document;

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();
            Element root = document.createElement("shoopinglist");
            Element componets = document.createElement("items");
            document.appendChild(root);
            root.appendChild(componets);
            componets.appendChild(createItem(document, "2", "Manzana"));
            componets.appendChild(createItem(document, "1", "Leche"));
            componets.appendChild(createItem(document, "3", "Pan"));
            componets.appendChild(createItem(document, "2", "Huevo"));
            componets.appendChild(createItem(document, "1", "Queso"));
            componets.appendChild(createItem(document, "1", "Cereal"));
            componets.appendChild(createItem(document, "4", "Agua"));
            componets.appendChild(createItem(document, "6", "Yogur"));
            componets.appendChild(createItem(document, "2", "Arroz"));
            File dir = new File("src/main/resources");
            if(!dir.exists()){
                dir.mkdir();
            }
            writeToFile(document, "src/main/resources/shooppingList.xml");
        } catch (Exception e){

        }

    }

    public static Element createItem(Document document, String quantity, String itemDesc){
        Element componete = document.createElement("item");
        componete.setAttribute("quantity", quantity);
        componete.setTextContent(itemDesc);
        return componete;
    }

    public static void writeToFile(Document document, String filname) throws TransformerException {
        TransformerFactory factory  = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,  "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
        File archivo = new File(filname);
        DOMSource source = new DOMSource(document);
        StreamResult result= new StreamResult(archivo);
        transformer.transform(source, result);
    }


}

