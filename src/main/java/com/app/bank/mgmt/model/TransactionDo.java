package com.app.bank.mgmt.model;

import java.io.Serializable;
import java.util.Date;

	public class TransactionDo implements Serializable {


		@Override
		public String toString() {
			return "TransactionDo [transactionId=" + transactionId + ", transactionType=" + transactionType
					+ ", amount=" + amount + ", date=" + date + "]";
		}

		public long getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(long transactionId) {
			this.transactionId = transactionId;
		}

		public TransactionType getTransactionType() {
			return transactionType;
		}

		public void setTransactionType(TransactionType transactionType) {
			this.transactionType = transactionType;
		}

		public Double getAmount() {
			return amount;
		}

		public void setAmount(Double amount) {
			this.amount = amount;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		private static final long serialVersionUID = 1L;

		private long transactionId;
		
		private TransactionType transactionType;

	    private Double amount;

	    private Date date;

		public TransactionDo(long transactionId, TransactionType transactionType, Double amount, Date date) {
			super();
			this.transactionId = transactionId;
			this.transactionType = transactionType;
			this.amount = amount;
			this.date = date;
		}


}
