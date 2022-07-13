package com.nikitakozhemyako.shema_etaja1.repository;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler {
    public void warning(SAXParseException e)  {
        System.out.println(e.getMessage());
        System.out.println("---------------------------------------------------");
        System.out.println("Pavel Olegovich, i did it");
        System.out.println("---------------------------------------------------");

    }

    public void error(SAXParseException e)  {
        System.out.println(e.getMessage());
        System.out.println("---------------------------------------------------");
        System.out.println("Pavel Olegovich, i did it");
        System.out.println("---------------------------------------------------");



    }

    public void fatalError(SAXParseException e) {
        System.out.println(e.getMessage());
        System.out.println("---------------------------------------------------");
        System.out.println("Pavel Olegovich, i did it");
        System.out.println("---------------------------------------------------");

    }
}