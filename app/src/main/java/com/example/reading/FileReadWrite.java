package com.example.reading;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FileReadWrite {
    public static final String textFileName = "person.txt";


    public static void readFileFromResources(ArrayList<Person> personArrList, Context context) {
        try (InputStream is = context.getResources().openRawResource(R.raw.persons);
             InputStreamReader isr = new InputStreamReader(is, "UTF8");
             BufferedReader reader = new BufferedReader(isr)) {

            personArrList.clear(); //empty the list we are going to readFile
            String strLine = reader.readLine(); // Ignore headers
            strLine = reader.readLine();

            while (strLine != null) {
                Person p = readPerson(strLine);
                personArrList.add(p);
                Log.d("Read Person ", p.toString());
                strLine = reader.readLine();
            }
        } catch (IOException e) {
            Log.e("ReadFromFile", "Error reading from file: persons.txt");
            e.printStackTrace();
        }
    }

    private static Person readPerson(String strLine) {
        String[] data = strLine.split(",");

        Person p = new Person();
        p.firstName = data[0].trim();
        p.lastName = data[1].trim();
        p.phone = data[2].trim();
        p.gender = Gender.valueOf(data[3].trim());
        return p;
    }

    private static void write2File(Person p, BufferedWriter writer) throws IOException {
        writer.append(p.firstName + ",");
        writer.append(p.lastName + ",");
        writer.append(p.phone + ",");
        writer.append(p.gender.toString() + "\n");
    }

    private static void writeHeader(BufferedWriter writer) throws IOException {
        writer.append("First name" + ",");
        writer.append("Last name " + ",");
        writer.append("phone" + ",");
        writer.append("Gender" + "\n");
    }

    private static boolean checkExternalStorageState() {
        // Verify that the external storage is available for writing
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) { // We can readFile and writeFile the media
            return true;
        }
        return false;
    }

    public static void readFile(ArrayList<Person> personArrList, Context context) {
        if (checkExternalStorageState()) {
            File txtFile = new File(context.getExternalFilesDir(null), textFileName);
            try (FileInputStream fis = new FileInputStream(txtFile);
                 InputStreamReader isr = new InputStreamReader(fis, "UTF8");
                 BufferedReader reader = new BufferedReader(isr)) {
                personArrList.clear(); // clear the list we are going to readFile
                String strLine = reader.readLine(); // ignore first line containing headers
                strLine = reader.readLine();
                while (strLine != null) {
                    Person p = readPerson(strLine);
                    personArrList.add(p);
                    Log.d("Read Person", p.toString());
                    strLine = reader.readLine();
                }
            } catch (IOException e) {
                Log.e("ReadFromFile", "Error reading from file: persons.txt");
                e.printStackTrace();
            }
        } // if mExternalStorageAvailable...
        else {
            Log.e("readFile", "Cannot access file '" + textFileName + "' - external storage not available or not readable");
        }
    }
    public static void writeFile(ArrayList<Person> personArrList, Context context) {
        // Check if external storage is available
        if (checkExternalStorageState()) {
            // Get the external storage directory and file
            File txtFile = new File(context.getExternalFilesDir(null), textFileName);

            // Use try-with-resources to ensure streams are closed properly
            try (FileOutputStream fos = new FileOutputStream(txtFile);
                 OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF8");
                 BufferedWriter writer = new BufferedWriter(osw)) {

                // Write the header (first line with column names)
                writeHeader(writer);

                // Write each person's information to the file
                for (int i = 0; i < personArrList.size(); i++) {
                    write2File(personArrList.get(i), writer);  // Write each Person object to the file
                    Log.d("Write2File", "Written: " + personArrList.get(i).toString());
                }

                Log.d("WriteFile", "File written successfully: " + txtFile.getAbsolutePath());

            } catch (IOException e) {
                Log.e("WriteFile", "Error writing to file: " + textFileName, e);
            }
        } else {
            Log.e("WriteFile", "External storage not available or not writable");
        }
    }

}
