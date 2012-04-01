/*
 * Copyright © 2011 Nokia Corporation. All rights reserved.
 * Nokia and Nokia Connecting People are registered trademarks of Nokia Corporation.
 * Oracle and Java are trademarks or registered trademarks of Oracle and/or its
 * affiliates. Other product and company names mentioned herein may be trademarks
 * or trade names of their respective owners.
 * See LICENSE.TXT for license information.
 */
package ke.co.sprout.tivi.views;

import java.util.Vector;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Form;

/**
 * View of recent channels
 */
public class ChannelsView extends Form implements View {

    private Vector channels;
    private ChoiceGroup list;

    public ChannelsView() {
        super("Recent channels");
        list = new ChoiceGroup(null, Choice.EXCLUSIVE);
        list.setFitPolicy(Choice.TEXT_WRAP_ON);
        append(list);
    }

    /**
     * Populates the recent channels list. If there are no channels to show, redirects to search view
     */
    public void activate() {
//        channels = Settings.recentchannels;
//        if (channels.isEmpty()) {
//            ViewMaster.getInstance().skipToView(ViewMaster.VIEW_SEARCH);
//        } else {
//            int length = channels.size();
//            for (int i = 0; i < length; i++) {
//                Location location = (Location) channels.elementAt(i);
//                list.append(location.city + ", " + location.country, null);
//            }
//            int index = channels.indexOf(Settings.location);
//            if (index > -1) {
//                list.setSelectedIndex(index, true);
//            }
//        }
    }

    /**
     * Empties the list items
     */
    public void deactivate() {
        selectItem();
        list.deleteAll();
    }

    public void selectItem() {
//        int selected = list.getSelectedIndex();
//        if (selected > -1) {
//            Settings.setLocation((Location) channels.elementAt(selected));
//        }
    }
}
