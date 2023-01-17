package ru.job4j.tracker.store;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.SqlTracker;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = new FileInputStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenFindItemByName() {
        SqlTracker tracker = new SqlTracker(connection);
        List<Item> items = new ArrayList<>();
        Item item1 = tracker.add(new Item("item01"));
        Item item2 = tracker.add(new Item("item01"));
        Item item3 = tracker.add(new Item("item03"));
        Item item4 = tracker.add(new Item("item04"));
        items.add(item1);
        items.add(item2);
        assertThat(tracker.findByName("item01")).isEqualTo(
                List.of(item1, item2));
    }

    @Test
    public void whenFindAll() {
        SqlTracker tracker = new SqlTracker(connection);
        List<Item> items = new ArrayList<>();
        Item item1 = tracker.add(new Item("item01"));
        Item item2 = tracker.add(new Item("item02"));
        Item item3 = tracker.add(new Item("item03"));
        Item item4 = tracker.add(new Item("item04"));
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        assertThat(tracker.findAll()).isEqualTo(
                        List.of(item1, item2, item3, item4));
    }

    @Test
    public void whenAddAndDeleteThenTrue() {
        SqlTracker tracker = new SqlTracker(connection);
        Item firstItem = new Item("item01");
        assertThat(tracker.add(firstItem).getName()).isEqualTo("item01");
        assertThat(tracker.delete(firstItem.getId())).isTrue();
        assertThat(tracker.delete(firstItem.getId())).isFalse();
    }

    @Test
    public void whenFindDeletedItemThenNull() {
        SqlTracker tracker = new SqlTracker(connection);
        Item firstItem = new Item("item01");
        assertThat(tracker.add(firstItem).getName()).isEqualTo("item01");
        assertThat(tracker.delete(firstItem.getId())).isTrue();
        assertThat(tracker.findById(firstItem.getId())).isNull();
    }

    @Test
    public void whenReplaceThenTrue() {
        SqlTracker tracker = new SqlTracker(connection);
        Item firstItem = new Item("item01");
        Item secondItem = new Item("item02");
        tracker.add(firstItem);
        tracker.add(secondItem);
        tracker.replace(firstItem.getId(), secondItem);
        assertThat(tracker.findById(firstItem.getId()).getName())
                .isEqualTo(secondItem.getName());
    }
}