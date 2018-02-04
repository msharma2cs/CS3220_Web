package models;

public class ArticlesList {

	int id;
	String title;
	String link;
	int votes;
	
	public ArticlesList(int id, String title, String link, int votes) {
		this.id = id;
		this.title = title;
		this.link = link;
		this.votes = votes;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	
	
}