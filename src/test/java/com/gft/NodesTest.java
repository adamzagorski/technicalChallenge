package com.gft;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by amzk on 2016-09-22.
 */
public final class NodesTest {

    @Test
    public void shouldReturnEmptyNode() {
        NodeImpl root = new NodeImpl(null);

        Iterator<Node> convert = Nodes.convert(root).iterator();

        assertThat(convert.hasNext(), is(equalTo(false)));
    }

    @Test
    public void shouldReturnNodeWithChildren() {
        NodeImpl child = new NodeImpl(null);
        NodeImpl root = new NodeImpl(child);

        Iterator<Node> convert = Nodes.convert(root).iterator();

        assertThat(convert.hasNext(), is(equalTo(true)));
    }


}