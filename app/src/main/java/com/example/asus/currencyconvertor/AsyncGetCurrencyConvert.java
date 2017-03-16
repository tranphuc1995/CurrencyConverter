package com.example.asus.currencyconvertor;

import android.os.AsyncTask;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by ASUS on 3/15/2017.
 */

public class AsyncGetCurrencyConvert extends AsyncTask<Void, Void, String> {

    private String fromCurrent;
    private String toCurrent;
    private TextView tvResult;

    @Override
    protected String doInBackground(Void... params) {
        String result = "";
        try {
            result = downloadUrl(getUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        Document document = getDomElement(result);
        String change = document.getDocumentElement().getChildNodes().item(0).getNodeValue();
        tvResult.setText("1 "+fromCurrent +" = " + change + " " + toCurrent);
    }

    public AsyncGetCurrencyConvert(String fromCurrent, String toCurrent, TextView tvResult) {
        this.fromCurrent = fromCurrent;
        this.toCurrent = toCurrent;
        this.tvResult = tvResult;
    }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(getUrl());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
            br.close();
        } catch (Exception e) {
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }

        return data;


    }

    private String getUrl() {
        String url = "http://www.webservicex.net/CurrencyConvertor.asmx/ConversionRate?FromCurrency="
                + fromCurrent
                + "&ToCurrency="
                + toCurrent;
        return url;
    }

    public Document getDomElement(String xml) {
        Document document = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();

            is.setCharacterStream(new StringReader(xml));
            document = db.parse(is);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

}
