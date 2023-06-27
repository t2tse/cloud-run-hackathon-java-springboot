package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    
    int width = arenaUpdate.arena.dims.get(0).intValue();
    int height = arenaUpdate.arena.dims.get(1).intValue();

    System.out.println("arena dim["+width+","+height+"]");

    String myURL = arenaUpdate._links.self.href;
    PlayerState myLocation = arenaUpdate.arena.state.get(myURL);

    System.out.println("myLocation["+myLocation.x+","+myLocation.y+"] Direction["+myLocation.direction+"] hit? "+myLocation.wasHit);

    // get all other player locations
    arenaUpdate.arena.state.remove(myURL);
    Collection<PlayerState> otherLocations = arenaUpdate.arena.state.values();

    // TODO fix this later
    // if(myLocation.wasHit){
    //   if(canMoveForward(myLocation, otherLocations, width, height)){

    //     return "F";
    //   } else {
    //     // Cannot move forward, turn right
    //     return "R";
    //   }
    // } 

    // not hit, attempt to hit someone or just move randomly

    // random command
    String[] commands = new String[]{"F", "R", "L", "T"};
    int i = new Random().nextInt(4);
    
    return commands[i];

  }

  private boolean canMoveForward(PlayerState myLocation, Collection<PlayerState> others, int width, int height) {
    
    int newX = myLocation.x;
    int newY = myLocation.y;
    
    switch(myLocation.direction) {
      case "E":
        newX++;
      case "S":      
        newY++;
      case "W":
        newY--;
      case "N":
        newX--;
    }

    for (PlayerState other : others) {
      if (myLocation.x == other.x && myLocation.y == other.y) {
      }
    }
    return false;
  }


}


