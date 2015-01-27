package org.ejb.jenkinsconnector;

import javax.ejb.EJB;
import org.acid.ejb.logger.Logger;
import org.xml.sax.Locator;

public class XMLParser {

    private Locator locator;

    @EJB(mappedName = "logger")
    Logger logger;

}
