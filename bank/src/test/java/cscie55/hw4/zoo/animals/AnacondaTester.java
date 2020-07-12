package cscie55.hw4.zoo.animals;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import static cscie55.hw4.utils.NumUtil.getRandomBetween;

public class AnacondaTester
{

    // here, I am using each of the 4 defined constructors
    Animal a1 = new Anaconda(); // default constuctor
    Animal a2 = new Anaconda(getRandomBetween(25,30)); // using constructor that takes int for age w/ generated numb
    Animal a3 = new Anaconda(2,"BabyConda"); // using constructor with age and name
    Animal a4 = new Anaconda(140, "LongLivingConda", new String[]{"goats","cows","children", "sleeping adults"}); // using ALL params

    @Test
    public void testDefaultConda(){
        assertEquals(100,a1.getAge());// should return default age
        assertEquals("AnnonyConda" ,a1.getName());// should return default name
        assertEquals("humans", a1.getFavoriteFoods().get(0));// should return default menu
    }

    @Test
    public void testCondaConstr2(){
        assertTrue(a2.getAge()>=25 && a2.getAge()<=30);// age should fit in this range, as passed to second constructor
        assertEquals("AnnonyConda" ,a2.getName());// should return provided name
        assertEquals("humans", a2.getFavoriteFoods().get(0));// should return default menu
    }
    @Test
    public void testCondaConstr3(){
        assertEquals(2,a3.getAge());// should return provided age
        assertEquals("BabyConda" ,a3.getName());// should return provided name
        assertEquals("humans", a3.getFavoriteFoods().get(0));// should return default menu
    }

    @Test
    public void testCondaConstr4(){
        assertEquals(140,a4.getAge());// should return provided age
        assertEquals("LongLivingConda" ,a4.getName());// should return provided name
        assertEquals("goats", a4.getFavoriteFoods().get(0));// should return the menu provided
    }
}
