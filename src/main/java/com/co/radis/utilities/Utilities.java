/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.co.radis.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author wilso
 */
public class Utilities {

    public static Properties loadProperties() throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("config.properties"));
        return prop;
    }

    public static String getInformation(Properties props, String idNumber, String formatDate, String formatCountry) throws IOException {
        StringBuilder dto = new StringBuilder();

        try ( CloseableHttpClient httpclient = createAcceptSelfSignedCertificateClient()) {
            HttpPost httppost = new HttpPost(props.getProperty(Constant.URL_INFORMATION));
            // Request parameters and other properties.
            // Request parameters and other properties.
            List<NameValuePair> params = new ArrayList<>(2);
            params.add(new BasicNameValuePair("clave", idNumber));
            params.add(new BasicNameValuePair("fecareg", formatDate));
            params.add(new BasicNameValuePair("tipo_toma", formatCountry));
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            httppost.setHeader("Cookie", props.getProperty(Constant.URL_COOKIE));

            //Execute and get the response.
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            dto.append(responseString);
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException | IOException e) {
            throw new RuntimeException(e);
        }
        return dto.toString();
    }

    public static String sendInformation(Properties props, String dcBeneficiario, String idProgramacion, String dateSystem, String tipoCons) throws IOException {
        StringBuilder dto = new StringBuilder();

        try ( CloseableHttpClient httpclient = createAcceptSelfSignedCertificateClient()) {
            HttpPost httppost = new HttpPost(props.getProperty(Constant.SEND_INFORMATION));
            // Request parameters and other properties.
            // Request parameters and other properties.
            List<NameValuePair> params = new ArrayList<>(2);
            params.add(new BasicNameValuePair("datos[dc_beneficiario]", dcBeneficiario));
            params.add(new BasicNameValuePair("datos[id_programacion]", idProgramacion));
            params.add(new BasicNameValuePair("tipo_dia", props.getProperty(Constant.TIPO_HAB)));
            params.add(new BasicNameValuePair("fecareg", dateSystem));
            params.add(new BasicNameValuePair("tipo_toma", tipoCons));
            params.add(new BasicNameValuePair("id_solicitud_sel", props.getProperty(Constant.TIPO_ID_SOLICITUD)));
            params.add(new BasicNameValuePair("biometria", props.getProperty(Constant.BIOMETRY)));
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            httppost.setHeader("Cookie", props.getProperty(Constant.URL_COOKIE));

            //Execute and get the response.
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            dto.append(responseString);
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException | IOException e) {
            throw new RuntimeException(e);
        }
        return dto.toString();
    }

    private static CloseableHttpClient createAcceptSelfSignedCertificateClient()
            throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

        // use the TrustSelfSignedStrategy to allow Self Signed Certificates
        SSLContext sslContext = SSLContextBuilder
                .create()
                .loadTrustMaterial(new TrustSelfSignedStrategy())
                .build();

        // we can optionally disable hostname verification. 
        // if you don't want to further weaken the security, you don't have to include this.
        HostnameVerifier allowAllHosts = new NoopHostnameVerifier();

        // create an SSL Socket Factory to use the SSLContext with the trust self signed certificate strategy
        // and allow all hosts verifier.
        SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext, allowAllHosts);

        // finally create the HttpClient using HttpClient factory methods and assign the ssl socket factory
        return HttpClients
                .custom()
                .setSSLSocketFactory(connectionFactory)
                .build();
    }

    public static String getFormat(Date myDate, String format) {
        return new SimpleDateFormat(format).format(myDate);
    }

    public static String getFormatCountry(String format) {
        final Date currentTime = new Date();
        final SimpleDateFormat sdf
                = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss z");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-0500"));
        return sdf.format(currentTime);
    }
}
