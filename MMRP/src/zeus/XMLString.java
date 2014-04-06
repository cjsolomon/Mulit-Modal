package zeus;

import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.jar.Attributes;

/**
 * Implmentation of an XML parsing algorithm.
 * <p>Title: XMLString</p>
 * <p>Description: Implementation of an XML parsing algorithm. Use with caution,
 * as it is not entirely tested yet. If you wish to backup a Zeus component, use
 * the clone() function instead.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Sam R. Thangiah
 * @version 2.0
 */
public class XMLString {
  private boolean isRoot;
  private String tag;
  private XMLString sibling;
  private XMLString child;
  private Attributes attributes;

  /**
   * Creates a new XMLString with this tag
   * @param name the name of the tag
   * @param isRoot if it is the root
   */
  public XMLString(String name, boolean isRoot) {
    tag = name;
    sibling = null;
    child = null;
    attributes = new Attributes();
  }

  /**
   * Creates a new XMLString class by parsing the XML string, will also create
   * any children or siblings that the XML provides
   * @param xml an XML string
   */
  public XMLString(String xml) {
    //allocate members
    sibling = null;
    child = null;
    attributes = new Attributes();

    //parse the XML string
    String headerTag = xml.substring(0, xml.indexOf(">"));

    //check to see if this tag has any attributes
    if (headerTag.indexOf("=") == -1) {
      //no attributes, so skip the < and get the rest for the tag
      tag = headerTag.substring(1).trim();
    }
    else {
      //there are attributes
      StringTokenizer st = new StringTokenizer(headerTag);
      tag = st.nextToken().substring(1);

      //add all the attributes
      while (st.hasMoreTokens()) {
        String att = st.nextToken();
        attributes.putValue(att.substring(0, att.indexOf("=")), att);
      }
    }

    //create the children, if any
    String insideTag = xml.substring(xml.indexOf(">") + 1,
                                     xml.indexOf("</" + tag + ">"));

    if (!insideTag.equals("")) {
      child = new XMLString(insideTag);
    }

    //create the sibling, if exists
    String outsideTag = xml.substring(xml.indexOf("</" + tag + ">") +
                                      ("</" + tag + ">").length());

    if (!outsideTag.equals("")) {
      sibling = new XMLString(outsideTag);
    }
  }

  /**
   * Inserts a sibling
   * @param sib sibling
   */
  public void addSibling(XMLString sib) {
    if (sibling == null) {
      sibling = sib;
    }
    else {
      sibling.addSibling(sib);
    }
  }

  /**
   * Inserts a child into this object
   * @param ch the child
   */
  public void addChild(XMLString ch) {
    if (child == null) {
      child = ch;
    }
    else {
      child.addSibling(ch);
    }
  }

  /**
   * Adds an attribute to this object that can be retrieved by using its name
   * later.
   * @param name name of attribute
   * @param value the attribute's value
   */
  public void insertAttribute(String name, String value) {
    attributes.putValue(name, name + "=\"" + value + "\"");
  }

  /**
   * Will return the string value of an attribute
   * @param name the name of the attribute to find
   * @return string of value, null if not found
   */
  public String getAttribute(String name) {
    String value = attributes.getValue(name);

    if (value.equals("")) {
      return "";
    }

    return value.substring(value.indexOf("=") + 2, value.length() - 1).trim();
  }

  public XMLString getChild() {
    return child;
  }

  public XMLString getSibling() {
    return sibling;
  }

  public String getTag() {
    return tag;
  }

  /**
   * Will create an XML string from this class
   * @return XML string
   */
  public String toString() {
    String xml = "";

    //start the tag header
    xml += ("<" + tag);

    //add all the attributes for this tag
    Iterator iter = attributes.values().iterator();

    while (iter.hasNext()) {
      String value = (String) iter.next();
      xml += (" " + value);
    }

    //close the opening tag
    xml += ">";

    //add all the children
    if (child != null) {
      xml += child.toString();
    }

    //close this tag
    xml += ("</" + tag + ">");

    //add the siblings
    if (sibling != null) {
      xml += sibling.toString();
    }

    return xml;
  }

  /**
   * Unit test for this class
   * @param args command line arguments (none)
   */
  public static void main(String[] args) {
    XMLString xmlString = new XMLString("zeus", true);
    xmlString.insertAttribute("hello", "fetus");
    xmlString.insertAttribute("goober", "booger");

    XMLString ch2 = new XMLString("child1", false);
    ch2.insertAttribute("hello", "fetus");

    XMLString ch1 = new XMLString("child2", false);
    ch1.insertAttribute("hello", "fetus");

    ch2.addChild(ch1);
    xmlString.addSibling(ch2);

    String xml = xmlString.toString();
    System.out.println(xml);

    XMLString root = new XMLString(xml);
    System.out.println(root.toString());
    System.out.println(root.getAttribute("goober"));
  }
}
