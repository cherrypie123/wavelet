import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;


class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;
    ArrayList<String> l = new ArrayList<>();
    ArrayList<String> ls = new ArrayList<>();
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Hoang's number: %d", num);
        } else if (url.getPath().equals("/increment")) {
            num += 1;
            return String.format("Number incremented!");
        } else if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                l.add(parameters[1]);
        }
        if(url.getPath().contains("/search")){
                String[] another = url.getQuery().split("=");
                for(String s: l){
                    if(s.contains(another[1])){
                        ls.add(s);
                    }
                }
                System.out.println(ls); 
            }
        
            return "404 Not Found!";
    }
}
class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
