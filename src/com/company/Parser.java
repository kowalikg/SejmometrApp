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
    private ArrayList<Member> memberList = new ArrayList<>();

    public Parser(Option option, String jsonToParse){
        this.option = option;
        this.jsonToParse = jsonToParse;
    };

    public void parseMainJson(){
        if(jsonToParse != null){
            JSONObject jsonObject = new JSONObject(jsonToParse);
            JSONArray jsonArray = jsonObject.getJSONArray("Dataobject");

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject o = jsonArray.getJSONObject(i);
                generateMember(o);

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
    public ArrayList getMembers(){
        return memberList;
    }

}
