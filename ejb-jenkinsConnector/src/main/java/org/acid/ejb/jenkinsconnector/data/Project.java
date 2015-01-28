package org.acid.ejb.jenkinsconnector.data;

import java.io.Serializable;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Project implements Serializable {
    
    private static final long serialVersionUID = 1L;

    //Attributes
    private String projectName;
    private String projectUrl;
    private ProjectColor color;

    // Constructor
    public Project(Node item) {
        NodeList info = item.getChildNodes();
        for (int i = 0; i < info.getLength(); i++) {
            switch (info.item(i).getNodeName()) {
                case "name":
                    this.projectName = info.item(i).getTextContent();
                    break;
                case "url":
                    this.projectUrl = info.item(i).getTextContent();
                    break;
                case "color":
                    String content = info.item(i).getTextContent();
                    if (content != null && !content.isEmpty()) {
                        this.color = ProjectColor.valueOf(info.item(i).getTextContent().toUpperCase());
                    } else {
                        this.color = ProjectColor.UNKNOWN;
                    }
                    break;
            }
        }
    }
    //Getters / Setters

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public ProjectColor getColor() {
        return color;
    }

    public void setColor(ProjectColor color) {
        this.color = color;
    }

    // Override methods
    @Override
    public String toString() {
        String ret = "";
        ret += "Project Name : " + this.projectName + "\n";
        ret += "Project Url  : " + this.projectUrl + "\n";
        ret += "Color        : " + this.color.name();
        return ret;
    }

}
