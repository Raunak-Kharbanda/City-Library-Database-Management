package models;

public class Reader {
	
	public String getReaderID() {
		return readerID;
	}
	public void setReaderID(String readerID) {
		this.readerID = readerID;
	}
	public String getReaderType() {
		return readerType;
	}
	public void setReaderType(String readerType) {
		this.readerType = readerType;
	}
	public String getReaderName() {
		return readerName;
	}
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	public String getReaderAddress() {
		return readerAddress;
	}
	public void setReaderAddress(String readerAddress) {
		this.readerAddress = readerAddress;
	}
	public String getFine() {
		return fine;
	}
	public void setFine(String fine) {
		this.fine = fine;
	}
	public String getOwnedDocs() {
		return ownedDocs;
	}
	public void setOwnedDocs(String ownedDocs) {
		this.ownedDocs = ownedDocs;
	}
	public String getBorrowNo() {
		return BorrowNo;
	}
	public void setBorrowNo(String borrowNo) {
		BorrowNo = borrowNo;
	}
	public String getDocId() {
		return DocId;
	}
	public void setDocId(String docId) {
		DocId = docId;
	}
	public String getCopyNo() {
		return CopyNo;
	}
	public void setCopyNo(String copyNo) {
		CopyNo = copyNo;
	}
	public String getBorrDate() {
		return BorrDate;
	}
	public void setBorrDate(String borrDate) {
		BorrDate = borrDate;
	}
	public String getRetDate() {
		return RetDate;
	}
	public void setRetDate(String retDate) {
		RetDate = retDate;
	}

	public Reader(String borNo, String docId, String copyNo, String borDate, String retDate, String fine) {
		
		this.BorrowNo = borNo;
		this.DocId = docId;
		this.CopyNo = copyNo;
		this.BorrDate = borDate;
		this.RetDate = retDate;
		this.fine = fine;
		
	}
	public Reader(String id, String type, String name, String add) {
		this.readerID = id;
		this.readerType = type;
		this.readerName =  name;
		this.readerAddress =  add;
	}
	
	public Reader(String id, String fines) {
		this.readerID = id;
		this.fine = fines;
	
	}
	public Reader(String id, String numDocs, String name) {
		this.readerID = id;
		this.ownedDocs = numDocs;
		this.readerName= name;
	
	}
	
	
	String readerID;
	String readerType;
	String readerName;
	String readerAddress;
	String fine;
	String ownedDocs;
	String BorrowNo;
	String DocId;
	String CopyNo;
	String BorrDate;
	String RetDate;
	

}
