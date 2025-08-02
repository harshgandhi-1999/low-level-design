package easy.notificationsystemlld.service;

import easy.notificationsystemlld.channels.ChannelType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelPreferenceService {

    private final Map<String, List<ChannelType>> userPreferences;

    public ChannelPreferenceService() {
        this.userPreferences = new HashMap<>();
    }

    public void addUserPreference(String userId, ChannelType channelType) {
        // Add the channel type to the list of preferred channels for the given user id. If the user does not exist in the map,
        // create a new entry with an empty list and then add the channel type to that list.
        userPreferences.getOrDefault(userId, new ArrayList<>()).add(channelType);
    }

    public void setUserPreferences(String userId, List<ChannelType> preferenceList) {
        if (preferenceList.isEmpty()) {
            throw new IllegalArgumentException("Preference list cannot be empty");
        }

        userPreferences.put(userId, preferenceList);
    }


    public List<ChannelType> getPreferredChannels(String userId) {
        return userPreferences.getOrDefault(userId, new ArrayList<>());
    }
}
