package launcher.aircraft;

import launcher.aircraft.*;
import launcher.weather.*;
import launcher.writer.*;

public interface Flyable
{
    public void updateConditions();
    public void registerTower(WeatherTower weatherTower);
    public void unregisterTower(WeatherTower weatherTower);
}