package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {

    private Connection cn;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection cn)     {
        this.cn = cn;
    }

    private void init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws SQLException {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement ps = cn.prepareStatement(
                "insert into items(id, name, created) values(?, ?, ?)")) {
            ps.setInt(1, item.getId());
            ps.setString(2, item.getName());
            ps.setTimestamp(3,
                    Timestamp.valueOf(item.getCreated()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        try (PreparedStatement ps = cn.prepareStatement(
                "update items set name = ?, created = ? where id = ?")) {
            ps.setString(1, item.getName());
            ps.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            ps.setInt(3, id);
            ps.execute();
            int updateCount = ps.getUpdateCount();
            if (updateCount > 0) {
                rsl = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        try (PreparedStatement ps = cn.prepareStatement(
                "delete from items where id = ?")) {
            ps.setInt(1, id);
            ps.execute();
            int updateCount = ps.getUpdateCount();
            if (updateCount > 0) {
                rsl = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement(
                "select * from items")) {
            ResultSet rsl = ps.executeQuery();
            while (rsl.next()) {
                int id = rsl.getInt("id");
                String name = rsl.getString("name");
                items.add(new Item(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement(
                "select * from items where name = ?")) {
            ps.setString(1, key);
            ResultSet rsl = ps.executeQuery();
            while (rsl.next()) {
                int id = rsl.getInt("id");
                String name = rsl.getString("name");
                items.add(new Item(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item item = new Item();
        try (PreparedStatement ps = cn.prepareStatement(
                "select * from items where id = ?")) {
            ps.setInt(1, id);
            ResultSet rsl = ps.executeQuery();
            while (rsl.next()) {
                item.setId(rsl.getInt("id"));
                item.setName(rsl.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
}