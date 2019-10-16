package juheAPI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Demo1 {
	/**
	 * get方法直接调用post方法
	 * @param url
	 * @return
	 */
	
	public static String get(String url){
			return post(url,null);
		}
	/**
	 * 设定post方法获取网络资源，如果参数为null，实际上设定为get方法
	 */
	@SuppressWarnings("rawtypes")
	public static <K,V>String post (String url,Map<K, V> param){
		HttpURLConnection conn=null;
		try{
			URL U=new URL(url);
			conn=(HttpURLConnection) U.openConnection();
			StringBuffer sb=null;
			if(param!=null){
				sb=new StringBuffer();
				conn.setDoInput(true);
				conn.setRequestMethod("POST");
				OutputStream out=conn.getOutputStream();
				BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(out));
				for(Map.Entry s:param.entrySet()){
					sb.append(s.getKey()).append("=").append(s.getValue()).append("&");
					}
				writer.write(sb.deleteCharAt(sb.toString().length()-1).toString());
				writer.close();
				sb=null;
			}
			conn.connect();
			sb=new StringBuffer();
			int recode=conn.getResponseCode();
			BufferedReader reader=null;
			if(recode==200){
				InputStream in=conn.getInputStream();
				reader=new BufferedReader(new InputStreamReader(in));
				String str=null;
				sb=new StringBuffer();
				while((str=reader.readLine())!=null){
					sb.append(str).append(System.getProperty("line.separator"));
					
				}
				reader.close();
				if(sb.toString().length()==0){
					return null;
				}
				return sb.toString().substring(0,sb.toString().length()-System.getProperty("line.separator").length());
			}
			}
			catch(Exception e){
				e.printStackTrace();
				return null;
			}
			finally{
				if(conn!=null)
					conn.disconnect();
			}
			return null;
		
		
	}

}
