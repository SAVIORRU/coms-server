package com.saviorru.comsserver.model;

import java.util.ArrayList;
import java.util.List;

public class LocationDispather {
    private List<Location> locationsList;


    public LocationDispather() {
        this.locationsList = new ArrayList<Location>();
    }

    public List<Location> getAllLocations() {
        return locationsList;
    }

    public void addLocation(Location newLocation) throws Exception
    {
        if (this.locationsList.contains(newLocation)) {
            throw new Exception("Duplicate locations is not allowed");
        }
        else {
            this.locationsList.add(newLocation);
        }
    }
    public void removeLocation(Location existingLocation) throws Exception
    {
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
        for (Location location: this.locationsList)
        {
            if (location.getPlace().equals(locationPlace))
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
    }
    public void freeLocation (Location location) throws Exception
    {
        if (this.locationsList.contains(location))
        {
            if (!(location.isBusy()))
            {
                location.setBusy(false);
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
    public void freeLocationByPlace(String locationPlace) throws Exception
    {
        for (Location location: this.locationsList)
        {
            if (location.getPlace().equals(locationPlace))
            {
                if (!(location.isBusy()))
                {
                    location.setBusy(false);
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
    }
}
