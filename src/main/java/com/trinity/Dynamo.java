package com.trinity;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Dynamo {
    private static final Dynamo INSTANCE = new Dynamo();
    private final DynamoDbClient client;

    private Dynamo() {
        this.client = DynamoDbClient
                .builder()
                .region(Region.US_EAST_2)
                .credentialsProvider(ProfileCredentialsProvider.builder().build())
                .build();
    }

    public static Dynamo getINSTANCE() {
        return INSTANCE;
    }

    public void savePlayer(Player p) {
        Map<String, AttributeValue> map = new HashMap<>();
        map.put("lastname", AttributeValue.builder().s(p.getLastName()).build());
        map.put("firstname", AttributeValue.builder().s(p.getFirstName()).build());
        map.put("playernum", AttributeValue.builder().n("" + p.getPlayerNumber()).build());
        client.putItem(PutItemRequest.builder().tableName("Players").item(map).build());
    }

    public Player getPlayer(String last, String first) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("lastname", AttributeValue.builder().s(last).build());
        key.put("firstname", AttributeValue.builder().s(first).build());
        GetItemResponse resp = client.getItem(GetItemRequest.builder().tableName("Players").key(key).build());
        if (!resp.hasItem()) {
            log.warn("Player not found! {} {}", last, first);
            return null;
        }
        return createPlayer(resp.item());
    }

    public List<Player> getAllPlayers() {
        ScanResponse resp = client.scan(ScanRequest.builder().tableName("Players").build());
        if (!resp.hasItems()) {
            log.warn("No players found!");
            return List.of();
        }
        List<Player> p = new ArrayList<>();
        for (Map<String, AttributeValue> playerMap : resp.items()) {
            p.add(createPlayer(playerMap));
        }
        return p;
    }

    private Player createPlayer(Map<String, AttributeValue> item) {
        String lastName = item.get("lastname").s();
        String firstName = item.get("firstname").s();
        int num = Integer.parseInt(item.get("playernum").n());
        Player p = new Player();
        p.setPlayerNumber(num);
        p.setFirstName(firstName);
        p.setLastName(lastName);
        return p;
    }
}
