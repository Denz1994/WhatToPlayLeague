package org.whattoplayleaguegenerator;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import javax.swing.JOptionPane;

public class WhatToPlayLeagueGenerator {
    public static void main(String[] args) throws IOException {
        ArrayList<String> allChamps = getChampsFromJson("Champions.json");

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
    public static ArrayList<String> getChampsFromJson(String filename) throws IOException {
        ArrayList<String> champions = new ArrayList<String>();
        try {
            InputStream inputStreamReader = WhatToPlayLeagueGenerator.class.getClassLoader().getResourceAsStream(filename);
            if (inputStreamReader== null){
                throw new IllegalArgumentException("File not found");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            HashMap championMap = objectMapper.readValue(inputStreamReader, HashMap.class);
            LinkedHashMap<String, Object> champDataMap = (LinkedHashMap<String, Object>) championMap.get("data");
            champions = new ArrayList<>(champDataMap.keySet());
        }
        catch(IOException e){
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }

        return champions;
    }
}