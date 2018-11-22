package com.yy.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

public class HttpClientUtil {
	
	private static Logger logger=Logger.getLogger(HttpClientUtil.class);
    private static String uri="http://suggest.taobao.com/sug?code=utf-8&q=裤子&callback=cb";
    private static String body="";
    
    private static CloseableHttpClient httpClient = null;
    private static CloseableHttpResponse response = null;
    private static String charSet = "UTF-8";
    
    public static String GenerateRequest(String url,String path,String meth,String map){
    	String uri=url+path;
    	String result="";
    	if(url.startsWith("http:")){
    		if(meth.toUpperCase().equals("POST")){
    			result=doHttpsPost(uri,map);
    		}
    		
    		
    	}
    	return result;
    }
    /**
     * https的post请求
     * @param url
     * @param jsonstr
     * @param charset
     * @return
     */
    public static String doHttpsPost(String url, String jsonStr) {
        try {
            httpClient =new SSLClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json");
            
            StringEntity se = new StringEntity(jsonStr);
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            httpPost.setEntity(se);
            
            response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    return EntityUtils.toString(resEntity, charSet);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
             if(httpClient != null){
                    try {
                        httpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(response != null){
                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
        return null;
    }
    /**
     * http的post请求(用于key-value格式的参数) 
     * @param url
     * @param param
     * @return
     */
    public static String doHttpPost(String url,Map<String,String> param){
        try {
            //请求发起客户端
            httpClient = HttpClients.createDefault();
            //参数集合
            List<NameValuePair> postParams = new ArrayList<NameValuePair>();
            //遍历参数并添加到集合
            for(Map.Entry<String, String> entry:param.entrySet()){
                postParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
//            for(String key:param.keySet()){
//            	postParams.add(new BasicNameValuePair(key,param.get(key)));
//            }
            
            //通过post方式访问
            HttpPost post = new HttpPost(url);
            HttpEntity paramEntity = new UrlEncodedFormEntity(postParams,charSet);
            post.setEntity(paramEntity);
            response = httpClient.execute(post);
            StatusLine status = response.getStatusLine();  
            int state = status.getStatusCode();  
            if (state == HttpStatus.SC_OK) {  
                HttpEntity valueEntity = response.getEntity();
                String content = EntityUtils.toString(valueEntity);
                //jsonObject = JSONObject.fromObject(content);
                return content;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    /**
     * http的Get请求(用于key-value格式的数据)
     * @param url
     * @param param
     * @return
     */
    public static String doHttpsGet(String url,Map<String,String> param) {
                
        try {
            httpClient = new SSLClient();
            if(param != null && !param.isEmpty()) {
                //参数集合
                List<NameValuePair> getParams = new ArrayList<NameValuePair>();
                for(Map.Entry<String, String> entry:param.entrySet()){
                    getParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                url +="?"+EntityUtils.toString(new UrlEncodedFormEntity(getParams), "UTF-8");
            }
            //发送gey请求
            HttpGet httpGet = new HttpGet(url);  
            response = httpClient.execute(httpGet);  
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                return EntityUtils.toString(response.getEntity());  
            } 
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            if(httpClient != null){
                try {
                	httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    //用HttpClient发送Post请求
	public static JSONObject httpClientPost(String url,String jsonParam)
	{
		JSONObject jsonResult=null;
		HttpClient httpClient=new DefaultHttpClient();
		HttpPost method=new HttpPost(url);
		logger.info("请求的url："+url);
		logger.info("q请求的method是POST");
		logger.info("请求的参数是："+jsonParam);
		try{
			if(jsonParam!="")
			{
				//解决中文乱码问题
				StringEntity entity=new StringEntity(jsonParam,"utf-8"); 
				entity.setContentEncoding("utf-8");
				entity.setContentType("application/json");
				method.setEntity(entity);
			}
			HttpResponse result=httpClient.execute(method);
			if(result.getStatusLine().getStatusCode()==200)
			{//json串转换为json对象
				jsonResult=JSONObject.parseObject(EntityUtils.toString(result.getEntity()));
				
			}
				
		}catch(Exception e)
		{
			logger.error("post请求提交失败："+e);
		}
		return jsonResult;
	}
	//用HttpClient发送get请求
	public static JSONObject httpClientGet(String url,String jsonParam)
	{
		JSONObject jsonResult=null;
		HttpClient httpClient;
		try {
			httpClient = new SSLClient();
		
		if(!jsonParam.isEmpty())
		{
			url=url+"?"+jsonParam;
		}
		HttpGet method=new HttpGet(url);
		logger.info("请求的url是："+url+"?"+jsonParam);
		logger.info("请求的method是GET");
		try {
			HttpResponse response=httpClient.execute(method);
			if(response.getStatusLine().getStatusCode()==200)
			{
				jsonResult=JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
							
			}
			else
			{
				logger.error("get请求提交失败"+url+"?"+jsonParam);
			}
		} catch (IOException e) {
			logger.error("get请求提交失败"+url+"?"+jsonParam+e);
		}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return jsonResult;
		
	}
	public static void main(String[] args)
	{
		Map map=new HashMap();
		map.put("name", "zhangsan");
		map.put("age", "18");
		
		String result=doHttpPost("http://localhost:8888/postdemo",map);
		System.out.println(result);
		//JSONObject json=httpClientPost(uri,body);
//		JSONObject json=httpClientGet(uri,body);
//		System.out.println(json.toString());
		
//		String source="{\"id\":4741,\"subLemmaId\":19077084,\"newLemmaId\":18757954,\"key\":\"hello\",\"desc\":\"阿黛尔·阿德金斯个人单曲\",\"title\":\"hello\",\"card\":[{\"key\":\"m31_nameC\",\"name\":\"中文名称\",\"value\":[\"你好\"],\"format\":[\"你好\"]},{\"key\":\"m31_nameE\",\"name\":\"外文名称\",\"value\":[\"Hello\"],\"format\":[\"Hello\"]},{\"key\":\"m31_ext_2\",\"name\":\"音乐销量\",\"value\":[\"1230万（2015年）\"],\"format\":[\"1230万（2015年）\"]}],\"redirect\":[]}";
//	
//		JSONObject object=JSONObject.parseObject(source);
//		String s=JSONPath.eval(object, "$.card[0].key").toString();
//		System.out.println(s);
		
	}
}
