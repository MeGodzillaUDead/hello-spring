package org.launchcode.hellospring.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@Controller
public class HelloController {

    @RequestMapping(value = "")
    @ResponseBody
    public String index(HttpServletRequest request) {

        String name = request.getParameter("name");

        if (name == null){
            name = "World";
        }

        return "Hello " + name;
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloForm() {

        String html = "<form method='post'>"
                +"<input type='text' name='name' />"
                +"<select name='language'>"
                +"<option value='en'>English</option>"
                +"<option value='fr'>French</option>"
                +"<option value='de'>German</option>"
                +"<option value='es'>Spanish</option>"
                +"<option value='au'>Australian</option>"
                +"</select>"
                +"<input type='submit' value='Greet ME'/>"
                +"</form>";

        return html;

    }

    @RequestMapping(value = "hello", method = RequestMethod.POST)
    @ResponseBody
    public String helloPost(HttpServletRequest request) {
        String name = request.getParameter("name");
        String language = request.getParameter("language");

        return HelloController.createMessage(name, language);
    }

    @RequestMapping(value = "/hello/{name}")
    @ResponseBody
    public String helloUrlSegment(@PathVariable String name){
        return "Hello " + name;
    }

    @RequestMapping(value = "goodbye")
    @ResponseBody
    public String goodbye() {
        return "Goodbye, frog";
    }

    public static String createMessage(String name, String language){

      String greeting = "Hello ";

        if (language.equals("fr")) {
            greeting = "Bonjour ";
        } else if (language.equals("de")) {
            greeting = "Guten Tag ";
        } else if (language.equals("es")) {
            greeting = "Hola ";
        } else if (language.equals("au")) {
            greeting = "G'day ";
        }

        return greeting + name;

    }

}
