package com.sci.testamigo.usermanagement.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.sci.testamigo.usermanagement.common.Constants;
import com.sci.testamigo.usermanagement.model.WebElementDetails;

public class JXMLParser {
  protected transient final Log log = LogFactory.getLog(getClass());
  private static JXMLParser jxmlManager = null;

  // ==========================================================================

  private JXMLParser() {
  }

  // ==========================================================================

  public static synchronized JXMLParser getInstance() {
    if (jxmlManager == null) {
      jxmlManager = new JXMLParser();
    }
    return jxmlManager;
  }

  // ==========================================================================

  public List<WebElementDetails> getWebElements(String pageName) {
    log.info("START of the method getWebElements");
    List<WebElementDetails> webElements = new ArrayList<WebElementDetails>();
    WebElementDetails webElementDetails = null;

    try {
      File xmlFile = new File(AppSetup.getXMLFilePath(pageName));
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(xmlFile);
      doc.getDocumentElement().normalize();
      NodeList nodeList = doc.getElementsByTagName(Constants.PAGE_ELEMENT);

      for (int i = 0; i < nodeList.getLength(); i++) {
        Node xmlNode = nodeList.item(i);

        if (xmlNode.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) xmlNode;
          webElementDetails = getWebElementDetails(element);

          if (Constants.CATEGORY_PARALLEL.equalsIgnoreCase(webElementDetails.getCategory())) {
            webElementDetails.setParallelSubElements(getParallelSubElements(element));
          } else if (Constants.CATEGORY_INNER.equalsIgnoreCase(webElementDetails.getCategory())) {
            webElementDetails.setInnerSubWebElementDetails(getInnerSubElementDetails(element));
          }
          webElements.add(webElementDetails);
        }
      }
    } catch (SAXParseException err) {
      log.error("** Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
      log.error(" " + err.getMessage());
    } catch (SAXException e) {
      Exception x = e.getException();
      ((x == null) ? e : x).printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    log.info("END of the method getWebElements");
    return webElements;
  }

  // ==========================================================================

  private List<WebElementDetails> getParallelSubElements(Element element) {
    List<WebElementDetails> parallelSubElements = null;
    NodeList childNodes = null;

    try {
      childNodes = element.getElementsByTagName(Constants.SUB_ELEMENT);

      if (childNodes != null && childNodes.getLength() > 0) {
        parallelSubElements = new ArrayList<WebElementDetails>(childNodes.getLength());

        for (int i = 0; i < childNodes.getLength(); i++) {
          Node xmlNode = childNodes.item(i);
          Element subElement = (Element) xmlNode;
          WebElementDetails webElementDetails = getWebElementDetails(subElement);
          parallelSubElements.add(webElementDetails);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return parallelSubElements;
  }

  // ==========================================================================

  private WebElementDetails getInnerSubElementDetails(Element element) {
    WebElementDetails innerSubElementDetails = null;
    Element subElement = null;
    boolean hasInnerNodes = true;
    int innerSubElementsCount = 0;
    Node parentNode = null;
    Element parentElement = null;

    try {
      subElement = element;

      while (hasInnerNodes) {
        NodeList innerNodes = subElement.getElementsByTagName(Constants.SUB_ELEMENT);

        if (innerNodes != null && innerNodes.getLength() > 0) {
          innerSubElementsCount++;
          Node innerNode = innerNodes.item(0);
          subElement = (Element) innerNode;
        } else {
          hasInnerNodes = false;
        }
      }
      innerSubElementDetails = getWebElementDetails(subElement);
      parentElement = subElement;

      for (int i = 0; i < (innerSubElementsCount - 1); i++) {
        parentNode = parentElement.getParentNode();
        parentElement = (Element) parentNode;
        WebElementDetails webElementDetails = getWebElementDetails(parentElement);
        webElementDetails.setInnerSubWebElementDetails(innerSubElementDetails);
        innerSubElementDetails = webElementDetails;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return innerSubElementDetails;
  }
  // ==========================================================================

  private WebElementDetails getWebElementDetails(Element element) {
    WebElementDetails webElementDetails = new WebElementDetails();
    String category;
    int no;
    String type;
    String id;
    String name;
    String className;
    String cssSelector;
    String xPath;
    String linkText;
    String data;

    try {
      category = element.getAttribute(Constants.CATEGORY);
      no = Integer.parseInt(element.getAttribute(Constants.NO));
      type = element.getElementsByTagName(Constants.TYPE).item(0).getTextContent();
      id = element.getElementsByTagName(Constants.ID).item(0).getTextContent();
      name = element.getElementsByTagName(Constants.NAME).item(0).getTextContent();
      className = element.getElementsByTagName(Constants.CLASS_NAME).item(0).getTextContent();
      cssSelector = element.getElementsByTagName(Constants.CSS_SELECTOR).item(0).getTextContent();
      xPath = element.getElementsByTagName(Constants.XPATH).item(0).getTextContent();
      linkText = element.getElementsByTagName(Constants.LINK_TEXT).item(0).getTextContent();
      data = element.getElementsByTagName(Constants.DATA).item(0).getTextContent();

      if (StringUtil.isNull(category)) {
        category = Constants.CATEGORY_NORMAL;
      }
      webElementDetails.setCategory(category);
      webElementDetails.setNo(no);
      webElementDetails.setType(type);
      webElementDetails.setId(id);
      webElementDetails.setName(name);
      webElementDetails.setClassName(className);
      webElementDetails.setCssSelector(cssSelector);
      webElementDetails.setxPath(xPath);
      webElementDetails.setLinkText(linkText);
      webElementDetails.setData(data);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return webElementDetails;
  }
  // ==========================================================================
}