package dao;
import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class DaoNoSqlGeneric<T> implements CRUDInterface<T>{
    protected DB db;
    private String tableName;

    public void createTable() {
        try {
            db.createCollection(this.tableName, new BasicDBObject());
            System.out.print("Table created\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTable() {
        try {
            DBCollection collection = db.getCollection(this.tableName);
            collection.drop();
            System.out.print("Table deleted\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public Optional<T> read(Integer id) {
        try {
            DBCollection col = db.getCollection(this.tableName);
            DBObject query = BasicDBObjectBuilder.start().add("id", id).get();
            DBCursor cursor = col.find(query);
            if (cursor.hasNext()){
                T obj = newInstance(cursor.next());
                return Optional.of(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    };

    public List<T> getAll() {
        List<T> result = new ArrayList<>();
        try {
            DBCollection col = db.getCollection(this.tableName);
            DBCursor cursor = col.find();
            while(cursor.hasNext()){
                System.out.println(cursor.next());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    };

    public abstract T newInstance(DBObject obj);
    public abstract DBObject toDBObject(T t);

    public void create(T t){
        try {
            DBObject doc = toDBObject(t);
            DBCollection col = db.getCollection(this.tableName);
            WriteResult result = col.insert(doc);
            System.out.printf("1 rows added\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public void delete(Integer id) {
        try {

            DBCollection col = db.getCollection(this.tableName);
            DBObject query = BasicDBObjectBuilder.start().add("id", id).get();
            WriteResult result = col.remove(query);
            System.out.printf("%d rows deleted\n", result.getN());

        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public void update(T t, String atribute, Map<String, Object> params) {
        try {
            DBObject doc = toDBObject(t);
            DBCollection col = db.getCollection(this.tableName);
            DBObject query = BasicDBObjectBuilder.start().add("id", atribute).get();
            WriteResult result = col.update(query, doc);

            System.out.printf("%d rows updated\n", result.getN());

        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public DaoNoSqlGeneric(String tableName) {
        this.tableName = tableName;
    };
}
