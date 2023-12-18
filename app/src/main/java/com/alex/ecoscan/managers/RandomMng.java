package com.alex.ecoscan.managers;

import java.util.concurrent.ThreadLocalRandom;

public class RandomMng {
    public static int getRandomIdentifier(){
        return ThreadLocalRandom.current().nextInt(1000, 9999);
    }

}
