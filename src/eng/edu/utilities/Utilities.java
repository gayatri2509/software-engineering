/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.edu.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author deeptichavan
 */
public class Utilities {

    public static String basePath = System.getProperty("user.home");
    public static String baseDirectory = "/questions/";
    public static int min = 1;
    public static int max;
    public int number;
    public static String assumptionsTxt;
    public static ArrayList<Integer> questionAlreadyDone = new ArrayList<>();

    public Utilities() {

        max = getNumberOfQuestions();
        number = getRandomQuestion(max);
        assumptionsTxt = getPath("assumptions", ".txt");
    }

    public static int getNumberOfQuestions() {

        File file = new File(basePath + baseDirectory);
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });

        return directories.length;
    }

    public static int getRandomQuestion(int n) {

        Random rand = new Random();
        //boolean check = quesAlreadyDone();
        int quesNo = rand.nextInt(n) + min;

        if (questionAlreadyDone.size() != max) {
            if (questionAlreadyDone.contains(quesNo)) {
                quesNo = getRandomQuestion(n);
            } else {
                System.out.println("Adding ques :: " + quesNo);
                questionAlreadyDone.add(quesNo);
            }
        }

        return quesNo;

    }

    public String getPath(String imageType, String fileType) {

        File file = new File(basePath + baseDirectory + "q" + number + "/" + imageType + number + fileType);
        String filename = file.toURI().toString();
        String parts[] = filename.split("file:");
   
        boolean exists = fileExists(parts[1]);
        if(exists)
            return filename;
        else
            return null;
    }
    
    public boolean fileExists(String filename)
    {
        File varTmpDir = new File(filename);
        boolean exists = varTmpDir.exists();
        System.out.println("exists: "+exists);
        return exists;
    }

    public static BufferedReader getFileReader(String fileName, int n1) {
        BufferedReader bufferedReader = null;
        try {

            File file = new File(basePath + baseDirectory + "q" + n1 + "/" + fileName + n1 + ".txt");
            String fileTxt = file.toURI().toString();
            String[] split = fileTxt.split("file:");
            fileTxt = split[1];

            bufferedReader = new BufferedReader(new FileReader(fileTxt));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return bufferedReader;
    }

}
