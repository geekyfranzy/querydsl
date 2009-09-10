/*
 * Copyright (c) 2009 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query.types.expr;

import com.mysema.query.types.Order;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.operation.OBoolean;
import com.mysema.query.types.operation.ONumber;
import com.mysema.query.types.operation.OString;
import com.mysema.query.types.operation.Ops;


/**
 * EComparableBase represents comparable expressions
 * 
 * @author tiwe
 * 
 * @param <D> Java type
 * @see java.lang.Comparable
 */
@SuppressWarnings("unchecked")
public abstract class EComparableBase<D extends Comparable> extends Expr<D> {

    private volatile OrderSpecifier<D> asc, desc;
    
    private volatile EString stringCast;

    public EComparableBase(Class<? extends D> type) {
        super(type);
    }
      

    /**
     * Get an OrderSpecifier for ascending order of this expression
     * 
     * @return
     */
    public final OrderSpecifier<D> asc() {
        if (asc == null){
            asc = new OrderSpecifier<D>(Order.ASC, this);
        }            
        return asc;
    }
   
    /**
     * Create a <code>first &lt; this &lt; second</code> expression
     * 
     * @param first
     * @param second
     * @return
     */
    public final EBoolean between(D first, D second) {
        return OBoolean.create(Ops.BETWEEN, this, ExprConst.create(first), ExprConst.create(second));
    }

    /**
     * Create a <code>first &lt; this &lt; second</code> expression
     * 
     * @param first
     * @param second
     * @return
     */
    public final EBoolean between(Expr<D> first, Expr<D> second) {
        return OBoolean.create(Ops.BETWEEN, this, first, second);
    }

    
    /**
     * Create a cast expression to the given numeric type
     * 
     * @param <A>
     * @param type
     * @return
     */
    public <A extends Number & Comparable<? super A>> ENumber<A> castToNum(Class<A> type) {
        return ONumber.create(type, Ops.NUMCAST, this, ExprConst.create(type));
    }

    /**
     * Get an OrderSpecifier for descending order of this expression
     * 
     * @return
     */
    public final OrderSpecifier<D> desc() {
        if (desc == null){
            desc = new OrderSpecifier<D>(Order.DESC, this);
        }            
        return desc;
    }

    /**
     * @param first
     * @param second
     * @return
     */
    public final EBoolean notBetween(D first, D second) {
        return between(first, second).not();
    }

    /**
     * @param first
     * @param second
     * @return
     */
    public final EBoolean notBetween(Expr<D> first, Expr<D> second) {
        return between(first, second).not();
    }

    /**
     * Get a cast to String expression 
     * 
     * @see     java.lang.Object#toString()
     * @return
     */
    public EString stringValue() {
        if (stringCast == null){
            stringCast = OString.create(Ops.STRING_CAST, this);
        }            
        return stringCast;
    }

}