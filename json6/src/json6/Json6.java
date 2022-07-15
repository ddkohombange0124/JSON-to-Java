/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package json6;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author Kohombange DD
 */
public class Json6 {
    public static void parseObject(JSONObject json, String key) throws JSONException//print value
    {
    //System.out.println(json.has(key));
    System.out.println(json.get(key));
    }
    public static void getKey(JSONObject json, String key) throws JSONException//getkey excepting json obj
    {
        boolean exists=json.has(key);//if json has key
        Iterator<?>keys;
        String nextKeys;
        if(!exists){//nested obj
            keys=json.keys();
            while(keys.hasNext()){//for iteration values
                nextKeys=(String)keys.next();//storing values
                try//look if that key in json array or obj
                {
                    if(json.get(nextKeys) instanceof JSONObject){
                         if(exists == false){
                           getKey(json.getJSONObject(nextKeys), key);  
                         }
                    }
                    else if(json.get(nextKeys) instanceof JSONArray)
                    {
                        JSONArray jsonarray=json.getJSONArray(nextKeys);
                        for(int i=0;i<jsonarray.length();i++){
                            String jsonarrayString =jsonarray.get(i).toString();
                            JSONObject innerJSON = new JSONObject(jsonarrayString);
                            
                            if(exists == false){
                            getKey(innerJSON,key); 
                         }
                        }
                    }
                    
                }catch(Exception e)
                        {
                            
                        }
            }
        }else
        {
            parseObject(json,key);
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JSONException {
        // TODO code application logic here
        String inputjson="{\n" +
"  \"boolean\": true,\n" +
"  \"color\": \"gold\",\n" +
"  \"null\": null,\n" +
"  \"number\": 123,\n" +
"  \"object\": {\n" +
"    \"context\": {\n" +
"      \"country\": \"US\"\n" +
"    }\n" +
"  }\n" +
"}";
        
        JSONObject inputjobject=new JSONObject(inputjson);//convert str into json
        getKey(inputjobject, "context");
    }
    
}
