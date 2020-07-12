package cscie55.hw4.zoo.animals;

import java.util.ArrayList;
import static cscie55.hw4.utils.NumUtil.getRandomBetween;

// by implementing Comparable with compareTo() below,
//  I ensure that collections of Animals return sorted base on (alphabetical) name property order
public abstract class Animal implements Comparable<Animal>{

    private String name = "";
    private int age;
    private ArrayList<String> favoriteFoods = new ArrayList<>();

    /************* constructors   *************/
    public Animal(){
        this(getRandomBetween(0,250),"Annonymous");
    }

    public Animal(int age, String name){
        this(age, name, new String[] {"kibble"});
    }

    public Animal(int age, String name, String[] favoriteFoods){
        this.age = age;
        this.name = name;
        for(String food : favoriteFoods) {
            this.favoriteFoods.add(food);
        }
    }

    /************ implementation of Comparable's required compareTo method ***************/
    @Override
    public int compareTo(Animal a) {
        // compareTo is based on the value of the name property
        int result = this.name.compareTo(a.getName());
        return (result > 0 ? 1 : (result < 0 ? -1 : 0));
    }

    /************* override methods   *************/
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append(this.age);
        sb.append(this.favoriteFoods.toString());
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((name == null) ? 0 : name.hashCode()) + age
                + favoriteFoods.hashCode();
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        // this is a series of tests verify type and comparing value of properties
        if (this == obj) { // points to same Object in memory
            return true;
        }
        if (obj == null) { // if null, can't be equal
            return false;
        }
        if (getClass() != obj.getClass()) { // test for same Class type
            return false;
        }

        final Animal other = (Animal) obj;
        if (name == null) {
            if (other.name != null) // if field is null, can't be equal
                return false;
        } else if (!name.equals(other.name)) {// if value is different, can't be equal
            return false;
        }
        else if(age != other.getAge()){
            return false;
        }
        else if(!favoriteFoods.toString().equals(other.getFavoriteFoods().toString())){
            return false;
        }
        return true; // FINALLY!!! It must be equal!

    }

    /************* getters/setters   *************/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void addFavoriteFood(String food) {
        this.favoriteFoods.add(food);
    }




}

