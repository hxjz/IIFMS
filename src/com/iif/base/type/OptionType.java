package com.iif.base.type;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.iif.base.entity.ExpenseOptions;
import com.iif.base.entity.Options;

/**
 * 
 * @author quzile
 * 
 */
public enum OptionType {

	PROJECT {

		@Override
		public DetachedCriteria dc(Object... params) {
			DetachedCriteria dc = DetachedCriteria.forClass(null);
			dc.add(Property.forName("isDel").eq(0));
			return dc;
		}

	};

	private OptionType() {
	}

	public abstract DetachedCriteria dc(Object... params);

	public Class<? extends Options> bean() {
		return Options.class;
	}

	public Projection[] Projections() {
		return null;
	}

}
