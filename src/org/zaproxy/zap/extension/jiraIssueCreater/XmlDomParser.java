package org.zaproxy.zap.extension.jiraIssueCreater; /**
 * Created by kasun on 10/28/15.
 */


import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XmlDomParser {


    String createIssueData,summary,type,priority;
    String description="";
    String[] issueList = new String[1000];


    public String[] parseXmlDoc(String projectKey, String filepath,String assignee){
        try {
            File inputFile = new File(filepath);
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :"
                    + doc.getDocumentElement().getNodeName());


            NodeList alertList = doc.getElementsByTagName("alertitem"); //alert items
            NodeList instances;



            for (int temp = 0; temp < alertList.getLength(); temp++) { //loop through alerts
                Node nNode = alertList.item(temp);
                Element alert=(Element) nNode;
                instances=alert.getElementsByTagName("instance");

//                System.out.println("instances :" +instances.getLength());
//                System.out.println("Description :" +alert.getElementsByTagName("desc").item(0).getTextContent());
//                System.out.println("alert :" +alert.getElementsByTagName("alert").item(0).getTextContent());
//                System.out.println("Solution :" +alert.getElementsByTagName("solution").item(0).getTextContent());
//                System.out.println("reference :" +alert.getElementsByTagName("reference").item(0).getTextContent());
//                System.out.println("No of Instances :" +alert.getElementsByTagName("count").item(0).getTextContent());
//                System.out.println("Risk type :" +alert.getElementsByTagName("riskdesc").item(0).getTextContent().
//                        substring(0, alert.getElementsByTagName("riskdesc").item(0).getTextContent().indexOf(" ")));


                summary= StringEscapeUtils.escapeHtml(alert.getElementsByTagName("alert").item(0).getTextContent());
                description+= StringEscapeUtils.escapeJava(alert.getElementsByTagName("desc").item(0).getTextContent() + "\n");
                description+= StringEscapeUtils.escapeJava("| No of Instances | " + alert.getElementsByTagName("count").item(0).getTextContent() + " | \n");
                description+= StringEscapeUtils.escapeJava("| Solution | " + alert.getElementsByTagName("solution").item(0).getTextContent() + " | \n");
                description+= StringEscapeUtils.escapeJava("| Reference | " + alert.getElementsByTagName("reference").item(0).getTextContent() + " | \n");

                priority= StringEscapeUtils.escapeHtml(alert.getElementsByTagName("riskdesc").item(0).getTextContent().
                        substring(0, alert.getElementsByTagName("riskdesc").item(0).getTextContent().indexOf(" ")));

                type="Bug"; //issue type set to BUG






                for (int i = 0; i < instances.getLength(); i++) { //loop through instances
//                    System.out.println("\nCurrent Element :"
//                            + nNode.getNodeName());
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        description+= StringEscapeUtils.escapeHtml("| URL | " + eElement.getElementsByTagName("uri").item(i).getTextContent() + " | \\n");
                    }

                }

                createIssueData = "{\"fields\": {\"project\": {\"key\":\"" + projectKey + "\"}," +
                        "\"summary\":" + "\"" + summary + "\"" + ",  \"assignee\": {\"name\": \""+assignee+"\"}," +
                        "\"description\":" + "\"" + description + "\"" + "," +
                        "\"issuetype\":{\"name\":\"" + type + "\"},\"priority\":{\"name\":\"" + priority + "\"}}}";



                issueList[temp]=createIssueData;

                description="";
                issueList[999] = Integer.toString(alertList.getLength());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return issueList;
        }
    }
}