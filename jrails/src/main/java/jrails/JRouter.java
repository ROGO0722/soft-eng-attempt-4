package jrails;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.lang.reflect.*;


public class JRouter {
    private Map<String, Map <String, String>> routes;
    public JRouter() {
        this.routes = new HashMap<>();
    }
    public void addRoute(String verb, String path, Class clazz, String method) {
        String controllerMethod = clazz.getName() + "#" + method;
        Map<String, String> param = routes.computeIfAbsent(verb, k -> new HashMap<>());
        param.put(path, controllerMethod);
    }

    // public void addRoute(String verb, String path, Class clazz, String method) {
    //     String controllerMethod = clazz.getSimpleName() + "#" + method;
    //     Map<String, String> param = routes.computeIfAbsent(verb, k -> new HashMap<>());
    //     param.put(path, controllerMethod);
    // }
    

    // Returns "clazz#method" corresponding to verb+URN
    // Null if no such route
    public String getRoute(String verb, String path) {
        Map<String, String> param = routes.get(verb);
        if (param != null) {
            return param.get(path);
        }
        return null;
    }
    
    // Call the appropriate controller method and
    // return the result
    public Html route(String verb, String path, Map<String, String> params) {
        String controllerMethod = getRoute(verb, path);
        if(controllerMethod != null) {
            String[] division = controllerMethod.split("#");
            String cName = division[0];
            String mName = division[1];
            try {
                Class<?> c = Class.forName("jrails." + cName);
                
               
            } catch (Exception e) {

            }
            
        }
        throw new UnsupportedOperationException();
    }
}
