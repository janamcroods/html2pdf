package com.example.htmlpdf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Component
public class Converter {

    private static String siteUrl="http://localhost:8080";

    public static String convertToHtml(HttpServletRequest req, String path) {
        String htmlMsg = "";

        StringBuffer url = req.getRequestURL();
        String uri = req.getRequestURI();
        String ctx = req.getContextPath();
        String base = url.substring(0, url.length() - uri.length() + ctx.length());


        try {
            URL XYZ = new URL(base);
            URL yahoo = new URL(XYZ + path);
            //URI contextUrl = URI.create(req.getRequestURL().toString()).resolve(req.getContextPath());
            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));


            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                
                htmlMsg = htmlMsg + inputLine;
            }

            in.close();

            System.out.println("*******  in convertHtml method********** ");


        } catch (Exception e) {
            System.out.println("************eeeeeee:" + e.getMessage());
        }


        return htmlMsg;


    }

    public static String convertToHtmlnew(String path) {
        String htmlMsg = "";
        String base = siteUrl; //"http://localhost:8080";
        System.out.println("******* string base sata:**********" + base);
        try {
            URL XYZ = new URL(base);
            URL yahoo = new URL(XYZ + path);
            //URI contextUrl = URI.create(req.getRequestURL().toString()).resolve(req.getContextPath());
            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));


            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                htmlMsg = htmlMsg + inputLine;
            }

            in.close();

            System.out.println("*******  in convertHtml method********** ");


        } catch (Exception e) {
            System.out.println("************eeeeeee:" + e.getMessage());
        }


        return htmlMsg;


    }



}
