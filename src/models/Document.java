package models;

public class Document {
	
	
	public String getDocID() {
		return DocID;
	}
	public void setDocID(String docID) {
		DocID = docID;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getPublishingDate() {
		return PublishingDate;
	}
	public void setPublishingDate(String publishingDate) {
		PublishingDate = publishingDate;
	}
	public String getPublisherID() {
		return PublisherID;
	}
	public void setPublisherID(String publisherID) {
		PublisherID = publisherID;
	}
	
	public Document() {
		
	}
	
	public Document(String id, String name, String publishDate,String publisherId) {
		this.DocID = id;
		this.Title = name;
		this.PublishingDate = publishDate;
		this.PublisherID = publisherId;
	}
	
	public Document(String id, String name) {
		this.DocID = id;
		this.Title = name;
	}
	
	public Document(String id, String copy, String name) {
		this.DocID = id;
		this.copyNumber = copy;
		this.Title = name;
	}
	
	
	//rk
	public Document(String ResNo, String ReaderID, String DocID, String copyNumber, String LibID, String Dtime) {
		this.ResNo = ResNo;
		this.ReaderID = ReaderID;
		this.DocID = DocID;
		this.copyNumber = copyNumber;
		this.LibID = LibID;
		this.Dtime = Dtime;	
	}
	
	//rk
	public Document(String BorNo, String ReaderID, String DocID, String copyNumber, String Dtime) {
		this.BorNo = BorNo;
		this.ReaderID = ReaderID;
		this.DocID = DocID;
		this.copyNumber = copyNumber;
		this.Dtime = Dtime;	
	}
	
	
	
	
	public void setReserved(String resNum) {
		if (resNum == null) {
			this.reserved = false;
		} else {
			this.reserved = true;
		}		
	}
	
	public boolean isReserved() {
		return this.reserved;
	}
	
	String DocID;
	String Title;
	String PublishingDate;
	String PublisherID;
	String copyNumber;
	String TimesBorrowed;
	String BorNo;
	String ReaderID;
	String Dtime;
	String LibID;
	String ResNo;
	String BorDate;
	String RetDtime;
	
	public String getRetDtime() {
		return RetDtime;
	}
	public void setRetDtime(String retDtime) {
		RetDtime = retDtime;
	}
	public String getBorDate() {
		return BorDate;
	}
	public void setBorDate(String borDate) {
		BorDate = borDate;
	}
	public String getBorNo() {
		return BorNo;
	}
	public void setBorNo(String borNo) {
		BorNo = borNo;
	}
	public String getReaderID() {
		return ReaderID;
	}
	public void setReaderID(String readerID) {
		ReaderID = readerID;
	}
	public String getDtime() {
		return Dtime;
	}
	public void setDtime(String dtime) {
		Dtime = dtime;
	}
	public String getLibID() {
		return LibID;
	}
	public void setLibID(String libID) {
		LibID = libID;
	}
	public String getResNo() {
		return ResNo;
	}
	public void setResNo(String resNo) {
		ResNo = resNo;
	}
	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}
	
	
	public String getTimesBorrowed() {
		return TimesBorrowed;
	}
	public void setTimesBorrowed(String timesBorrowed) {
		TimesBorrowed = timesBorrowed;
	}
	public String getCopyNumber() {
		return copyNumber;
	}
	public void setCopyNumber(String copyNumber) {
		this.copyNumber = copyNumber;
	}

	boolean reserved;

}
