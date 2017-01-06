package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gabrysia on 16.12.2016.
 */
public class Parser {
    private Option option;
    private String jsonToParse;
    private String memberName;

    private ArrayList<Member> memberList = new ArrayList<>();
    private ArrayList<PersonalCost> costList = new ArrayList<>();


    public Parser(Option option, String jsonToParse){
        this.option = option;
        this.jsonToParse = jsonToParse;
    };
    public Parser(Option option, String jsonToParse, String name){
        this.option = option;
        this.jsonToParse = jsonToParse;
        this.memberName = name;
    };
    public Parser(String jsonToParse){
        this.jsonToParse = jsonToParse;
    };

    public void parseMainJson() throws IllegalArgumentException{

        if(jsonToParse != null){
            JSONObject jsonObject = new JSONObject(jsonToParse);
            JSONArray jsonArray = jsonObject.getJSONArray("Dataobject");

            if(option.equals(Option.MEMBER_D_COSTS) || option.equals(Option.MEMBER_S_COSTS)){
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject o = jsonArray.getJSONObject(i);
                    if(generateSingleMember(o)){
                        return;
                    }
                }
                throw new IllegalArgumentException("Parser: the member doesnt exists");
            }
            else{
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject o = jsonArray.getJSONObject(i);
                    generateMember(o);
                }
            }

        }
    }

    private void generateMember(JSONObject o) {
        JSONObject data = (JSONObject) o.get("data");
        String name = data.getString("ludzie.nazwa");
        String id = o.getString("id");

        Member member = new Member(name, id);
        memberList.add(member);
    }

    public void parseCosts() {
        if (jsonToParse != null) {
            JSONObject jsonObject = new JSONObject(jsonToParse);
            JSONObject layers = (JSONObject) jsonObject.get("layers");
            JSONObject costs = (JSONObject) layers.get("wydatki");
            JSONArray costsArray = costs.getJSONArray("punkty");
            JSONArray yearsArray = costs.getJSONArray("roczniki");

            for (int i = 0; i < yearsArray.length(); i++){
                JSONObject y = yearsArray.getJSONObject(i);
                int year = Integer.parseInt(y.getString("rok"));

                for (int j = 0; j < costsArray.length(); j++){
                    JSONArray values = y.getJSONArray("pola");
                    JSONObject description = costsArray.getJSONObject(j);

                    int number = Integer.parseInt(description.getString("numer"));
                    String title = description.getString("tytul");
                    float value = Float.parseFloat(values.getString(j));

                    PersonalCost c = new PersonalCost(number, title, value, year);
                    costList.add(c);

                }
            }
        }
    }



    private boolean generateSingleMember(JSONObject o) {
        JSONObject data = (JSONObject) o.get("data");
        String name = data.getString("ludzie.nazwa");
        String id = o.getString("id");

        if(name.equals(memberName)){
            Member member = new Member(name, id);
            memberList.add(member);
            return true;
        }
        return false;
    }
    public ArrayList getMembers(){
        return memberList;
    }
    public ArrayList getCostList() { return costList; }


}
