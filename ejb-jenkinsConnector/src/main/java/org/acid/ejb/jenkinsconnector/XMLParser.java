package org.acid.ejb.jenkinsconnector;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.acid.ejb.logger.Logger;
import org.acid.ejb.jenkinsconnector.data.Project;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLParser {

    private final Logger logger;

    private DocumentBuilder b;
    private Document page;

    public XMLParser(InputStream pageContent, Logger l) {
        this.logger = l;
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            b = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            logger.error("xml parse error", "failed to create the dom parser");
        }
        try {
            page = b.parse(pageContent);
        } catch (SAXException | IOException ex) {
            logger.error("xml parse error", "failed to parse the page content", ex);
        }
    }

    public XMLParser(String pageContent, Logger l) {
        this.logger = l;
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            b = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            logger.error("xml parse error", "failed to create the dom parser");
        }
        try {
            page = b.parse(new InputSource(new StringReader(pageContent)));
        } catch (SAXException | IOException ex) {
            logger.error("xml parse error", "failed to parse the page content", ex);
        }
    }

    public List<Project> fetchProjectList() {
        List<Project> ret = new ArrayList<>();
        if (page != null) {
            NodeList jobs = page.getElementsByTagName("job");
            if (jobs != null) {
                for (int i = 0; i < jobs.getLength(); i++) {
                    ret.add(new Project(jobs.item(i)));
                }
            }
        }
        return ret;
    }

}
