package juheAPI;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetCityList {
	 /**
     * ���û�ȡ�����б�ӿ�,������������
     * @return ���ؽӿ�����
     */
	 public static String excute(){
	        String url="http://v.juhe.cn/weather/citys?key=***a7558b2e0bedaa19673f74a6809ce";//�ӿ�URL
	        
	        return PureNetUtil.get(url);
	    }
	    /**
	     * ���ýӿڷ������ݺ�,��������,��������������õ���ӦID
	     * @param cityName ��������
	     * @return ���ض�ӦID
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
		 System.out.println(getIDBycityName("����"));

	}

}
