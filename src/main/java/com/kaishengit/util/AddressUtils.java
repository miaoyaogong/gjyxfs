package com.kaishengit.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * 根据IP地址获取详细的地域信息
 * @author Lwl
 * @dateJan 26, 2016
*/
public class AddressUtils {
    /**
     *
     * @param content
     * 请求的参数 格式为：name=xxx&pwd=xxx
     * @param encodingString
     * 服务器端请求编码。如GBK,UTF-8等
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getAddresses(String content, String encodingString)
    throws UnsupportedEncodingException {
        // 这里调用pconline的接口
        String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
        // 从http://whois.pconline.com.cn取得IP所在的省市区信息
        String returnStr = HttpUtils.getData(urlStr);
        if (returnStr != null) {
            // 处理返回的省市区信息
            System.out.println(returnStr);
            String[] temp = returnStr.split(",");
            if(temp.length<3){
                return "0";//无效IP，局域网测试
                }
            String region = (temp[5].split(":"))[1].replaceAll("\"", "");
            region = decodeUnicode(region);// 省份
            
            String country = "";
            String area = "";
            // String region = "";
            String city = "";
            String county = "";
            String isp = "";
            for (int i = 0; i < temp.length; i++) {
                switch (i) {
                    case 1:
                        country = (temp[i].split(":"))[2].replaceAll("\"", "");
                        country = decodeUnicode(country);// 国家
                        break;
                    case 3:
                        area = (temp[i].split(":"))[1].replaceAll("\"", "");
                        area = decodeUnicode(area);// 地区
                        break;
                    case 5:
                        region = (temp[i].split(":"))[1].replaceAll("\"", "");
                        region = decodeUnicode(region);// 省份
                        break;
                    case 7:
                        city = (temp[i].split(":"))[1].replaceAll("\"", "");
                        city = decodeUnicode(city);// 市区
                        break;
                    case 9:
                        county = (temp[i].split(":"))[1].replaceAll("\"", "");
                        county = decodeUnicode(county);// 地区
                        break;
                    case 11:
                        isp = (temp[i].split(":"))[1].replaceAll("\"", "");
                        isp = decodeUnicode(isp); // ISP公司
                        break;
                    }
                }
            
            System.out.println(country+area+region+city+county+isp);
            return new StringBuffer(country).append(area).append(region).append(city).append(county).append(isp).toString();
            }
        return null;
        }

    /**
     * unicode 转换成 中文
     *
     * @author fanhui 2007-3-15
     * @param theString
     * @return
     */
            public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                                case '1':
                                case '2':
                                case '3':
                                case '4':
                                case '5':
                                case '6':
                                case '7':
                                case '8':
                                case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                                case 'b':
                                case 'c':
                                case 'd':
                                case 'e':
                                case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                                case 'B':
                                case 'C':
                                case 'D':
                                case 'E':
                                case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed encoding.");
                                }
                        }
                    outBuffer.append((char) value);
                    } else {
                    if (aChar == 't') {
                        aChar = '\t';
                        } else if (aChar == 'r') {
                        aChar = '\r';
                        } else if (aChar == 'n') {
                        aChar = '\n';
                        } else if (aChar == 'f') {
                        aChar = '\f';
                        }
                    outBuffer.append(aChar);
                    }
                } else {
                outBuffer.append(aChar);
                }
            }
        return outBuffer.toString();
        }

    public static String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }
    // 测试
            public static void main(String[] args) {
        AddressUtils addressUtils = new AddressUtils();
        // 测试ip 219.136.134.157 中国=华南=广东省=广州市=越秀区=电信
        String ip = "125.70.11.136";
        String address = "";
        try {
            address = addressUtils.getAddresses("ip="+ip, "utf-8");
            } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }
        System.out.println(address);
        // 输出结果为：广东省,广州市,越秀区
        }
    
            
}