package jrails;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;

public class Model {
    private static int count = 0;
    private int id = 0;
    private static HashMap<Integer, Model> model = new HashMap<>();
    private static String db = "dataBase";

    public void save() {
        /* this is an instance of the current model */
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(db, true));
            LinkedList <String> lines = new LinkedList<String>();
            // BufferedReader reader = new BufferedReader(new FileReader(db));
            // String line;
            if(this.id() == 0) {
                count++;
                this.setID(count);
                model.put(this.id(), this);
                System.out.println("added ID: " + this.id());
                // System.out.println(this.getClass() + " added");
            }
            // System.out.println("model size: " + model.size());
            for (int a : model.keySet()) {
                // System.out.println("got obejct from map " + a);
                if(model.get(a) != null) {
                    Class<?> c = model.get(a).getClass();
                    // System.out.println("hello");
                    Field[] fields = c.getDeclaredFields();
                    // LinkedList <String> types = new LinkedList<String>();
                    LinkedList <String> column = new LinkedList<String>();
                    //  System.out.println("hello1");
                    column.add(model.get(a).getClass().getName());
                    for(Field field: fields) {
                        // System.out.println("hello2");
                        if (field.isAnnotationPresent(Column.class)) {
                            field.setAccessible(true);
                            Class<?> f = field.getType();
                            if((f == int.class) || (f == String.class) || (f == boolean.class)) {
                                if(field.get(model.get(a)) != null) {
                                    Object o = field.get(model.get(a));
                                    String value = o.toString();
                                    column.add(value);
                                } else {
                                    column.add("");
                                }
                            }
                        }
                    }
                    column.addFirst(String.valueOf(model.get(a).id()));
                    lines.add(String.join(",   ", column));
                    // System.out.println("added: " + String.join(",   ", column));
                }
            }
            reset();
            for (String newline : lines) {
                writer.write(newline);
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    public int id() {
        return id;
    }

    public static <T> T find(Class<T> c, int id) {
        Model t = model.get(id);
        if(t != null && c.isInstance(t)) {
            return c.cast(t);
        }
        return null;
    }

    public static <T> List<T> all(Class<T> c) {
        LinkedList <T> all = new LinkedList<>();
        for (int a : model.keySet()) {
            if(model.get(a) != null) {
                Model currentModel = model.get(a);
                if (c.isInstance(currentModel)) {
                    all.add(c.cast(currentModel));
                }
                System.out.println(currentModel.id);
            }
        }
        System.out.println("Total models: " + all.size());
        return all;
    }
    public void setID(int newId) {
        id = newId;
    }
    public void destroy() {
        if (this.id() == 0 || model.get(this.id()) == null) {
            throw new IllegalStateException("Cannot destroy: Model has not been saved previously.");
        }
        int recordedId = this.id();
        model.remove(recordedId);
        System.out.println("recorded ID: " + recordedId + " destroyed");
    }
    public static void reset() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(db, false));
            writer.write(""); // Clear the file content
            writer.close();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
