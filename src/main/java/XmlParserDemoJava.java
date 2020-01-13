import org.w3c.dom.*;
import org.xml.sax.*;

import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.*;

public class XmlParserDemoJava {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

        try {
            // Loading the file to the application
            File inputFile = new File("src/main/java/xml-exemplo.xml");

            // create a document builder
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // create a document from a file or stream
            Document doc = builder.parse(inputFile);

            // build Xpath
            XPath xPath = XPathFactory.newInstance().newXPath();

            // Prepare Path expression and evaluate it
            String expression = "/pessoas/pessoa"; // monta a expressão baseada na estrutura do XML

            // Monta uma lista de nós, passando a expressão, e usa o <.evaluate> para determinar o objeto a ser aplicada a expressão e o tipo de retorno
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            // Iterate over NodeList
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node nNode = nodeList.item(i);
//                System.out.printf("\nCurrent Element: " + "||" + nNode.getNodeName() + "||");

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
//                    System.out.printf("\nNome: " + eElement.getElementsByTagName("nome").item(0).getTextContent());
//                    System.out.printf("\nIdade: " + eElement.getElementsByTagName("idade").item(0).getTextContent());
//                    System.out.printf("\nOcupação: " + eElement.getElementsByTagName("ocupacao").item(0).getTextContent());
//                    System.out.printf("\nMagias: " + eElement.getElementsByTagName("magiaslist").item(0).getTextContent());
                }
            }

            Node nNode = nodeList.item(0);
            Element element = (Element) nNode;
            element.getElementsByTagName("nome").item(0).setTextContent("Triss");

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            DOMSource source = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
            String xml = writer.getBuffer().toString();

            FileOutputStream outputStream = new FileOutputStream("C:\\Users\\marcelo.martins\\Documents\\Java_codigos\\PROJETOS\\projeto-xpath\\xmls de saida\\saida.xml");
            byte out[] = xml.getBytes();
            outputStream.write(out);
            outputStream.close();



        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }




}
