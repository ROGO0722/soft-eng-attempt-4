package jrails;

public class Html {
    private String html;

    public Html() {
        this.html = "";
    }

    public Html(String html) {
        this.html =  html;
    }

    public String toString() {
        return html;
    }

    public Html seq(Html h) {
        return new Html(this.html + h.html);
    }

    public Html br() {
        return new Html(this.html + "<br/>");
    }

    public Html t(Object o) {
        return new Html(this.html + o.toString());
    }

    public Html p(Html child) {
        return new Html(this.html + "<p>" + child.html + "</p>");
    }

    public Html div(Html child) {
        return new Html(this.html + "<div>" + child.html + "</div>");
    }

    public Html strong(Html child) {
        return new Html(this.html + "<strong>" + child.html + "</strong>");
    }

    public Html h1(Html child) {
        return new Html(this.html + "<h1>" + child.html + "</h1>");
    }

    public Html tr(Html child) {
        return new Html(this.html + "<tr>" + child.html + "</tr>");
    }

    public Html th(Html child) {
        return new Html(this.html + "<th>" + child.html + "</th>");
    }

    public Html td(Html child) {
        return new Html(this.html + "<td>" + child.html + "</td>");
    }

    public Html table(Html child) {
        return new Html(this.html + "<table>" + child.html + "</table>");
    }

    public Html thead(Html child) {
        return new Html(this.html + "<thead>" + child.html + "</thead>");
    }

    public Html tbody(Html child) {
        return new Html(this.html + "<tbody>" + child.html + "</tbody>");
    }

    public Html textarea(String name, Html child) {
        return new Html(this.html + "<textarea name=\"" + name + "\">" + child.html + "</teaxtarea>");
    }

    public Html link_to(String text, String url) {
        return new Html(this.html + "<a href=\"" + url + "\">" + text + "</a>");
    }

    public Html form(String action, Html child) {
        return new Html(this.html + "<form action=\"" + action + "\" accept-charset=\"UTF-8\" method=\"post\">" + child.html + "</form>");
    }

    public Html submit(String value) {
       return new Html(this.html + "input type=\"submit\"" + " value=\"" + value + "\"/>");
    }
}