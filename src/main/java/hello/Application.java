package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

@SpringBootApplication
@RestController
public class Application {

  static class Self {
    public String href;
  }

  static class Links {
    public Self self;
  }

  static class PlayerState {
    public Integer x;
    public Integer y;
    public String direction;
    public Boolean wasHit;
    public Integer score;
  }

  static class Arena {
    public List<Integer> dims;
    public Map<String, PlayerState> state;
    public Boolean wasHit;
  }

  static class ArenaUpdate {
    public Links _links;
    public Arena arena;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.initDirectFieldAccess();
  }

  @GetMapping("/")
  public String index() {
    return "Let the battle begin!";
  }

  @PostMapping("/**")
  public String index(@RequestBody ArenaUpdate arenaUpdate) {
    
    System.out.println(arenaUpdate);
  
    int size = arenaUpdate.arena.dims.size();
    
    int width = arenaUpdate.arena.dims.get(0);
    int height = arenaUpdate.arena.dims.get(1);

    boolean wasHit = arenaUpdate.arena.wasHit.booleanValue();

    System.out.println("arena dim["+width+","+height+"]");
    System.out.println("Were I hit? " + wasHit);

    // if(wasHit){



    //   return "R";
    // }


    String[] commands = new String[]{"F", "R", "L", "T"};
    int i = new Random().nextInt(4);
    
    // TODO 
    
    return commands[i];
  }

}

