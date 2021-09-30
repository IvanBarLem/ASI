package es.udc.asiproject.backend.model.entities;

public class QueryConstructor {
    private boolean isFirstCondition;
    private String query;

    public QueryConstructor(String selectFromTable) {
	this.isFirstCondition = true;
	this.query = selectFromTable;
    }

    public void addCondition(String condition) {
	if (this.isFirstCondition) {
	    this.query = this.query + " WHERE ";
	    this.isFirstCondition = false;
	} else {
	    this.query = this.query + " AND ";
	}
	this.query = this.query + condition;
    }

    public String getQuery() {
	return this.query;
    }

}
