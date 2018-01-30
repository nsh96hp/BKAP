package com.test.cookcook.data;

import android.content.Context;

import com.test.cookcook.R;
import com.test.cookcook.data.entity.Cooked;
import com.test.cookcook.data.entity.Ingredients;
import com.test.cookcook.data.entity.Steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by NSH on 12/01/2018.
 */

public class ReadJSon {
    public static ArrayList<Cooked> ReadCookedJsonFile(Context context) {
        ArrayList<Cooked> lstCooked= new ArrayList<>();
        try {
            //Lay noi dung text cua file Json
            String jsonText = readText(context, R.raw.cookeddf);
            //Doi tuong root
            JSONObject jsonRoot = new JSONObject(jsonText);

            JSONArray objects = jsonRoot.getJSONArray("objects");
            JSONObject table = objects.getJSONObject(1);
            JSONArray data = table.getJSONArray("rows");
            String cook[];
            for (int i=0;i<data.length();i++){
                JSONArray result = data.getJSONArray(i);
                cook=new String[result.length()];
                for (int j=0;j<result.length();j++){
                    cook[j]=result.getString(j);
                }
                Cooked cooked = new Cooked();
                cooked.setIdCooked(cook[0]);
                cooked.setName(cook[1]);
                cooked.setIntro(cook[2]);
                cooked.setPeople(Integer.parseInt(cook[3]));
                cooked.setLike(0);
                cooked.setShare(0);
                cooked.setIdUser(cook[6]);
                cooked.setImage(cook[7]);
                lstCooked.add(cooked);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lstCooked;
    }

    public static ArrayList<Ingredients> ReadIngredientsJsonFile(Context context) {
        ArrayList<Ingredients> lstIngre= new ArrayList<>();
        try {
            //Lay noi dung text cua file Json
            String jsonText = readText(context, R.raw.cookeddf);
            //Doi tuong root
            JSONObject jsonRoot = new JSONObject(jsonText);

            JSONArray objects = jsonRoot.getJSONArray("objects");
            JSONObject table = objects.getJSONObject(2);
            JSONArray data = table.getJSONArray("rows");
            String temp[];
            for (int i=0;i<data.length();i++){
                JSONArray result = data.getJSONArray(i);
                temp=new String[result.length()];
                for (int j=0;j<result.length();j++){
                    temp[j]=result.getString(j);
                }
                Ingredients ingredients=new Ingredients();
                ingredients.setIdIngre(Integer.parseInt(temp[0]));
                ingredients.setIdCooked(temp[1]);
                ingredients.setName(temp[2]);
                ingredients.setAmount(Double.parseDouble(temp[3]));
                ingredients.setUnit(temp[4]);
                lstIngre.add(ingredients);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lstIngre;
    }

    public static ArrayList<Steps> ReadStepsJsonFile(Context context) {
        ArrayList<Steps> lstSteps= new ArrayList<>();
        try {
            //Lay noi dung text cua file Json
            String jsonText = readText(context, R.raw.cookeddf);
            //Doi tuong root
            JSONObject jsonRoot = new JSONObject(jsonText);

            JSONArray objects = jsonRoot.getJSONArray("objects");
            JSONObject table = objects.getJSONObject(3);
            JSONArray data = table.getJSONArray("rows");
            String temp[];
            for (int i=0;i<data.length();i++){
                JSONArray result = data.getJSONArray(i);
                temp=new String[result.length()];
                for (int j=0;j<result.length();j++){
                    temp[j]=result.getString(j);
                }
                Steps steps=new Steps();
                steps.setIdSteps(Integer.parseInt(temp[0]));
                steps.setIdCooked(temp[1]);
                steps.setNum(Integer.parseInt(temp[2]));
                steps.setName(temp[3]);
                steps.setTime(Double.parseDouble(temp[4]));
                steps.setUnit(temp[5]);
                steps.setImage(temp[6]);

                lstSteps.add(steps);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lstSteps;
    }
    private static String readText(Context context, int resId) {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder str = new StringBuilder();
        String s = null;
        try {
            while ((s = br.readLine()) != null) {
                str.append(s);
                str.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }
}
