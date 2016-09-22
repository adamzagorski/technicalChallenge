package com.gft;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by amzk on 2016-09-22.
 */
public final class NodeImpl implements Node
{

    List<Node> childrenList;

    public NodeImpl(Node... childrenList) {
        this.childrenList = Arrays.asList(childrenList);
    }

    public Iterator<Node> iterator() {
        return childrenList.iterator();
    }
}
