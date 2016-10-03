package com.gft;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by amzk on 2016-09-22.
 */
public interface Node extends Iterable<Node> {

     List<Node> getChildrens();
    String getName();

}
