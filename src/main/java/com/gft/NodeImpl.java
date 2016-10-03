package com.gft;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by amzk on 2016-09-22.
 */

public final class NodeImpl implements Node {

    String name;
    List<Node> childrenList;


    public NodeImpl(String name,Node... childrenList) {
        this.name=name;
        if (childrenList != null ){
            this.childrenList = Arrays.asList(childrenList);
        }
        else{
            this.childrenList = new ArrayList<Node>();
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public List<Node> getChildrens() {
        return this.childrenList;
    }

    public Iterator<Node> iterator() {
        return new NodeIterator(this.childrenList);
    }

    static class NodeIterator implements Iterator<Node>{

        List<Node> childrens;
        public NodeIterator() {
        }
        public NodeIterator(List<Node> childrens) {
            if (childrens != null ){
                this.childrens = childrens;
            }
            else{
                this.childrens = new ArrayList<>();
            }
        }

        @Override
        public boolean hasNext() {
            if (this.childrens.isEmpty()){
                return false;
            }else{
                return true;
            }
        }
        @Override
        public NodeImpl next() {
            if (hasNext()) {
                return (NodeImpl) childrens.iterator().next();
            }else{
                return null;
            }
        }



    }



}
