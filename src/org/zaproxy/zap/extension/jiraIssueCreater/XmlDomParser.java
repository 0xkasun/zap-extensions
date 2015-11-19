package org.zaproxy.zap.extension.jiraIssueCreater;


/**
 * Created by kasun on 10/28/15.
 */


import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.parosproxy.paros.Constant;
import org.parosproxy.paros.control.Control;
import org.parosproxy.paros.extension.Extension;
import org.parosproxy.paros.extension.ExtensionLoader;
import org.parosproxy.paros.extension.report.ReportGenerator;
import org.parosproxy.paros.model.Model;
import org.parosproxy.paros.model.SiteMap;
import org.parosproxy.paros.model.SiteNode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.zaproxy.zap.extension.XmlReporterExtension;
import org.zaproxy.zap.utils.XMLStringUtil;
import org.zaproxy.zap.view.ScanPanel;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class XmlDomParser{


    String createIssueData,summary,type,priority;
    String description="";
    String[] issueList = new String[1000];
    private Logger log = Logger.getLogger(this.getClass());


    public String[] parseXmlDoc(String projectKey, String assignee ) throws SessionNotFoundException{  //parse the xml document or file
        try {

            StringBuilder currentSession=new StringBuilder();
            String report=this.generate(currentSession);
            InputStream stream = new ByteArrayInputStream(report.getBytes(StandardCharsets.UTF_8));
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stream);
            doc.getDocumentElement().normalize();

            NodeList session=doc.getElementsByTagName("alerts");

            if(session.getLength()!=0) {


                NodeList alertList = doc.getElementsByTagName("alertitem"); //alert items
                NodeList instances;


                for (int temp = 0; temp < alertList.getLength(); temp++) { //loop through alerts
                    Node nNode = alertList.item(temp);
                    Element alert = (Element) nNode;
                    instances = alert.getElementsByTagName("instance");


                    summary = StringEscapeUtils.escapeHtml(alert.getElementsByTagName("alert").item(0).getTextContent());
                    description += StringEscapeUtils.escapeJava(alert.getElementsByTagName("desc").item(0).getTextContent() + "\n\n\n");
                    description += StringEscapeUtils.escapeJava("| No of Instances | " + alert.getElementsByTagName("count").item(0).getTextContent() + " | \n");
                    description += StringEscapeUtils.escapeJava("| Solution | " + alert.getElementsByTagName("solution").item(0).getTextContent() + " | \n");
                    description += StringEscapeUtils.escapeJava("| Reference | " + alert.getElementsByTagName("reference").item(0).getTextContent() + " | \n");

                    priority = StringEscapeUtils.escapeHtml(alert.getElementsByTagName("riskdesc").item(0).getTextContent().
                            substring(0, alert.getElementsByTagName("riskdesc").item(0).getTextContent().indexOf(" ")));

                    type = "Bug"; //issue type set to BUG


                    for (int i = 0; i < instances.getLength(); i++) { //loop through instances

                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            description += StringEscapeUtils.escapeHtml("| URL | " + eElement.getElementsByTagName("uri").item(i).getTextContent() + " | \\n");
                        }

                    }

                    createIssueData = "{\"fields\": {\"project\": {\"key\":\"" + projectKey + "\"}," +
                            "\"summary\":" + "\"" + summary + "\"" + ",  \"assignee\": {\"name\": \"" + assignee + "\"}," +
                            "\"description\":" + "\"" + description + "\"" + "," +
                            "\"issuetype\":{\"name\":\"" + type + "\"},\"priority\":{\"name\":\"" + priority + "\"}}}";


                    issueList[temp] = createIssueData;

                    description = "";
                    issueList[999] = Integer.toString(alertList.getLength()); //no of alerts are set to the last index of the array


                }
            }else{
                issueList[999] = "0"; //no of issues set to 0 when there are no alerts found
                throw(new SessionNotFoundException("Session not Found"));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return issueList;
    }


    // Methods copied and modified from package org.parosproxy.paros.extension.report; class ReportLastScan

    public String generate(StringBuilder report) throws Exception {
        report.append("<?xml version=\"1.0\"?>");
        report.append("<OWASPZAPReport version=\"").append(Constant.PROGRAM_VERSION).append("\" generated=\"").append(ReportGenerator.getCurrentDateTimeString()).append("\">\r\n");
        siteXML(report);
        report.append("</OWASPZAPReport>");
        return report.toString();
    }

    private void siteXML(StringBuilder report) {
        SiteMap siteMap = Model.getSingleton().getSession().getSiteTree();
        SiteNode root = (SiteNode) siteMap.getRoot();
        int siteNumber = root.getChildCount();
        for (int i = 0; i < siteNumber; i++) {
            SiteNode site = (SiteNode) root.getChildAt(i);
            String siteName = ScanPanel.cleanSiteName(site, true);
            String[] hostAndPort = siteName.split(":");
            boolean isSSL = (site.getNodeName().startsWith("https"));
            String siteStart = "<site name=\"" + XMLStringUtil.escapeControlChrs(site.getNodeName()) + "\"" +
                    " host=\"" + XMLStringUtil.escapeControlChrs(hostAndPort[0])+ "\""+
                    " port=\"" + XMLStringUtil.escapeControlChrs(hostAndPort[1])+ "\""+
                    " ssl=\"" + String.valueOf(isSSL) + "\"" +
                    ">";
            StringBuilder extensionsXML = getExtensionsXML(site);
            String siteEnd = "</site>";
            report.append(siteStart);
            report.append(extensionsXML);
            report.append(siteEnd);
        }
    }

    public StringBuilder getExtensionsXML(SiteNode site) {
        StringBuilder extensionXml = new StringBuilder();
        ExtensionLoader loader = Control.getSingleton().getExtensionLoader();
        int extensionCount = loader.getExtensionCount();
        for(int i=0; i<extensionCount; i++) {
            Extension extension = loader.getExtension(i);
            if(extension instanceof XmlReporterExtension) {
                extensionXml.append(((XmlReporterExtension)extension).getXml(site));
            }
        }
        return extensionXml;
    }

}