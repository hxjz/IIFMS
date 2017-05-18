package com.iif.base.pattern.decorate;

/**
 * 抽象装饰角色
 * 
 * @author LiuM
 * 
 */
public abstract class OperateDecorator<T> implements OperateComponent<T> {

	private OperateComponent<T> operate;

	public OperateDecorator(OperateComponent<T> operate) {
		super();
		this.operate = operate;
	}

	@Override
	public T operation() {
		return operate.operation();
	}

}
