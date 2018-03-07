package com.saviorru.comsserver.model;

import com.saviorru.comsserver.model.Location;
import org.junit.Test;

public class LocationTests {
    @Test(expected = Exception.class)
    public void locationNullTest() throws Exception {
        Location table = new Location (null, "");
    }
    @Test(expected = Exception.class)
    public void locationEmptyPlaceTest() throws Exception
    {
        Location table = new Location ("", "");
    }
    @Test(expected = Exception.class)
    public void locationNullDescTest() throws Exception
    {
        Location table = new Location ("1", null);
    }
}
