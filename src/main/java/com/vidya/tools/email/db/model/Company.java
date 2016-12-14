package com.vidya.tools.email.db.model;

public class Company {

	private long id;
	private String name;
	private String url;
	private String domain;
	private String mxRecord;
	private String emailPattern;

	public Company(long id) {
		this.id = id;
	}
	
	public Company() {	
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getMxRecord() {
		return mxRecord;
	}

	public void setMxRecord(String mxRecord) {
		this.mxRecord = mxRecord;
	}

	public String getEmailPattern() {
		return emailPattern;
	}

	public void setEmailPattern(String emailPattern) {
		this.emailPattern = emailPattern;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		result = prime * result + ((emailPattern == null) ? 0 : emailPattern.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((mxRecord == null) ? 0 : mxRecord.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (domain == null) {
			if (other.domain != null)
				return false;
		} else if (!domain.equals(other.domain))
			return false;
		if (emailPattern == null) {
			if (other.emailPattern != null)
				return false;
		} else if (!emailPattern.equals(other.emailPattern))
			return false;
		if (id != other.id)
			return false;
		if (mxRecord == null) {
			if (other.mxRecord != null)
				return false;
		} else if (!mxRecord.equals(other.mxRecord))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", url=" + url + ", domain=" + domain + ", mxRecord=" + mxRecord
				+ ", emailPattern=" + emailPattern + "]";
	}
	
	public static class Builder {
		
		private String name;
		private String url;
		private String domain;
		private String mxRecord;
		private String emailPattern;
		
		public Builder name(String name){
			this.name = name;
			return this;
		}
		
		public Builder url(String url){
			this.url = url;
			return this;
		}
		
		public Builder domain(String domain){
			this.domain = domain;
			return this;
		}

		public Builder mxRecord(String mxRecord){
			this.mxRecord = mxRecord;
			return this;
		}

		public Builder emailPattern(String emailPattern){
			this.emailPattern = emailPattern;
			return this;
		}
		
		public Company build() {
			
			Company company = new Company();
			
			company.setName(name);
			company.setUrl(url);
			company.setDomain(domain);
			company.setMxRecord(mxRecord);
			company.setEmailPattern(emailPattern);

			return company;
		}
	}
}