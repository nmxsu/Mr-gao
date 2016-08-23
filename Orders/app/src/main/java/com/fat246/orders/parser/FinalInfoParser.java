package com.fat246.orders.parser;

import android.util.Xml;

import com.fat246.orders.bean.FinalStandInfo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by hx on 2016/8/23.
 */
public class FinalInfoParser {

    //URL str
    private String URL_Str;

    //OrderId
    private String OrderId;

    public FinalInfoParser(String URL_Str, String OrderId) {

        this.URL_Str = URL_Str;
        this.OrderId = OrderId;
    }

    public FinalStandInfo getFinalInfo() {

        //保存中网页服务上面加载下来的  xml数据
        FinalStandInfo finalStandInfo = sendGetAllFinalListPost("OrderId=" + OrderId);

        return finalStandInfo;
    }

    //发送  post 请求
    private FinalStandInfo sendGetAllFinalListPost(String param) {

        PrintWriter out = null;
        FinalStandInfo finalStandInfo;

        try {

            URL url = new URL(URL_Str);

            //打开和URL之间的链接
            URLConnection conn = url.openConnection();

            //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            // 非常重要的两行
            conn.setDoOutput(true);
            conn.setDoInput(true);

            //获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());

            //发送请求参数
            out.print(param);

            //flush
            out.flush();

            //定义InputStream 输入流来读取URL的响应
            InputStream is = conn.getInputStream();

            finalStandInfo = parse(is);

        } catch (Exception e) {

            e.printStackTrace();
            finalStandInfo = null;
        }

        //System.out.println("-----------------------------------");
        //添加一点 车市数据
        finalStandInfo = new FinalStandInfo("1213", "1213", "1213", "1213", "1213", "1213");

        //System.out.println("+++++++++++++++++++++++++-----------");
        return finalStandInfo;
    }

    //解析  xml数据
    private FinalStandInfo parse(InputStream is) throws XmlPullParserException, IOException {

        FinalStandInfo finalStandInfo = null;

        try {

            XmlPullParser parser = Xml.newPullParser();

            parser.setInput(is, "utf-8");

            //首先跳出 ArrayOfString

            int eventType = parser.getEventType();
            int i = 0;

            //引用
            String fCode = null, fName = null, fOrderq = null, fReceiveq = null;
            String fStorageq = null, fReturnq = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {

                switch (eventType) {

                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:

                        if (parser.getName().equals("string")) {

                            eventType = parser.next();
                            String str = parser.getText();
                            switch (i % 6) {

                                case 0:
                                    fCode = str;

                                    if (fCode == null) fCode = "";
                                    break;
                                case 1:
                                    fName = str;

                                    if (fName == null) fName = "";
                                    break;
                                case 2:
                                    fOrderq = str;

                                    if (fOrderq == null) fOrderq = "";
                                    break;

                                case 3:

                                    fReceiveq = str;

                                    if (fReceiveq == null) fReceiveq = "";
                                    break;

                                case 4:

                                    fStorageq = str;

                                    if (fStorageq == null) fStorageq = "";
                                    break;
                                case 5:

                                    fReturnq = str;

                                    if (fReturnq == null) fReturnq = "";

                                    default:
                                    finalStandInfo = new FinalStandInfo(fCode, fName, fOrderq,
                                            fReceiveq, fStorageq, fReturnq);
                                    break;
                            }

                            //别忘了  ++
                            i++;
                        }
                        break;
                }
                eventType = parser.next();
            }

        } finally {

            is.close();
        }
        return finalStandInfo;
    }
}
