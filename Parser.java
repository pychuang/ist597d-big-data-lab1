
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;


public class Parser {
	public static void main(String[] args) throws Exception {
		BufferedReader br1 = new BufferedReader(new FileReader("./tweets"));
		BufferedWriter bw1 = new BufferedWriter(new FileWriter("./SourceTweet1.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("./ParsedTweet1.txt"));

		//JsonArray jsonArray = JsonArray.readFrom(bw1);
		boolean flag=false;
		bw.write("x\ty\tretweet_status\ttime\tretweet_count\tid\ttext");
		bw1.write("x\ty\ttime\tretweet_count\tid\ttext");
		bw.newLine();
		bw1.newLine();
		while(br1.ready()){
			bw.flush();
			bw1.flush();

			try{	
			String s1=br1.readLine();
//			Charset.forName("UTF-8").encode(s1);
			if(s1.length()>5){
			String content=s1.substring(19);
			//System.out.println(content);
			JsonObject obj=JsonObject.readFrom(content);
			List <String> l=obj.names();
			int retweeted=0;
			int id=0;
			int retweet_status=0;
			int retweet_count=0;
			int text=0;
			int created_at=0;
			int coordinates=0;
			int lang=0;

			
			String str_id="";
			String str_retweet_status="";
			String str_retweet_count="";
			String str_text="";
			String str_created_at="";
			String str_coordinates="";
			String str_lang="";
			
			for(int i=0;i<l.size();i++){
				if(l.get(i).equalsIgnoreCase("id"))
					id=i;
				if(l.get(i).equalsIgnoreCase("retweeted_status"))
					retweet_status=i;
				if(l.get(i).equalsIgnoreCase("created_at"))
					created_at=i;
				if(l.get(i).equalsIgnoreCase("text"))
					text=i;
				if(l.get(i).equalsIgnoreCase("retweet_count"))
					retweet_count=i;
				if(l.get(i).equalsIgnoreCase("coordinates"))
					coordinates=i;
				if(l.get(i).equalsIgnoreCase("lang"))
					lang=i;
			}
			String source="";
			for(int i=0;i<l.size();i++){
				if(i==retweet_status){
					
					if(obj.get(l.get(i)).toString().length()>5)
					{
						source=obj.get(l.get(i)).toString();
						str_retweet_status="TRUE";
					}else{
						str_retweet_status="NULL";;
					}
				}
				if(i==retweet_count)
					str_retweet_count=obj.get(l.get(i)).toString();
				if(i==coordinates)
					str_coordinates=obj.get(l.get(i)).toString();
				if(i==id){
					str_id=obj.get(l.get(i)).toString();
				}
				if(i==text){					
					str_text=obj.get(l.get(i)).toString();
				}
				if(i==created_at)
					str_created_at=obj.get(l.get(i)).toString();
				if(i==created_at)
					str_created_at=obj.get(l.get(i)).toString();
				if(i==lang)
					str_lang=obj.get(l.get(i)).toString();
			}
			String a="";
			if(str_lang.equalsIgnoreCase("\"en\"")){
			if(str_coordinates.length()>10){
				a=str_coordinates.substring(str_coordinates.indexOf("[")+1, str_coordinates.indexOf("]"));
				bw.write(a.split(",")[0]+"\t"+a.split(",")[1]+"\t"+str_retweet_status+"\t"+str_created_at+"\t"+str_retweet_count+"\t"+str_id+"\t"+str_text);
				bw.newLine();
			}else
			{
				bw.write("0"+"\t"+"0"+"\t"+str_retweet_status+"\t"+str_created_at+"\t"+str_retweet_count+"\t"+str_id+"\t"+str_text);
				bw.newLine();
			}
			}
			if(source.length()>50&&source.startsWith("{"))
			{
				JsonObject obj1=JsonObject.readFrom(source);
				List <String> l1=obj1.names();
				retweeted=-1;
				id=-1;
				retweet_status=0;
				retweet_count=-1;
				text=-1;
				created_at=-1;
				coordinates=-1;
				
				str_id="";
				str_retweet_status="";
				str_retweet_count="";
				str_text="";
				str_created_at="";
				str_coordinates="";
				
				for(int i=0;i<l1.size();i++){
					if(l1.get(i).equalsIgnoreCase("id"))
						id=i;
					if(l1.get(i).equalsIgnoreCase("created_at"))
						created_at=i;					
					if(l1.get(i).equalsIgnoreCase("retweeted_status"))
						retweet_status=i;
					if(l1.get(i).equalsIgnoreCase("text"))
						text=i;
					if(l1.get(i).equalsIgnoreCase("retweet_count"))
						retweet_count=i;
					if(l1.get(i).equalsIgnoreCase("coordinates"))
						coordinates=i;
					if(l1.get(i).equalsIgnoreCase("lang"))
						lang=i;
				}
				if(id>0){
					for(int i=0;i<l1.size();i++){
						if(i==retweet_status){
							str_retweet_status="NULL";;
						}
						if(i==retweet_count)
							str_retweet_count=obj1.get(l1.get(i)).toString();
						if(i==id){
							str_id=obj1.get(l1.get(i)).toString();
						}
						if(i==text)
							str_text=obj1.get(l1.get(i)).toString();
						if(i==created_at)
							str_created_at=obj1.get(l1.get(i)).toString();
						if(i==coordinates)
							str_coordinates=obj.get(l1.get(i)).toString();
						if(i==lang)
							str_lang=obj.get(l1.get(i)).toString();
					}
					a="";
					if(str_lang.equalsIgnoreCase("\"en\"")){

					if(str_coordinates.length()>10){
						a=str_coordinates.substring(str_coordinates.indexOf("[")+1, str_coordinates.indexOf("]"));
						bw1.write(a.split(",")[0]+"\t"+a.split(",")[1]+"\t"+str_created_at+"\t"+str_retweet_count+"\t"+str_id+"\t"+str_text);
						bw1.newLine();
					}else
					{
						bw1.write("0"+"\t"+"0"+"\t"+str_created_at+"\t"+str_retweet_count+"\t"+str_id+"\t"+str_text);
						bw1.newLine();
					}
					}
					
				}
				}
			}
			}catch(Exception e){
				throw e; 
			}
			
		}
		bw.close();
		bw1.close();
	}
		

}
