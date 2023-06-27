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
    
    int width = arenaUpdate.arena.dims.get(0).intValue();
    int height = arenaUpdate.arena.dims.get(1).intValue();

    System.out.println("arena dim["+width+","+height+"]");

    String myURL = arenaUpdate._links.self.href;
    PlayerState myLocation = arenaUpdate.arena.state.get(myURL);

    System.out.println("myLocation["+myLocation.x+","+myLocation.y+"] Direction["+myLocation.direction+"] hit? "+myLocation.wasHit);



    // while(arenaUpdate.arena.state.values().iterator().hasNext()){
    //   PlayerState playerState = arenaUpdate.arena.state.values().iterator().next();
    //   if(playerState.){
    //     return "HIT";
    //   }
    // }

    // if(wasHit){
    //   if(canMoveForward(arenaUpdate.arena)){

    //     return "F";
    //   } else {
    //     // Cannot move forward, turn right
    //     return "R";
    //   }

    // }

    // random command
    String[] commands = new String[]{"F", "R", "L", "T"};
    int i = new Random().nextInt(4);
    
    return commands[i];
  }

  private boolean canMoveForward(Arena arena) {

    while(arena.state.values().iterator().hasNext()){
      
    }
    return false;
  }

}

