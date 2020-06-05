//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package az.maqa.project.dao.impl;

import az.maqa.project.dao.helper.DbHelper;
import az.maqa.project.dao.inter.AnswerDao;
import az.maqa.project.model.*;
import az.maqa.project.utility.Utility;
import az.maqa.project.model.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AnswerDaoImpl implements AnswerDao {
    public List<Answer> getAnswerList() throws Exception {
        List<Answer> answerList = new ArrayList();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ANSWER.ID,ANSWER.ANSWER,answerStatus.ID answerstatus_id,answerStatus.STATUS_NAME, " +
                " Q.ID question_id, Q.QUESTION, T.ID  topic_id, T.NAME  topic_name, C2.ID class_id, C2.CLASS_NUMBER, " +
                " L.ID lesson_id,L.LESSON_NAME FROM ANSWER          " +
                " INNER JOIN QUESTIONS Q on ANSWER.QUESTION_ID = Q.ID          " +
                " INNER JOIN ANSWER_STATUS answerStatus on ANSWER.ANSWER_STATUS_ID = answerStatus.ID          " +
                " INNER JOIN TOPIC T on Q.TOPIC_ID = T.ID          " +
                " INNER JOIN LESSON L on T.LESSON_ID = L.ID          " +
                " INNER JOIN CLASS C2 on T.CLASS_ID = C2.ID  " +
                " WHERE ANSWER.ACTIVE = 1";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Answer answer = new Answer();
                    ClassRoom classRoom = new ClassRoom();
                    Lesson lesson = new Lesson();
                    Questions questions = new Questions();
                    Topic topic = new Topic();
                    AnswerStatus answerStatus = new AnswerStatus();
                    answer.setId(rs.getLong("ID"));
                    answer.setAnswer(rs.getString("ANSWER"));
                    answerStatus.setId(rs.getLong("answerstatus_id"));
                    answerStatus.setStatusName(rs.getString("STATUS_NAME"));
                    questions.setId(rs.getLong("question_id"));
                    questions.setQuestions(rs.getString("QUESTION"));
                    topic.setId(rs.getLong("topic_id"));
                    topic.setTopicName(rs.getString("topic_name"));
                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));
                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    topic.setClassRoom(classRoom);
                    topic.setLesson(lesson);
                    questions.setTopic(topic);
                    answer.setAnswerStatus(answerStatus);
                    answer.setQuestions(questions);
                    answerList.add(answer);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }

        return answerList;
    }

    public boolean add(Answer answer) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO ANSWER(ID, ANSWER, QUESTION_ID, ANSWER_STATUS_ID) " +
                " VALUES (ANSWER_SEQ.nextval,?,?,?)";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, answer.getAnswer());
                ps.setLong(2, answer.getQuestions().getId());
                ps.setLong(3, answer.getAnswerStatus().getId());
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

    public Answer getAnswerById(Long id) throws Exception {
        Answer answer = new Answer();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ANSWER.ID, ANSWER.ANSWER, answerStatus.ID answerstatus_id, answerStatus.STATUS_NAME," +
                "  Q.ID question_id, Q.QUESTION,  T.ID  topic_id,  T.NAME  topic_name,   C2.ID  class_id, " +
                " C2.CLASS_NUMBER, L.ID lesson_id, L.LESSON_NAME FROM ANSWER  " +
                " INNER JOIN QUESTIONS Q on ANSWER.QUESTION_ID = Q.ID  " +
                " INNER JOIN ANSWER_STATUS answerStatus on ANSWER.ANSWER_STATUS_ID = answerStatus.ID" +
                " INNER JOIN TOPIC T on Q.TOPIC_ID = T.ID " +
                " INNER JOIN LESSON L on T.LESSON_ID = L.ID  " +
                " INNER JOIN CLASS C2 on T.CLASS_ID = C2.ID  " +
                " WHERE (ANSWER.ACTIVE = 1) AND (ANSWER.ID = ?)";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();

                while (rs.next()) {
                    ClassRoom classRoom = new ClassRoom();
                    Lesson lesson = new Lesson();
                    Questions questions = new Questions();
                    Topic topic = new Topic();
                    AnswerStatus answerStatus = new AnswerStatus();
                    answer.setId(rs.getLong("ID"));
                    answer.setAnswer(rs.getString("ANSWER"));
                    answerStatus.setId(rs.getLong("answerstatus_id"));
                    answerStatus.setStatusName(rs.getString("STATUS_NAME"));
                    questions.setId(rs.getLong("question_id"));
                    questions.setQuestions(rs.getString("QUESTION"));
                    topic.setId(rs.getLong("topic_id"));
                    topic.setTopicName(rs.getString("topic_name"));
                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));
                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    topic.setClassRoom(classRoom);
                    topic.setLesson(lesson);
                    questions.setTopic(topic);
                    answer.setAnswerStatus(answerStatus);
                    answer.setQuestions(questions);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }

        return answer;
    }

    public boolean update(Answer answer, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE ANSWER SET ANSWER=?,QUESTION_ID = ?, ANSWER_STATUS_ID = ?  WHERE ID = ?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, answer.getAnswer());
                ps.setLong(2, answer.getQuestions().getId());
                ps.setLong(3, answer.getAnswerStatus().getId());
                ps.setLong(4, id);
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
        String sql = "UPDATE ANSWER SET ACTIVE = ? WHERE ID = ?";

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

    public List<Answer> search(String keyword) throws Exception {
        List<Answer> answerList = new ArrayList();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ANSWER.ID, ANSWER.ANSWER, answerStatus.ID answerstatus_id, answerStatus.STATUS_NAME, " +
                " Q.ID question_id, Q.QUESTION, T.ID  topic_id, T.NAME  topic_name, C2.ID class_id, " +
                " C2.CLASS_NUMBER,  L.ID  lesson_id,   L.LESSON_NAME FROM ANSWER " +
                " INNER JOIN QUESTIONS Q on ANSWER.QUESTION_ID = Q.ID " +
                " INNER JOIN ANSWER_STATUS answerStatus on ANSWER.ANSWER_STATUS_ID = answerStatus.ID " +
                " INNER JOIN TOPIC T on Q.TOPIC_ID = T.ID " +
                " INNER JOIN LESSON L on T.LESSON_ID = L.ID " +
                " INNER JOIN CLASS C2 on T.CLASS_ID = C2.ID " +
                " WHERE (ANSWER.ACTIVE = 1)  AND (LOWER(C2.CLASS_NUMBER) LIKE LOWER(?) OR " +
                " LOWER(L.LESSON_NAME) LIKE LOWER(?) OR LOWER(T.NAME) LIKE LOWER(?) OR " +
                " LOWER(Q.QUESTION) LIKE LOWER(?) OR LOWER(ANSWER.ANSWER) LIKE LOWER(?) OR " +
                " LOWER(answerStatus.STATUS_NAME) LIKE LOWER(?))";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                ps.setString(3, "%" + keyword + "%");
                ps.setString(4, "%" + keyword + "%");
                ps.setString(5, "%" + keyword + "%");
                ps.setString(6, "%" + keyword + "%");
                rs = ps.executeQuery();

                while (rs.next()) {
                    Answer answer = new Answer();
                    ClassRoom classRoom = new ClassRoom();
                    Lesson lesson = new Lesson();
                    Questions questions = new Questions();
                    Topic topic = new Topic();
                    AnswerStatus answerStatus = new AnswerStatus();
                    answer.setId(rs.getLong("ID"));
                    answer.setAnswer(rs.getString("ANSWER"));
                    answerStatus.setId(rs.getLong("answerstatus_id"));
                    answerStatus.setStatusName(rs.getString("STATUS_NAME"));
                    questions.setId(rs.getLong("question_id"));
                    questions.setQuestions(rs.getString("QUESTION"));
                    topic.setId(rs.getLong("topic_id"));
                    topic.setTopicName(rs.getString("topic_name"));
                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));
                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    topic.setClassRoom(classRoom);
                    topic.setLesson(lesson);
                    questions.setTopic(topic);
                    answer.setAnswerStatus(answerStatus);
                    answer.setQuestions(questions);
                    answerList.add(answer);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }

        return answerList;
    }
}
