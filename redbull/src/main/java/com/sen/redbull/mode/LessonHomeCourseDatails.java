package com.sen.redbull.mode;

import java.util.List;


public class LessonHomeCourseDatails {
	private List<LessonCourseDetails> lessonCourseDetails;
	private String success;
	/**
	 * @return the lessonCourseDetails
	 */
	public List<LessonCourseDetails> getLessonCourseDetails() {
		return lessonCourseDetails;
	}
	/**
	 * @param lessonCourseDetails the lessonCourseDetails to set
	 */
	public void setLessonCourseDetails(List<LessonCourseDetails> lessonCourseDetails) {
		this.lessonCourseDetails = lessonCourseDetails;
	}
	/**
	 * @return the success
	 */
	public String getSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(String success) {
		this.success = success;
	}
	
}
