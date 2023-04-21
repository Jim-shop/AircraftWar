package net.imshit.io.scoreboard;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 计分板DAO的文件实现
 * @author Jim
 */
public class ScoreboardDaoFile implements ScoreboardDao {

    private final File f;
    private List<ScoreInfo> buffer;

    public ScoreboardDaoFile(String path) {
        this.f = new File(path);
        try {
            if (!f.exists()) {
                Files.createFile(Path.of(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (var ois = new ObjectInputStream(new FileInputStream(f))) {
            buffer = (ArrayList<ScoreInfo>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            buffer = new ArrayList<>();
        }
        buffer.sort(Comparator.comparingInt(ScoreInfo::score).reversed());
    }

    @Override
    public List<ScoreInfo> getTopKItem(int topK) {
        if (topK == -1) {
            return new ArrayList<>(buffer);
        } else {
            return new ArrayList<>(buffer.subList(0, topK));
        }
    }

    @Override
    public void addItem(ScoreInfo item) {
        buffer.remove(item);
        buffer.add(item);
        buffer.sort(Comparator.comparingInt(ScoreInfo::score).reversed());
        try (var oos = new ObjectOutputStream(new FileOutputStream(f))) {
            oos.writeObject(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteItem(ScoreInfo item) {
        buffer.remove(item);
        try (var oos = new ObjectOutputStream(new FileOutputStream(f))) {
            oos.writeObject(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
