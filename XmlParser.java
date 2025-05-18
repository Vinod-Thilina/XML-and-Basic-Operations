package xmlparser;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XmlParser {
    public static void main(String[] args) {
        try {
            // Load the XML file
            File xmlFile = new File("C:\\Users\\VINOD\\Documents\\NetBeansProjects\\XmlParser\\build\\classes\\xmlparser\\books.xml");
            if (!xmlFile.exists()) {
                System.out.println("books.xml file not found!");
                return;
            }

            // Create DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            // Get list of all <book> elements
            NodeList nodeList = document.getElementsByTagName("book");

            // Modify the <year> of the first <book>
            if (nodeList.getLength() > 0) {
                Element firstBook = (Element) nodeList.item(0);
                firstBook.getElementsByTagName("year").item(0).setTextContent("2023");
                System.out.println("First book's year updated to 2023.");
            }

            // Save the modified XML to a new file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("C:\\Users\\VINOD\\Documents\\NetBeansProjects\\XmlParser\\build\\classes\\xmlparser\\updated_books.xml"));
            transformer.transform(source, result);

            System.out.println("Updated XML saved to updated_books.xml.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
