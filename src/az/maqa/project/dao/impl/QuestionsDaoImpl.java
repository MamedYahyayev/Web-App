package az.maqa.project.dao.impl;

import az.maqa.project.dao.helper.DbHelper;
import az.maqa.project.model.ClassRoom;
import az.maqa.project.model.Lesson;
import az.maqa.project.model.Questions;
import az.maqa.project.model.Topic;
import az.maqa.project.utility.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoImpl {
    public List<Questions> getQuestionsList() throws Exception {
        List<Questions> questionsList = new ArrayList();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,QUESTIONS.ID,QUESTIONS.QUESTION, " +
                " T.NAME,C2.CLASS_NUMBER,L.LESSON_NAME,C2.ID class_id,L.ID lesson_id FROM  QUESTIONS  " +
                " INNER JOIN TOPIC T on QUESTIONS.TOPIC_ID = T.ID  " +
                " INNER JOIN CLASS C2 on T.CLASS_ID = C2.ID  " +
                " INNER JOIN LESSON L on T.LESSON_ID = L.ID  " +
                " WHERE  QUESTIONS.ACTIVE = 1";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Questions questions = new Questions();
                    questions.setId(rs.getLong("ID"));
                    questions.setRownum(rs.getLong("r"));
                    questions.setQuestions(rs.getString("QUESTION"));
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    ClassRoom classRoom = new ClassRoom();
                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));
                    Topic topic = new Topic();
                    topic.setTopicName(rs.getString("NAME"));
                    topic.setLesson(lesson);
                    topic.setClassRoom(classRoom);
                    questions.setTopic(topic);
                    questionsList.add(questions);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var13) {
            var13.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }

        return questionsList;
    }

    public boolean add(Questions questions) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO QUESTIONS(ID, QUESTION, TOPIC_ID)  " +
                " VALUES (QUESTION_SEQ.nextval,?,?)";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, questions.getQuestions());
                ps.setLong(2, questions.getTopic().getId());
                ps.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, (ResultSet) null);
        }

        return result;
    }

    public List<Questions> getQuestionByTopicId(Long id) throws Exception {
        List<Questions> questionsList = new ArrayList();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,QUESTIONS.ID,QUESTIONS.QUESTION, T.NAME,C2.CLASS_NUMBER," +
                " L.LESSON_NAME,C2.ID class_id,L.ID lesson_id FROM  QUESTIONS  " +
                " INNER JOIN TOPIC T on QUESTIONS.TOPIC_ID = T.ID  " +
                " INNER JOIN CLASS C2 on T.CLASS_ID = C2.ID  " +
                " INNER JOIN LESSON L on T.LESSON_ID = L.ID  " +
                " WHERE  (QUESTIONS.ACTIVE = 1) AND (T.ID = ?)";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Questions questions = new Questions();
                    questions.setId(rs.getLong("ID"));
                    questions.setRownum(rs.getLong("r"));
                    questions.setQuestions(rs.getString("QUESTION"));
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    ClassRoom classRoom = new ClassRoom();
                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));
                    Topic topic = new Topic();
                    topic.setTopicName(rs.getString("NAME"));
                    topic.setLesson(lesson);
                    topic.setClassRoom(classRoom);
                    questions.setTopic(topic);
                    questionsList.add(questions);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }

        return questionsList;
    }

    public Questions getQuestionById(Long id) throws Exception {
        Questions questions = new Questions();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,QUESTIONS.ID,QUESTIONS.QUESTION, T.NAME,C2.CLASS_NUMBER," +
                " L.LESSON_NAME,C2.ID class_id,L.ID lesson_id,T.ID topic_id FROM  QUESTIONS  " +
                " INNER JOIN TOPIC T on QUESTIONS.TOPIC_ID = T.ID  " +
                " INNER JOIN CLASS C2 on T.CLASS_ID = C2.ID  " +
                " INNER JOIN LESSON L on T.LESSON_ID = L.ID  " +
                " WHERE  (QUESTIONS.ACTIVE = 1) AND (QUESTIONS.ID = ?)";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();

                while (rs.next()) {
                    questions.setId(rs.getLong("ID"));
                    questions.setRownum(rs.getLong("r"));
                    questions.setQuestions(rs.getString("QUESTION"));
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    ClassRoom classRoom = new ClassRoom();
                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));
                    Topic topic = new Topic();
                    topic.setId(rs.getLong("topic_id"));
                    topic.setTopicName(rs.getString("NAME"));
                    topic.setLesson(lesson);
                    topic.setClassRoom(classRoom);
                    questions.setTopic(topic);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var13) {
            var13.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }

        return questions;
    }

    public boolean update(Questions question, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE QUESTIONS SET QUESTION = ? , TOPIC_ID = ?  WHERE ID = ?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, question.getQuestions());
                ps.setLong(2, question.getTopic().getId());
                ps.setLong(3, id);
                ps.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, (ResultSet) null);
        }

        return result;
    }

    public boolean delete(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE QUESTIONS SET ACTIVE = ? WHERE ID = ?";

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
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, (ResultSet) null);
        }

        return result;
    }

    public List<Questions> search(String keyword) throws Exception {
        List<Questions> questionsList = new ArrayList();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r," +
                "       QUESTIONS.ID," +
                "       QUESTIONS.QUESTION," +
                "       T.NAME," +
                "       C2.CLASS_NUMBER," +
                "       L.LESSON_NAME," +
                "       C2.ID  class_id, " +
                "       L.ID   lesson_id " +
                " FROM QUESTIONS\n" +
                "         INNER JOIN TOPIC T on QUESTIONS.TOPIC_ID = T.ID " +
                "         INNER JOIN CLASS C2 on T.CLASS_ID = C2.ID " +
                "         INNER JOIN LESSON L on T.LESSON_ID = L.ID " +
                " WHERE (QUESTIONS.ACTIVE = 1)\n" +
                "  AND (LOWER(C2.CLASS_NUMBER) LIKE LOWER(?) OR " +
                " LOWER(L.LESSON_NAME) LIKE LOWER(?) OR LOWER(T.NAME) LIKE LOWER(?) OR " +
                "       LOWER(QUESTIONS.QUESTION) LIKE LOWER(?))";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                ps.setString(3, "%" + keyword + "%");
                ps.setString(4, "%" + keyword + "%");
                rs = ps.executeQuery();

                while (rs.next()) {
                    Questions questions = new Questions();
                    questions.setId(rs.getLong("ID"));
                    questions.setRownum(rs.getLong("r"));
                    questions.setQuestions(rs.getString("QUESTION"));
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    ClassRoom classRoom = new ClassRoom();
                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));
                    Topic topic = new Topic();
                    topic.setTopicName(rs.getString("NAME"));
                    topic.setLesson(lesson);
                    topic.setClassRoom(classRoom);
                    questions.setTopic(topic);
                    questionsList.add(questions);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }

        return questionsList;
    }
}
