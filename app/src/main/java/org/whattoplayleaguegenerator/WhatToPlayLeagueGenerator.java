package org.whattoplayleaguegenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class WhatToPlayLeagueGenerator {
    public static void main(String[] args){
        ArrayList<String> allChamps = getChamps("ChampList.txt");
        List<String> playstyles = new ArrayList<String>(Arrays.asList("burst dmg","annoying cdr", "tank", "pve", "support", "meta", "try hard sweaty"));
        Random random = new Random();
        String selectedChamp = allChamps.get(random.nextInt(allChamps.size()));
        String selectedPlaystyle = playstyles.get(random.nextInt(playstyles.size()));
        String message = String.format(
                "Champ: %-15s\nPlaystyle: %-15s",
                selectedChamp,
                selectedPlaystyle
        );
        System.out.println(message);
        JOptionPane.showMessageDialog(null,message);
    }

    public static ArrayList<String> getChamps(String fileName) {
        ArrayList<String> champs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                WhatToPlayLeagueGenerator.class.getClassLoader().getResourceAsStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    champs.add(line);
                }
            }
        } catch (IOException | NullPointerException error) {
            error.printStackTrace();
        }
        return champs;
    }
    // TODO: Use json reader not file reader. Jackson? JSON.simple?
    public static ArrayList<String> getChampsFromJson(String filename){
        ArrayList<String> champions = new ArrayList<String>();

        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = br.readLine()) != null){
                if(!line.isEmpty()){
                    System.out.println(line);
                }
            }
        }
        catch (IOException error){
            error.printStackTrace();
        }

        return champions;
    }
}