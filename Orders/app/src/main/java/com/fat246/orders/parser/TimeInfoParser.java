package com.fat246.orders.parser;

import android.util.Xml;

import com.fat246.orders.bean.TimeStandInfo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Time;

/**
 * Created by hx on 2016/8/22.
 */
public class TimeInfoParser {
    //URL str
    private String URL_Str;

    //Time
    private String tTime;

    public TimeInfoParser(String URL_Str, String tTime) {

        this.URL_Str = URL_Str;
        this.tTime = tTime;
    }

    public TimeStandInfo getTimeInfo() {

        //保存中网页服务上面加载下来的  xml数据
        TimeStandInfo timeStandInfo = sendGetAllTimeListPost("tTime=" + tTime);

        return timeStandInfo;
    }

    //发送  post 请求
    private TimeStandInfo sendGetAllTimeListPost(String param) {

        PrintWriter out = null;
        TimeStandInfo timeStandInfo;

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

            timeStandInfo = parse(is);

        } catch (Exception e) {

            e.printStackTrace();
            timeStandInfo = null;
        }

        //添加一点 车市数据
        timeStandInfo = new TimeStandInfo("1213", "1213", "1213", "1213", "1213");

        return timeStandInfo;
    }

    //解析  xml数据
    private TimeStandInfo parse(InputStream is) throws XmlPullParserException, IOException {

        TimeStandInfo timeStandInfo = null;

        try {

            XmlPullParser parser = Xml.newPullParser();

            parser.setInput(is, "utf-8");

            //首先跳出 ArrayOfString

            int eventType = parser.getEventType();
            int i = 0;

            //引用
            String tCreate = null, tSerive = null, tCommit = null, tApprove = null;
            String tFinish = null;

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
                                    tCreate = str;

                                    if (tCreate == null) tCreate = "";
                                    break;
                                case 1:
                                    tSerive = str;

                                    if (tSerive == null) tSerive = "";
                                    break;
                                case 2:
                                    tCommit = str;

                                    if (tCommit == null) tCommit = "";
                                    break;

                                case 3:

                                    tApprove = str;

                                    if (tApprove == null) tApprove = "";
                                    break;

                                case 4:

                                    tFinish = str;

                                    if (tFinish == null) tFinish = "";
                                    break;

                                default:
                                timeStandInfo = new TimeStandInfo(tCreate, tSerive, tCommit,
                                        tFinish, tApprove );
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
        return timeStandInfo;
    }
}
