package tennis.model;

import java.util.List;

public class Player {

    private String name;
    private List<Boolean> pointPerRoundList;

    public Player() {
        this.name = "John Doe";
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Boolean> getPointPerRoundList() {
        return pointPerRoundList;
    }

    public void setPointPerRoundList(List<Boolean> pointPerRoundList) {
        this.pointPerRoundList = pointPerRoundList;
    }
}
