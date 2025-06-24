package org.ua.fkrkm.proglatformdao.dao.impl;

import com.mongodb.client.MongoClient;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.ua.fkrkm.proglatformdao.dao.TestDaoI;
import org.ua.fkrkm.proglatformdao.entityMongo.Test;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

/**
 * Клас для роботи з документом "Тести"
 */
public class TestDaoImpl implements TestDaoI {

    private final MongoTemplate mongoTemplate;

    public TestDaoImpl(MongoClient mongoClient, String dbName) {
        this.mongoTemplate = new MongoTemplate(mongoClient, dbName);
    }

    public TestDaoImpl(MongoDatabaseFactory mongoDbFactory) {
        this.mongoTemplate = new MongoTemplate(mongoDbFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String create(Test entity) {
        Test test = mongoTemplate.save(entity);
        return test.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Test entity) {
        if (entity.getId() == null) return;

        Query query = new Query(Criteria.where("id").is(entity.getId()));

        Update update = new Update();

        if (entity.getName() != null) {
            update.set("name", entity.getName());
        }
        if (entity.getTopicId() != null) {
            update.set("topicId", entity.getTopicId());
        }
        if (!entity.getQuestions().isEmpty()) {
            update.set("questions", entity.getQuestions());
        }

        mongoTemplate.findAndModify(query, update, Test.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Test getByUUID(String uuid) {
        return mongoTemplate.findById(uuid, Test.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Test> getByTopicId(Integer topicId) {
        Query query = new Query(Criteria.where("topicId").is(topicId));
        return mongoTemplate.find(query, Test.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Test> getByTopicIds(List<Integer> topicIds) {
        Query query = new Query(Criteria.where("topicId").in(topicIds));
        return mongoTemplate.find(query, Test.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Test> getAll() {
        return mongoTemplate.findAll(Test.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String uuid) {
        Query query= new Query();
        query.addCriteria(Criteria.where("id").is(uuid));
        mongoTemplate.findAndRemove(query, Test.class);
    }
}
