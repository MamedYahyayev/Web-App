package az.maqa.project.dao.impl;

import az.maqa.project.dao.helper.DbHelper;
import az.maqa.project.dao.inter.TopicDao;
import az.maqa.project.model.*;
import az.maqa.project.utility.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TopicDaoImpl implements TopicDao {
    @Override
    public List<Topic> getTopicList() throws Exception {
        List<Topic> topicList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT T.ID, T.NAME, C2.ID class_id, C2.CLASS_NUMBER, L.ID lesson_id, L.LESSON_NAME  FROM TOPIC T " +
                " INNER JOIN CLASS C2 on T.CLASS_ID = C2.ID " +
                " INNER JOIN LESSON L on T.LESSON_ID = L.ID " +
                " WHERE T.ACTIVE = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Topic topic = new Topic();
                    ClassRoom classRoom = new ClassRoom();
                    Lesson lesson = new Lesson();

                    topic.setId(rs.getLong("ID"));
                    topic.setTopicName(rs.getString("NAME"));

                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));

                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));

                    topic.setLesson(lesson);
                    topic.setClassRoom(classRoom);

                    topicList.add(topic);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }
        return topicList;
    }

    @Override
    public boolean add(Topic topic) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  TOPIC(ID, NAME, CLASS_ID, LESSON_ID) " +
                " VALUES (TOPIC_SEQ.nextval,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, topic.getTopicName());
                ps.setLong(2, topic.getClassRoom().getId());
                ps.setLong(3, topic.getLesson().getId());
                ps.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, null);
        }
        return result;
    }

    @Override
    public Topic getTopicById(Long id) throws Exception {
        Topic topic = new Topic();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT T.ID, T.NAME, C2.ID class_id, C2.CLASS_NUMBER, L.ID lesson_id, L.LESSON_NAME  FROM TOPIC T " +
                " INNER JOIN CLASS C2 on T.CLASS_ID = C2.ID " +
                " INNER JOIN LESSON L on T.LESSON_ID = L.ID " +
                " WHERE (T.ACTIVE = 1) AND (T.ID = ?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ClassRoom classRoom = new ClassRoom();
                    Lesson lesson = new Lesson();

                    topic.setId(rs.getLong("ID"));
                    topic.setTopicName(rs.getString("NAME"));

                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));

                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));

                    topic.setLesson(lesson);
                    topic.setClassRoom(classRoom);


                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }
        return topic;
    }

    @Override
    public boolean update(Topic topic, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE TOPIC SET NAME = ?, CLASS_ID = ?, LESSON_ID = ? WHERE ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, topic.getTopicName());
                ps.setLong(2, topic.getClassRoom().getId());
                ps.setLong(3, topic.getLesson().getId());
                ps.setLong(4, id);
                ps.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE TOPIC SET ACTIVE = ? WHERE ID = ?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, 0);
                ps.setLong(2, id);
                ps.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, null);
        }
        return result;
    }

    @Override
    public List<Topic> search(String keyword) throws Exception {
        List<Topic> topicList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT T.ID, T.NAME, C2.ID class_id, C2.CLASS_NUMBER, L.ID lesson_id, L.LESSON_NAME\n" +
                "FROM TOPIC T\n" +
                "         INNER JOIN CLASS C2 on T.CLASS_ID = C2.ID\n" +
                "         INNER JOIN LESSON L on T.LESSON_ID = L.ID\n" +
                "WHERE (T.ACTIVE = 1)\n" +
                "  AND (LOWER(C2.CLASS_NUMBER) LIKE LOWER(?) OR LOWER(L.LESSON_NAME) LIKE LOWER(?) OR LOWER(T.NAME) LIKE LOWER(?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                ps.setString(3, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Topic topic = new Topic();
                    ClassRoom classRoom = new ClassRoom();
                    Lesson lesson = new Lesson();

                    topic.setId(rs.getLong("ID"));
                    topic.setTopicName(rs.getString("NAME"));

                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));

                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));

                    topic.setLesson(lesson);
                    topic.setClassRoom(classRoom);

                    topicList.add(topic);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }
        return topicList;
    }
}
