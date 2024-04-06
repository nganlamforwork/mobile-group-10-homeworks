package com.example.myapplication;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class RSSParser {
    private static String TAG_CHANNEL = "channel";
    private static String TAG_TITLE = "title";
    private static String TAG_LINK = "link";
    private static String TAG_ITEM = "item";
    private static String TAG_DATE = "pubDate";

    public RSSParser() {

    }

    public List<RSSItem> getRSSFeedItems(String rss_url) {
        List<RSSItem> itemsList = new ArrayList<>();
        String rss_feed_xml = getXmlFromUrl(rss_url);
        if(rss_feed_xml != null) {
            try {
                Document doc = getDomElement(rss_feed_xml);
                NodeList nodeList = doc.getElementsByTagName(TAG_CHANNEL);
                Element e = (Element) nodeList.item(0);
                NodeList items = e.getElementsByTagName(TAG_ITEM);
                for(int i = 0; i < items.getLength(); i++){
                    Element e1 = (Element) items.item(i);
                    String title = this.getValue(e1, TAG_TITLE);
                    String link = this.getValue(e1, TAG_LINK);
                    String pubdate = this.getValue(e1, TAG_DATE);
                    RSSItem rssItem = new RSSItem(title, link, pubdate);
                    itemsList.add(rssItem);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return itemsList;
    }


    public String getXmlFromUrl(String url) {
        String xml = null;
        try {
            URL mUrl = new URL(url);
            HttpURLConnection httpConnection = (HttpURLConnection) mUrl.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Content-length", "0");
            httpConnection.setUseCaches(false);
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setConnectTimeout(100000);
            httpConnection.setReadTimeout(100000);
            httpConnection.connect();
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                xml = sb.toString();
            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }

    public Document getDomElement(String xml) {
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = db.parse(is);
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }
        catch (SAXException e) {
            e.printStackTrace();
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return doc;
    }

    public final String getElementValue(Node elem) {
        Node child;
        if(elem != null){
            if(elem.hasChildNodes()){
                for(child = elem.getFirstChild(); child != null; child = child.getNextSibling()){
                    if(child.getNodeType() == Node.TEXT_NODE || (child.getNodeType() == Node.CDATA_SECTION_NODE)){
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }

    public String getValue(Element item, String str){
        NodeList n = item.getElementsByTagName(str);
        return this.getElementValue(n.item(0));
    }
}