package juheAPI;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetCityList {
	 /**
     * 调用获取城市列表接口,返回所有数据
     * @return 返回接口数据
     */
	 public static String excute(){
	        String url="http://v.juhe.cn/weather/citys?key=***a7558b2e0bedaa19673f74a6809ce";//接口URL
	        
	        return PureNetUtil.get(url);
	    }
	    /**
	     * 调用接口返回数据后,解析数据,根据输入城市名得到对应ID
	     * @param cityName 城市名称
	     * @return 返回对应ID
	     */
	    public static String getIDBycityName(String cityName) {
	        String result=excute();
	        if(result!=null){
	            JSONObject obj=JSONObject.fromObject(result);
	            result=obj.getString("resultcode");
	            if(result!=null&&result.equals("200")){
	                result=obj.getString("result");
	                JSONArray arr=JSONArray.fromObject(result);
	                for(Object o:arr){
	                    obj=JSONObject.fromObject(o.toString());
	                   
	                    result=obj.getString("district");
	                    
	                    if(result.equals(cityName)||result.contains(cityName)){
	                        result=obj.getString("id");
	                        return result;
	                    }
	                }
	            }
	        }
	        return result;
	    }
	public static void main(String[] args) {
		 System.out.println(getIDBycityName("荆州"));

	}

}
