package com.dany.androidtest.robolectric.shadow;
import com.dany.androidtest.bean.Person;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

/**
 * {@link Person}的影子类
 *自定义Shadow
 * Created by dan.y on 2018/8/15.
 */
@Implements(Person.class)
public class ShadowPerson {

    @Implementation
    public String getName(){
        return "AndroidUT";
    }

    @Implementation
    public int getSex() {
        return 0;
    }

    @Implementation
    public int getAge(){
        return 18;
    }

}
