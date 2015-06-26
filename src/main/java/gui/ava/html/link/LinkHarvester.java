package gui.ava.html.link;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JEditorPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.html.HTML.Attribute;
import javax.swing.text.html.HTML.Tag;

import com.itextpdf.tool.xml.html.HTML;

public class LinkHarvester
{
  private final JTextComponent textComponent;
  private final List<LinkInfo> links = new ArrayList();

  public LinkHarvester(JEditorPane textComponent) {
    this.textComponent = textComponent;
    harvestElement(textComponent.getDocument().getDefaultRootElement());
  }

  public List<LinkInfo> getLinks() {
    return this.links;
  }

  private void harvestElement(Element element) {
    if (element == null) {
      return;
    }

    AttributeSet attributes = element.getAttributes();
    Enumeration attributeNames = attributes.getAttributeNames();
    while (attributeNames.hasMoreElements()) {
      Object key = attributeNames.nextElement();
      if (HTML.Tag.A.equals(key)) {
        Map linkAttributes = harvestAttributes(element);
        List bounds = harvestBounds(element, null);
        if ((!linkAttributes.isEmpty()) && (!bounds.isEmpty())) {
          this.links.add(new LinkInfo(linkAttributes, bounds));
        }
      }
    }

    for (int i = 0; i < element.getElementCount(); i++) {
      Element child = element.getElement(i);
      harvestElement(child);
    }
  }

  private Map<String, String> harvestAttributes(Element element) {
    Object value = element.getAttributes().getAttribute(HTML.Tag.A);
    if ((value instanceof SimpleAttributeSet)) {
      SimpleAttributeSet attributeSet = (SimpleAttributeSet)value;
      Map result = new HashMap();
      addAttribute(attributeSet, result, HTML.Attribute.HREF);
      //addAttribute(attributeSet, result, HTML.Attribute.TARGET);
      //addAttribute(attributeSet, result, HTML.Attribute.TITLE);
      addAttribute(attributeSet, result, HTML.Attribute.CLASS);
      addAttribute(attributeSet, result, "tabindex");
      addAttribute(attributeSet, result, "dir");
      addAttribute(attributeSet, result, "lang");
      addAttribute(attributeSet, result, "accesskey");

      addAttribute(attributeSet, result, "onblur");
      addAttribute(attributeSet, result, "onclick");
      addAttribute(attributeSet, result, "ondblclick");
      addAttribute(attributeSet, result, "onfocus");
      addAttribute(attributeSet, result, "onmousedown");
      addAttribute(attributeSet, result, "onmousemove");
      addAttribute(attributeSet, result, "onmouseout");
      addAttribute(attributeSet, result, "onmouseover");
      addAttribute(attributeSet, result, "onmouseup");
      addAttribute(attributeSet, result, "onkeydown");
      addAttribute(attributeSet, result, "onkeypress");
      addAttribute(attributeSet, result, "onkeyup");
      return result;
    }

    return Collections.emptyMap();
  }

  private void addAttribute(SimpleAttributeSet attributeSet, Map<String, String> result, Object attribute) {
    String attName = attribute.toString();
    String attValue = (String)attributeSet.getAttribute(attribute);
    if ((attValue != null) && (!attValue.equals("")))
      result.put(attName, attValue);
  }

  private List<Rectangle> harvestBounds(Element element, Throwable e)
  {
    List boundsList = new ArrayList();
    try {
      int startOffset = element.getStartOffset();
      int endOffset = element.getEndOffset();

      Rectangle lastBounds = null;
      for (int i = startOffset; i <= endOffset; i++) {
        Rectangle bounds = this.textComponent.modelToView(i);

        if (bounds == null)
        {
          continue;
        }

        if (lastBounds == null) {
          lastBounds = bounds;
        }
        else if (bounds.getY() == lastBounds.getY()) {
          lastBounds = lastBounds.union(bounds);
        }
        else
        {
          if ((lastBounds.getWidth() > 1.0D) && (lastBounds.getHeight() > 1.0D)) {
            boundsList.add(lastBounds);
          }
          lastBounds = null;
        }
      }

      if ((lastBounds != null) && (lastBounds.getWidth() > 1.0D) && (lastBounds.getHeight() > 1.0D)) {
        boundsList.add(lastBounds);
      }
      return boundsList;
    } catch (BadLocationException e1) {
    }
    throw new RuntimeException("Got BadLocationException", e);
  }
}