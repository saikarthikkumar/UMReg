package com.sci.testamigo.usermanagement.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.sci.testamigo.usermanagement.model.WebElementDetails;

public class JWebElement {
  // ==========================================================================

  public static WebElement getWebElementById(String id) {
    return PageCommonWD.getOpenBrowserHelp().getDriver().findElement(By.id(id));
  }

  // ==========================================================================

  public static WebElement getWebElementByName(String name) {
    return PageCommonWD.getOpenBrowserHelp().getDriver().findElement(By.name(name));
  }

  // ==========================================================================

  public static WebElement getWebElementByClassName(String className) {
    return PageCommonWD.getOpenBrowserHelp().getDriver().findElement(By.className(className));
  }

  // ==========================================================================

  public static WebElement getWebElementByCSSSelector(String cssSelector) {
    return PageCommonWD.getOpenBrowserHelp().getDriver().findElement(By.cssSelector(cssSelector));
  }

  // ==========================================================================

  public static WebElement getWebElementByXPath(String xpathExpression) {
    return PageCommonWD.getOpenBrowserHelp().getDriver().findElement(By.xpath(xpathExpression));
  }

  // ==========================================================================

  public static WebElement getWebElementByLinkText(String linkText) {
    return PageCommonWD.getOpenBrowserHelp().getDriver().findElement(By.linkText(linkText));
  }
  // ==========================================================================

  public static List<WebElement> getWebElementsById(String id) {
    return PageCommonWD.getOpenBrowserHelp().getDriver().findElements(By.id(id));
  }

  // ==========================================================================

  public static List<WebElement> getWebElementsByName(String name) {
    return PageCommonWD.getOpenBrowserHelp().getDriver().findElements(By.name(name));
  }

  // ==========================================================================

  public static List<WebElement> getWebElementsByClassName(String className) {
    return PageCommonWD.getOpenBrowserHelp().getDriver().findElements(By.className(className));
  }

  // ==========================================================================

  public static List<WebElement> getWebElementsByCSSSelector(String cssSelector) {
    return PageCommonWD.getOpenBrowserHelp().getDriver().findElements(By.cssSelector(cssSelector));
  }

  // ==========================================================================

  public static List<WebElement> getWebElementsByXPath(String xpathExpression) {
    return PageCommonWD.getOpenBrowserHelp().getDriver().findElements(By.xpath(xpathExpression));
  }

  // ==========================================================================

  public static List<WebElement> getWebElementsByLinkText(String linkText) {
    return PageCommonWD.getOpenBrowserHelp().getDriver().findElements(By.linkText(linkText));
  }

  // ==========================================================================

  public static WebElement getWebElement(WebElementDetails webElementDetails) {
    WebElement webElement = null;

    if (webElementDetails != null) {
      if (StringUtil.isNotNull(webElementDetails.getId())) {
        webElement = getWebElementById(webElementDetails.getId());
      } else if (StringUtil.isNotNull(webElementDetails.getName())) {
        webElement = getWebElementByName(webElementDetails.getName());
      } else if (StringUtil.isNotNull(webElementDetails.getClassName())) {
        webElement = getWebElementByClassName(webElementDetails.getClassName());
      } else if (StringUtil.isNotNull(webElementDetails.getCssSelector())) {
        webElement = getWebElementByCSSSelector(webElementDetails.getCssSelector());
      } else if (StringUtil.isNotNull(webElementDetails.getxPath())) {
        webElement = getWebElementByXPath(webElementDetails.getxPath());
      } else if (StringUtil.isNotNull(webElementDetails.getLinkText())) {
        webElement = getWebElementByLinkText(webElementDetails.getLinkText());
      }
    }
    return webElement;
  }

  // ==========================================================================
  public static List<WebElement> getWebElements(WebElementDetails webElementDetails) {
    List<WebElement> webElement = null;

    if (webElementDetails != null) {
      if (StringUtil.isNotNull(webElementDetails.getId())) {
        webElement = getWebElementsById(webElementDetails.getId());
      } else if (StringUtil.isNotNull(webElementDetails.getName())) {
        webElement = getWebElementsByName(webElementDetails.getName());
      } else if (StringUtil.isNotNull(webElementDetails.getClassName())) {
        webElement = getWebElementsByClassName(webElementDetails.getClassName());
      } else if (StringUtil.isNotNull(webElementDetails.getCssSelector())) {
        webElement = getWebElementsByCSSSelector(webElementDetails.getCssSelector());
      } else if (StringUtil.isNotNull(webElementDetails.getxPath())) {
        webElement = getWebElementsByXPath(webElementDetails.getxPath());
      } else if (StringUtil.isNotNull(webElementDetails.getLinkText())) {
        webElement = getWebElementsByLinkText(webElementDetails.getLinkText());
      }
    }
    return webElement;
  }
}