import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

class ResultArray {
	int exp;
	int exp2;
	int draw;
	int normal;
	int size;
	public ResultArray(int exp, int exp2, int draw, int normal, int size) {
		super();
		this.exp = exp;
		this.exp2 = exp2;
		this.draw = draw;
		this.normal = normal;
		this.size = size;
	}
}

public class FindSporTotoAverages {

	public static void main(String[] args) throws Exception{
		int totalExp = 0;
		int totalExp2 = 0;
		int totalMatch = 0;
		int totalNormal = 0;
		int totalDraw = 0;
		
		ArrayList<ResultArray> resultArrays = new ArrayList<>();
		
		for(int i = 141; i < 154; i++) {
			Document doc = Jsoup.connect("https://www.nesine.com/sportoto/mac-sonuclari?pNo=" + i + "_1").get();
			JsonArray o = new JsonParser().parse(Utils.getRequest("http://localhost:8080/sporTotoWeek/" + i)).getAsJsonArray();
			int exp = 0;
			int exp2 = 0;
			int draw = 0;
			int normal = 0;
			for(JsonElement e : o) {
				int diff = e.getAsJsonObject().get("homeMatchScore").getAsInt() - e.getAsJsonObject().get("awayMatchScore").getAsInt();
				
				if(diff > 0) {
					
					if( e.getAsJsonObject().get("handicapPercentage2").getAsFloat() > e.getAsJsonObject().get("handicapPercentageX").getAsFloat() && 
							e.getAsJsonObject().get("handicapPercentage2").getAsFloat() > e.getAsJsonObject().get("handicapPercentage1").getAsFloat()) {
						exp2++;
					} else if( e.getAsJsonObject().get("handicapPercentage1").getAsFloat() > e.getAsJsonObject().get("handicapPercentageX").getAsFloat() && 
							e.getAsJsonObject().get("handicapPercentage1").getAsFloat() > e.getAsJsonObject().get("handicapPercentage2").getAsFloat()) {
						normal++;
					} else {
						exp++;
					}
					
					
				} else if(diff < 0) {
					
					if( e.getAsJsonObject().get("handicapPercentage1").getAsFloat() > e.getAsJsonObject().get("handicapPercentageX").getAsFloat() && 
							e.getAsJsonObject().get("handicapPercentage1").getAsFloat() > e.getAsJsonObject().get("handicapPercentage2").getAsFloat()) {
						exp2++;
					} else if( e.getAsJsonObject().get("handicapPercentage2").getAsFloat() > e.getAsJsonObject().get("handicapPercentageX").getAsFloat() && 
							e.getAsJsonObject().get("handicapPercentage2").getAsFloat() > e.getAsJsonObject().get("handicapPercentage1").getAsFloat()) {
						normal++;
					} else {
						exp++;
					}
				} else {
					draw++;
					if( e.getAsJsonObject().get("handicapPercentageX").getAsFloat() > e.getAsJsonObject().get("handicapPercentage1").getAsFloat() && 
							e.getAsJsonObject().get("handicapPercentageX").getAsFloat() > e.getAsJsonObject().get("handicapPercentage2").getAsFloat()) {
						normal++;
					} else {
						exp++;
					}
				}
			}
			System.out.println("Week: " + i);
			System.out.println("Normal:" + normal);
			System.out.println("Exp:" + exp);
			System.out.println("Exp2:" + exp2);
			System.out.println("Draw:" + draw);
			Elements table = doc.select("div.table-responsive");
			Elements knows = table.get(1).select("tr");
			System.out.println(knows.get(1).select("td").text());
			System.out.println(knows.get(2).select("td").text());
			System.out.println(knows.get(3).select("td").text());
			System.out.println(knows.get(4).select("td").text());
			System.out.println();
			totalExp += exp;
			totalExp2 += exp2;
			totalNormal += normal;
			totalMatch += o.size();
			totalDraw += draw;
			resultArrays.add(new ResultArray(exp, exp2, draw, normal, o.size()));
		}
		System.out.println("Average draw: " + (float)totalDraw / totalMatch * 15);
		System.out.println("Average exp: " + (float)totalExp / totalMatch * 15);
		System.out.println("Average exp2: " + (float)totalExp2 / totalMatch * 15);
		
		double ssNormal = 0;
		double ssDraw = 0;
		double ssExp = 0;
		double ssExp2 = 0;
		for(int i = 0; i < resultArrays.size(); i++) {
			ssNormal += Math.pow( (double)resultArrays.get(i).normal * 15 / resultArrays.get(i).size - (float)totalNormal / totalMatch * 15, 2);
			ssExp += Math.pow((double)resultArrays.get(i).exp * 15 / resultArrays.get(i).size - (float)totalExp / totalMatch * 15, 2);
			ssExp2 += Math.pow((double)resultArrays.get(i).exp2 * 15 / resultArrays.get(i).size - (float)totalExp2 / totalMatch * 15, 2);
			ssDraw += Math.pow((double)resultArrays.get(i).draw * 15 / resultArrays.get(i).size - (float)totalDraw / totalMatch * 15, 2);
		}
		
		ssNormal = Math.sqrt(ssNormal / (resultArrays.size() - 1));
		ssExp = Math.sqrt(ssExp / (resultArrays.size() - 1));
		ssExp2 = Math.sqrt(ssExp2 / (resultArrays.size() - 1));
		ssDraw = Math.sqrt(ssDraw / (resultArrays.size() - 1));
		
		System.out.println("ssNormal: " + ssNormal);
		System.out.println("ssExp: " + ssExp);
		System.out.println("ssExp2: " + ssExp2);
		System.out.println("ssDraw: " + ssDraw);
	}

}
