package com.saviorru.comsserver.model;

import java.util.ArrayList;
import java.util.List;

public class LocationDispatcher {
    private List<Location> locationsList;


    public LocationDispatcher() {
        this.locationsList = new ArrayList<Location>();
    }

    public List<Location> getAllLocations() {
        return locationsList;
    }

    public void addLocation(Location newLocation) throws Exception
    {
        if (newLocation == null) throw new Exception("Null argument");
        if (this.locationsList.contains(newLocation)) {
            throw new Exception("Duplicate locations is not allowed");
        }
        else {
            this.locationsList.add(newLocation);
        }
    }
    public void removeLocation(Location existingLocation) throws Exception
    {
        if (existingLocation == null) throw new Exception("Null argument");
        if (this.locationsList.contains(existingLocation))
        {
            this.locationsList.remove(existingLocation);
        }
        else {
            throw new Exception("Location doesn't exist in dispather");
        }
    }
    public void removeLocationByPlace(String locationPlace) throws Exception
    {
        if (locationPlace == null) throw new Exception("Null argument");
        for (Location location: this.locationsList)
        {
            if (location.getPlace().equals(locationPlace))
            {
                this.locationsList.remove(location);
                return;
            }
        }
        throw new Exception("Location with specified place doesn't exist in dispather");
    }
    public Location getFreeLocation()
    {
        for (Location location: this.locationsList)
        {
            if (!(location.isBusy()))
            {
                return location;
            }
        }
        return null;
    }
    public List<Location> getAllFreeLocations()
    {
        List<Location> freeLocationsList = new ArrayList<Location>();
        for (Location location: this.locationsList)
        {
            if (!(location.isBusy()))
            {
                freeLocationsList.add(location);
            }
        }
        return  freeLocationsList;
    }
    public void reserveLocation (Location location) throws Exception
    {
        if (location == null) throw new Exception("Null argument");
        if (this.locationsList.contains(location))
        {
            if (!(location.isBusy()))
            {
                location.setBusy(true);
            }
            else
            {
                throw new Exception("Location is busy");
            }
        }
        else
        {
            throw new Exception("Location doesn't exist in dispather");
        }
    }
    public void reserveLocationByPlace(String locationPlace) throws Exception
    {
        if (locationPlace == null) throw new Exception("Null argument");
        for (Location location: this.locationsList)
        {
            if (location.getPlace().equals(locationPlace))
            {
                if (!(location.isBusy()))
                {
                    location.setBusy(true);
                    return;
                }
                else
                {
                    throw new Exception("Location is busy");
                }
            }

        }
        throw new Exception("Location doesn't exist in dispather");
    }
    public void freeLocation (Location location) throws Exception
    {
        if (location == null) throw new Exception("Null argument");
        if (this.locationsList.contains(location))
        {
                location.setBusy(false);

        }
        else
        {
            throw new Exception("Location doesn't exist in dispather");
        }
    }
    public void freeLocationByPlace(String locationPlace) throws Exception
    {
        if (locationPlace == null) throw new Exception("Null argument");
        for (Location location: this.locationsList)
        {
            if (location.getPlace().equals(locationPlace))
            {
                    location.setBusy(false);
                    return;
            }
        }
        throw new Exception("Location doesn't exist in dispather");
    }
}
