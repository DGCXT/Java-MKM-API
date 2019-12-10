package entities;

import java.util.ArrayList;
import java.util.List;

import entities.parameters.Grade;

public class Evaluation 
{
	//Evaluation values default to very good (1)
	private int evaluationGrade = Grade.VERY_GOOD.getValue();
	private int itemDescription = Grade.VERY_GOOD.getValue();
	private int packaging = Grade.VERY_GOOD.getValue();
	private int speed = Grade.VERY_GOOD.getValue();
	private String comment;
	private List<String> complaint = new ArrayList<String>();
	
	public static Evaluation makeVeryGoodEvaluation()
	{
		return makeEvaluation("", Grade.VERY_GOOD);
	}
	
	public static Evaluation makeVeryGoodEvaluation(String comment)
	{
		return makeEvaluation(comment, Grade.VERY_GOOD);
	}
	
	public static Evaluation makeGoodEvaluation()
	{
		return makeEvaluation("", Grade.GOOD);
	}
	
	public static Evaluation makeGoodEvaluation(String comment)
	{
		return makeEvaluation(comment, Grade.GOOD);
	}
	
	public static Evaluation makeNeutralEvaluation()
	{
		return makeEvaluation("", Grade.NEUTRAL);
	}
	
	public static Evaluation makeNeutralEvaluation(String comment)
	{
		return makeEvaluation(comment, Grade.NEUTRAL);
	}
	
	public static Evaluation makeBadEvaluation()
	{
		return makeEvaluation("", Grade.BAD);
	}
	
	public static Evaluation makeBadEvaluation(String comment)
	{
		return makeEvaluation(comment, Grade.BAD);
	}
	
	public static Evaluation makeNAEvaluation()
	{
		return makeEvaluation("", Grade.NA);
	}
	
	public static Evaluation makeNAEvaluation(String comment)
	{
		return makeEvaluation(comment, Grade.NA);
	}
	
	private static Evaluation makeEvaluation(String comment, Grade grade)
	{
		Evaluation evaluation = new Evaluation();
		evaluation.setComment(comment);
		evaluation.setItemDescription(grade.getValue());
		evaluation.setEvaluationGrade(grade.getValue());
		evaluation.setPackaging(grade.getValue());
		evaluation.setSpeed(grade.getValue());
		return evaluation;
	}
	
	public int getEvaluationGrade() 
	{
		return evaluationGrade;
	}
	
	public void setEvaluationGrade(int evaluationGrade)
	{
		this.evaluationGrade = evaluationGrade;
	}
	
	public int getItemDescription()
	{
		return itemDescription;
	}
	
	public void setItemDescription(int itemDescription)
	{
		this.itemDescription = itemDescription;
	}
	
	public int getPackaging()
	{
		return packaging;
	}
	
	public void setPackaging(int packaging)
	{
		this.packaging = packaging;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public void setSpeed(int speed) 
	{
		this.speed = speed;
	}
	
	public String getComment() 
	{
		return comment;
	}
	
	public void setComment(String comment) 
	{
		this.comment = comment;
	}
	
	public List<String> getComplaints() 
	{
		return complaint;
	}
	
	public void addComplaint(String complaint) 
	{
		this.complaint.add(complaint);
	}
}
