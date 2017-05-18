package com.iif.base.entity;

public class ExpenseOptions extends Options {

	private boolean batch;

	public ExpenseOptions() {
	}

	@Override
	public Object getKey() {
		return super.getKey();
	}

	@Override
	public void setKey(Object key) {
		super.setKey(key);
	}

	@Override
	public Object getValue() {
		return super.getValue();
	}

	@Override
	public void setValue(Object value) {
		super.setValue(value);
	}

	public boolean isBatch() {
		return batch;
	}

	public void setBatch(boolean batch) {
		this.batch = batch;
	}

}
