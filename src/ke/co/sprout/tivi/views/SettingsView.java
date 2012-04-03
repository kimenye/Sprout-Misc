package ke.co.sprout.tivi.views;

import javax.microedition.lcdui.Form;

/**
 * Settings view
 */
public class SettingsView extends Form implements View {

//    ChoiceGroup tempUnits = new ChoiceGroup("Temperature Unit", Choice.POPUP,
//            new String[] {"Celsius", "Fahrenheit"}, null);
//    ChoiceGroup windUnits = new ChoiceGroup("Windspeed Unit", Choice.POPUP,
//            new String[] {UnitUtil.getWindSpeedUnit(Settings.MPS), UnitUtil.getWindSpeedUnit(Settings.KMPH),
//            UnitUtil.getWindSpeedUnit(Settings.MPH), UnitUtil.getWindSpeedUnit(Settings.KNOTS)}, null);

    public SettingsView() {
        super("Settings");
//        append(tempUnits);
//        append(windUnits);
    }

    public void activate() {
    }

    public void deactivate() {
//        Settings.temperatureUnit = tempUnits.getSelectedIndex();
//        Settings.windSpeedUnit = windUnits.getSelectedIndex();
    }

}
