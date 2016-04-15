package com.cn.hnust.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class NetWorkHelper {

	public NetWorkHelper() {
		// TODO Auto-generated constructor stub
	}
	
	public String getMessage(String ur) {

        try {
            URL url = new URL(ur);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            InputStream inStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            //System.out.println("result=" + buffer.toString());
            conn.disconnect();
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
	
	
	 public void postMessage(String sender, String content, String ur) {
	        // String path = "http://192.168.1.106:8009/messageApi";

	        try {
	            Map<String, String> requestParams = new HashMap<String, String>();
	            requestParams.put("sender", "12");
	            //requestParams.put("content", "中国");
	            StringBuilder params = new StringBuilder();
	            for (Map.Entry<String, String> entry : requestParams.entrySet()) {
	                params.append(entry.getKey());
	                params.append("=");
	                params.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
	                params.append("&");
	            }
	            if (params.length() > 0)
	                params.deleteCharAt(params.length() - 1);
	            byte[] data = params.toString().getBytes();
	            URL url = new URL(ur);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setConnectTimeout(5000);
	            conn.setRequestMethod("POST");
	            conn.setRequestProperty("Content-Type",
	                    "application/x-www-form-urlencoded");
	            conn.setRequestProperty("Content-Length",
	                    String.valueOf(data.length));
	            // POST方式，其实就是浏览器把数据写给服务器
	            conn.setDoOutput(true); // 设置可输出流
	            // OutputStream os = conn.getOutputStream(); // 获取输出流
	            DataOutputStream outStream = new DataOutputStream(
	                    conn.getOutputStream());
	            outStream.write(data); // 将数据写给服务器
	            int code = conn.getResponseCode();
	            if (code == 200) {
	                InputStream is = conn.getInputStream();
	                // return StreamTools.streamToString(is);
	            } else {
	                // return "网络访问失败";
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            // return 网络访问失败;
	        }
	    }
	
	
	 public String getURLResponse(String urlString) {
	        HttpURLConnection conn = null; //连接对象
	        InputStream is = null;
	        String resultData = "";
	        try {
	            URL url = new URL(urlString); //URL对象
	            conn = (HttpURLConnection) url.openConnection(); //使用URL打开一个链接
	            conn.setDoInput(true); //允许输入流，即允许下载

	            //在android中必须将此项设置为false
	            conn.setDoOutput(false); //允许输出流，即允许上传
	            conn.setUseCaches(false); //不使用缓冲
	            conn.setRequestMethod("GET"); //使用get请求
	            is = conn.getInputStream();   //获取输入流，此时才真正建立链接
	            InputStreamReader isr = new InputStreamReader(is);
	            BufferedReader bufferReader = new BufferedReader(isr);
	            String inputLine = "";
	            while ((inputLine = bufferReader.readLine()) != null) {
	                resultData += inputLine + "\n";
	            }
	            System.out.println(resultData);

	        } catch (MalformedURLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("MalformedURLException:" + e.getMessage());
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("IOException:" + e.getMessage());
	        } finally {
	            if (is != null) {
	                try {
	                    is.close();
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	            if (conn != null) {
	                conn.disconnect();
	            }
	        }

	        return resultData;
	    }
	
	
	 /**
     * 下载文件
     * @param hsUrl
     * @return
     */
    public String DownLoadHttpsFile(String hsUrl,String fileName,String ur) {
        URL url;
        InputStream is = null;
        String filePath = ur+fileName;
        try {
            url = new URL(hsUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            TrustManager[] tm = {xtm};

            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, tm, null);

            con.setSSLSocketFactory(ctx.getSocketFactory());
            con.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });


            con.setDoInput(true); //允许输入流，即允许下载

            //在android中必须将此项设置为false
            con.setDoOutput(false); //允许输出流，即允许上传
            con.setUseCaches(false); //不使用缓冲
            con.setRequestMethod("GET"); //使用get请求
            is = con.getInputStream();   //获取输入流，此时才真正建立链接

            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while( (len=is.read(buffer)) != -1 ){
                outStream.write(buffer, 0, len);
            }
            is.close();
            byte[] fileBytes = outStream.toByteArray();

            File file = new File(filePath);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(fileBytes);
            fops.flush();
            fops.close();
           /* Certificate[] certs = con.getServerCertificates();
            for (Certificate cert : certs) {
                X509Certificate xcert = (X509Certificate) cert;
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }
    
    
    /**
     * HTTPS协议的POST请求
     * @param hsUrl  请求地址
     * @param json   请求数据
     * @return
     */
    public String postHttpsResponse(String hsUrl,String json) {
        URL url;
        InputStream is = null;
        String resultData = "";
        try {
            url = new URL(hsUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            TrustManager[] tm = {xtm};

            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, tm, null);

            con.setSSLSocketFactory(ctx.getSocketFactory());
            con.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });


            con.setDoInput(true); //允许输入流，即允许下载

            //在android中必须将此项设置为false
            con.setDoOutput(true); //允许输出流，即允许上传
            con.setUseCaches(false); //不使用缓冲
            con.setRequestMethod("POST"); //使用post请求

            //表单数据
            if (null != json) {
                OutputStream outputStream = con.getOutputStream();
                outputStream.write(json.getBytes("UTF-8"));
                outputStream.close();
            }


            is = con.getInputStream();   //获取输入流，此时才真正建立链接
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine = "";
            while ((inputLine = bufferReader.readLine()) != null) {
                resultData += inputLine + "\n";
            }
            System.out.println(resultData);
            Certificate[] certs = con.getServerCertificates();

            for (Certificate cert : certs) {
                X509Certificate xcert = (X509Certificate) cert;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }
	
	

	public String getHttpsResponse(String hsUrl, String requestMethod) {
		URL url;
		InputStream is = null;
		String resultData = "";
		try {
			url = new URL(hsUrl);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			TrustManager[] tm = { xtm };
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, tm, null);
			con.setSSLSocketFactory(ctx.getSocketFactory());
			con.setHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}

			});
			con.setDoInput(true); // 允许输入流，即允许下载
			// 在android中必须将此项设置为false
			con.setDoOutput(false); // 允许输出流，即允许上传
			con.setUseCaches(false); // 不使用缓冲
			if (null != requestMethod && !requestMethod.equals("")) {
				con.setRequestMethod(requestMethod); // 使用指定的方式
			} else {
				con.setRequestMethod("GET"); // 使用get请求
			}
			is = con.getInputStream(); // 获取输入流，此时才真正建立链接
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bufferReader = new BufferedReader(isr);
			String inputLine = "";
			while ((inputLine = bufferReader.readLine()) != null) {
				resultData += inputLine + "\n";
			}
			Certificate[] certs = con.getServerCertificates();
			int certNum = 1;
			for (Certificate cert : certs) {
				X509Certificate xcert = (X509Certificate) cert;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultData;
	}

	private X509TrustManager xtm = new X509TrustManager() {

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			// TODO Auto-generated method stub

		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			// TODO Auto-generated method stub

		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			// TODO Auto-generated method stub
			return null;
		}

	};

}
