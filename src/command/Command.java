package command;

import java.util.ArrayList;
import java.util.List;

public class Command {
    private String name;
    private List<Integer> intParams;
    private List<String> strParams;
    private List<Float> floatParams;

    public Command() {
        this.intParams = new ArrayList<>();
        this.strParams = new ArrayList<>();
        this.floatParams = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getIntParams() {
        return intParams;
    }

    public void addIntParam(int param) {
        this.intParams.add(param);
    }

    public List<String> getStrParams() {
        return strParams;
    }

    public void addStrParam(String param) {
        this.strParams.add(param);
    }

    public List<Float> getFloatParams() {
        return floatParams;
    }

    public void addFloatParam(float param) {
        this.floatParams.add(param);
    }

    @Override
    public String toString() {
        return "Command{" +
                "name='" + name + '\'' +
                ", intParams=" + intParams +
                ", strParams=" + strParams +
                ", floatParams=" + floatParams +
                '}';
    }
}