package dao;

import entity.SuperEntity;

import java.io.IOException;
import java.util.List;

public interface SuperDAO<Entity extends SuperEntity, ID> {
        public boolean save(Entity entity) throws Exception;

        public boolean update(Entity entity) throws Exception;

        public boolean delete(ID id) throws Exception;

        public boolean exists(ID id) throws Exception;

        public List<Entity> getAll() throws Exception;

        public Entity search(ID id) throws IOException;

        public String generateNewID() throws IOException;
    }