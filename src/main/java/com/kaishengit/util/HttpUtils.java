package com.kaishengit.util;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vance on 16/6/14.
 */
public class HttpUtils {

    private static final Logger LOG = LoggerFactory.getLogger(HttpUtils.class);
    /**
     * 建立连接超时设置
     */
    private final static int CONN_TIMEOUT = 10 * 1000;
    /**
     * 默认读取数据超时设置
     */
    private final static int DEAFULT_SO_TIMEOUT = 15 * 1000;

    public static String getData(String url, Map<String, String> params, int timeOut) {
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(CONN_TIMEOUT);
        String resultUrl = assembleUrl(url, params);
        GetMethod getMethod = new GetMethod(resultUrl);
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 1000 * timeOut);
        getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
        getMethod.setRequestHeader("Connection", "close");
        //请求重试处理，使用默认的，重试3次
//        getMethod.getParams()
//            .setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        InputStream inputStream = null;
        BufferedReader br = null;
        try {
            int statusCode = client.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                LOG.info("excute get method for url {} failure with HTTP status code {} ",
                    resultUrl, getMethod.getStatusLine());
                //return String.valueOf(getMethod.getStatusLine());
                return null;
            }
            inputStream = getMethod.getResponseBodyAsStream();
            br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            StringBuffer stringBuffer = new StringBuffer();
            String str = "";
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
                stringBuffer.append("\n");
            }
            return stringBuffer.toString().trim();
        } catch (IOException e) {
            LOG.error("occoured IOException when excute HTTP get method No retry, url : {}", url, e);
        } finally {
            try {
                if(inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(br != null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            getMethod.releaseConnection();
        }
        return null;
    }


    public static String getData(String url, int timeOut) {
        return getData(url, null, timeOut);
    }

    public static String getData(String url) {
        return getData(url, null, DEAFULT_SO_TIMEOUT);
    }

    public static String getData(String url, Map<String, String> params) {
        return getData(url, params, DEAFULT_SO_TIMEOUT);
    }

    public static String postData(String url, Map<String, String> params, int timeOut) {
        Pattern p = Pattern
            .compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher matcher = p.matcher(url);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("illegal argument with url " + url);
        }

        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(CONN_TIMEOUT);
        PostMethod postMethod = new PostMethod(url);
        postMethod.setRequestHeader("Connection", "close");
        postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        if (params != null && params.size() > 0) {
            List<NameValuePair> parametersBody = new ArrayList<NameValuePair>();
            Iterator<String> iterator = params.keySet().iterator();
            try {
                while (iterator.hasNext()) {
                    String param = iterator.next();
                    String value = params.get(param);
                    if (StringUtils.isBlank(value))
                        continue;
                    // if (checkContainChinese(value)) {
                    //    parametersBody.add(new NameValuePair(param, URLEncoder.encode(value, "UTF-8")));
                    // } else {
                    parametersBody.add(new NameValuePair(param, value));
                    //}
                }
            } catch (Exception e) {
                LOG.error("unsupported encoding exception", e);
            }

            postMethod
                .setRequestBody(parametersBody.toArray(new NameValuePair[parametersBody.size()]));
        }
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 2000 * timeOut);
        //请求重试处理，使用默认的，重试3次
        postMethod.getParams()
            .setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        int statusCode = 0;
        InputStream inputStream = null;
        BufferedReader br = null;
        try {
            statusCode = client.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                LOG.info("excute post method for url {} failure with HTTP status code {} ", url,
                    postMethod.getStatusLine());
              //  return postMethod.getStatusLine() + "";
                return null;
            }
            //String result = postMethod.getResponseBodyAsString();
            inputStream = postMethod.getResponseBodyAsStream();
            br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            StringBuffer stringBuffer = new StringBuffer();
            String str = StringUtils.EMPTY;
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
                stringBuffer.append("\n");
            }
            return stringBuffer.toString().trim();
        } catch (IOException e) {
            LOG.error("occoured IOException when excute HTTP post method, url : {} , params : {}",
                url, params, e);
        } finally {
            try {
                if(inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(br != null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            postMethod.releaseConnection();
        }
        return null;
    }

    public static String postData(String url, int timeOut) {
        return postData(url, null, timeOut);
    }

    public static String postData(String url) {
        return postData(url, null, DEAFULT_SO_TIMEOUT);
    }

    public static String postDataAsBody(String url, String jsonData, int timeOut) {
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(CONN_TIMEOUT);
        PostMethod postMethod = new PostMethod(url);
        postMethod.setRequestHeader("Connection", "close");
        postMethod
            .setRequestHeader("Content-Type", "application/json;charset=utf-8");
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 1000 * timeOut);
        //请求重试处理，使用默认的，重试3次
        postMethod.getParams()
            .setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
        try {
            postMethod.setRequestEntity(new ByteArrayRequestEntity(jsonData.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            LOG.error("setRequestEntity error", e);
        }
        int statusCode;
        try {
            statusCode = client.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                LOG.info("excute post method for url {} failure with HTTP status code {} ",
                    new Object[] {url, postMethod.getStatusLine()});
             //   return postMethod.getStatusLine() + "";
                return null;
            }
            InputStream inputStream = postMethod.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            StringBuffer stringBuffer = new StringBuffer();
            String str = StringUtils.EMPTY;
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
                stringBuffer.append("\n");
            }
            return stringBuffer.toString().trim();
        } catch (IOException e) {
            LOG.error("occoured i/o exception when excute HTTP post method, url : {}", url, e);
        } finally {
            postMethod.releaseConnection();
        }
        return null;
    }

    public static String postDataAsBody(String url, String jsonData){
        return postDataAsBody(url, jsonData, DEAFULT_SO_TIMEOUT);
    }

    private static String assembleUrl(String url, Map<String, String> params) {
        if (params == null)
            return url;
        Pattern p = Pattern
            .compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher matcher = p.matcher(url);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("illegal argument with url " + url);
        }
        StringBuilder sb = new StringBuilder(url);
        if (!url.contains("?")) {
            sb.append("?");
        } else {
            if (!url.endsWith("?")) {
                sb.append("&");
            }
        }
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String param = iterator.next();
            String value = params.get(param);
            sb.append(param);
            sb.append("=");
            //针对中文参数编码
            if (checkContainChinese(value)) {
                try {
                    sb.append(URLEncoder.encode(value, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    LOG.error("encoding error", e);
                }
            } else {
                sb.append(value);
            }
            if (iterator.hasNext())
                sb.append("&");
        }
        return sb.toString();
    }

    private static boolean checkContainChinese(String s) {
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }


    public static void main(String[] args) {
        String token = "t_2FKqxydvalpfN_2bY8wyf_2bIGO7tfV0C7_2FRcJzRyTj_2FEifJ_2Fzn1UvB60xhKNoGPNmkacBY5PVdtxm9_2bfcuPMi7EZlicZRk_3d";
        int auditoriumNum = 20;
        int auditoriumType = 1;
        String auditoriumName = "杜比厅";
        int auditoriumSeat = 45;
        Map<String, String> param = new HashMap();
        param.put("token", token);
        param.put("auditorium_num", String.valueOf(auditoriumNum));
        param.put("auditorium_type", String.valueOf(auditoriumType));
        param.put("auditorium_name", String.valueOf(auditoriumName));
        param.put("auditorium_seat", String.valueOf(auditoriumSeat));
        String res = HttpUtils.postData("http://ad.test.cinemasmedia.cn/api/tms-auditorium_create.do", 5000);
        System.out.println(res);
    }

}
