package com.sen.redbull.mode;

import java.io.Serializable;

public class LessonCourseDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String hour;//课程标准学时
	private String le_name;//课程名称
	private String lescore;//课程标准学分
	private String score;//已获取学分
	private String studyplan;// 学习进度
	private String studytime;// 学习总时长
	private String traincomment;//课程主要内容	
	private String whether;//是否选课S
	/**
	 * @return the studytime
	 */
	public String getStudytime() {
		return studytime;
	}
	/**
	 * @param studytime the studytime to set
	 */
	public void setStudytime(String studytime) {
		this.studytime = studytime;
	}
	/**
	 * @return the score
	 */
	public String getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(String score) {
		this.score = score;
	}
	/**
	 * @return the studyplan
	 */
	public String getStudyplan() {
		return studyplan;
	}
	/**
	 * @param studyplan the studyplan to set
	 */
	public void setStudyplan(String studyplan) {
		this.studyplan = studyplan;
	}
	/**
	 * @return the traincomment
	 */
	public String getTraincomment() {
		return traincomment;
	}
	/**
	 * @param traincomment the traincomment to set
	 */
	public void setTraincomment(String traincomment) {
		this.traincomment = traincomment;
	}
	/**
	 * @return the hour
	 */
	public String getHour() {
		return hour;
	}
	/**
	 * @param hour the hour to set
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}
	/**
	 * @return the le_name
	 */
	public String getLe_name() {
		return le_name;
	}
	/**
	 * @param le_name the le_name to set
	 */
	public void setLe_name(String le_name) {
		this.le_name = le_name;
	}
	/**
	 * @return the lescore
	 */
	public String getLescore() {
		return lescore;
	}
	/**
	 * @param lescore the lescore to set
	 */
	public void setLescore(String lescore) {
		this.lescore = lescore;
	}
	/**
	 * @return the whether
	 */
	public String getWhether() {
		return whether;
	}
	/**
	 * @param whether the whether to set
	 */
	public void setWhether(String whether) {
		this.whether = whether;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LessonCourseDetails [hour=" + hour + ", le_name=" + le_name
				+ ", lescore=" + lescore + ", score=" + score + ", studyplan="
				+ studyplan + ", studytime=" + studytime + ", traincomment="
				+ traincomment + ", whether=" + whether + "]";
	}
	
	
}
