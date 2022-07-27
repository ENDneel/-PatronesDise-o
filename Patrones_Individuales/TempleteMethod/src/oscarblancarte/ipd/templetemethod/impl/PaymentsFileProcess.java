package oscarblancarte.ipd.templetemethod.impl;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import oscarblancarte.ipd.templetemethod.util.OnMemoryDataBase;

public class PaymentsFileProcess extends AbstractFileProcessTemplete {

    private Document doc;

    public PaymentsFileProcess(File file, String logPath, String movePath) {
        super(file, logPath, movePath);
    }

    @Override
    protected void validateName() throws Exception {
        String fileName = file.getName();
        if (!fileName.endsWith(".xml")) {
            throw new Exception("Invalid file name, must end with .xml ");
        }

        if (fileName.length() != 8) {
            throw new Exception("Invalid document format " + fileName.length());
        }
    }

    @Override
    protected void processFile() throws Exception {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(file);
            NodeList payments = doc.getElementsByTagName("payment");

            for (int i = 0; i < payments.getLength(); i++) {
                Node payment = payments.item(i);
                if (payment.getNodeType() == Node.ELEMENT_NODE) {
                    Element paymentElement = (Element) payment;
                    String id = paymentElement.getElementsByTagName("id").item(0).getTextContent();
                    String customer = paymentElement.getElementsByTagName("customer").item(0).getTextContent();
                    double amount = Double
                            .parseDouble(paymentElement.getElementsByTagName("amount").item(0).getTextContent());
                    String date = paymentElement.getElementsByTagName("date").item(0).getTextContent();
                    String card = paymentElement.getElementsByTagName("card").item(0).getTextContent();
                    String nombre = paymentElement.getElementsByTagName("nombre").item(0).getTextContent();
                    String apellido = paymentElement.getElementsByTagName("apellido").item(0).getTextContent();

                    boolean exist = OnMemoryDataBase.customerExist(Integer.parseInt(customer));
                    String result = "";
                    if (!exist) {
                        result = id +"N "+nombre + "\t" + " A " +apellido+ "\t" + " E " + customer + "\t" + date + " Customer not exist";

                    } else if (amount > 200) {

                        result = id +" N"+nombre + "\t" + " A" +apellido+ "\t"+ " E "+customer + "\t" + date + " The amount exceeds the maximum";
                    } else {

                        result =id +"N"+nombre + "\t" + "A" +apellido+ "\t" + " E" + customer + "\t" + date + " \t" + card + " Successfully payment";
                    }
                    Element resultAction = doc.createElement("result");
                    resultAction.appendChild(doc.createTextNode(result));
                    payment.appendChild(resultAction);
                }

            }
        } finally

        {
            try {

            } catch (Exception e) {
            }
        }
    }

    @Override
    protected void createLog() throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            File outFile = new File(logPath + "/" + file.getName());
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(outFile);
            transformer.transform(domSource, streamResult);
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
