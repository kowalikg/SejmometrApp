package com.company;

import org.junit.Test;

/**
 * Created by Gabrysia on 12.01.2017.
 */
public class MainTest {
    @Test
    public void main() throws Exception {
        String[] t1 = new String[2];
        t1[0] = "5";
        t1[1] = "--mej";
        assert (Main.launch(t1) == false);
        String[] t2 = new String[4];
        t2[0] = "VII";
        t2[1] = "-d";
        t2[2] = "Jan";
        t2[3] = "Kowalski";
        assert (Main.launch(t2) == false);
        t2[0] = "VII";
        t2[1] = "-da";
        t2[2] = "Jan";
        t2[3] = "Kowalski";
        assert (Main.launch(t2) == false);
        t1[0] = "8";
        t1[1] = "--italy";
        assert (Main.launch(t1) == true);
        t1[0] = "7";
        t1[1] = "--mj";
        assert (Main.launch(t1) == true);
        t1[0] = "7";
        t1[1] = "--average";
        assert (Main.launch(t1) == true);
        t2[0] = "VII";
        t2[1] = "-s";
        t2[2] = "Robert";
        t2[3] = "Biedroń";
        assert (Main.launch(t2) == true);


    }

}