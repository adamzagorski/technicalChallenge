package com.gft;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;


/**
 * Created by amzk on 2016-09-22.
 */
public final class NodesTest {

    @Test
    public void shouldReturnEmptyNode() {
        NodeImpl root = new NodeImpl(null);

        Iterator<Node> convert = Nodes.convertToIterable(root).iterator();

        assertThat(convert.hasNext(), is(equalTo(false)));
    }

    @Test
    public void shouldReturnNodeWithChildren() {
        NodeImpl child = new NodeImpl(null);
        NodeImpl root = new NodeImpl("root", child);

        Iterator<Node> convert = Nodes.convertToIterable(root).iterator();

        assertThat(convert.hasNext(), is(equalTo(true)));
    }

    @Test
    public void shouldReturnChildChildNode() {
        NodeImpl childChild = new NodeImpl(null);
        NodeImpl child = new NodeImpl("child1", childChild);
        NodeImpl root = new NodeImpl("root", child);

        Iterator<Node> rootIterator = Nodes.convertToIterable(root).iterator();
        Iterator<Node> childIterator = Nodes.convertToIterable(rootIterator.next()).iterator();
        Iterator<Node> childChildIterator = Nodes.convertToIterable(childIterator.next()).iterator();
        assertThat(rootIterator.hasNext(), is(equalTo(false)));
        assertThat(childIterator.hasNext(), is(equalTo(false)));
        assertThat(childChildIterator.hasNext(), is(equalTo(false)));

    }

    @Test
    public void shouldReturnChildChild() {
        NodeImpl child11 = new NodeImpl("child11", null);
        NodeImpl child12 = new NodeImpl("child21", null);
        Node[] child1Array = new NodeImpl[2];
        Arrays.fill(child1Array, child11);
        Arrays.fill(child1Array, child12);
        NodeImpl child1 = new NodeImpl("child1", child1Array);
        NodeImpl child21 = new NodeImpl("child21", null);
        NodeImpl child22 = new NodeImpl("child22", null);
        Node[] child2Array = new NodeImpl[2];
        Arrays.fill(child2Array, child21);
        Arrays.fill(child2Array, child22);
        NodeImpl child2 = new NodeImpl("child2", child2Array);
        Node[] rootArray = new NodeImpl[2];
        Arrays.fill(rootArray, child1);
        Arrays.fill(rootArray, child2);
        NodeImpl root = new NodeImpl("root", rootArray);

        Iterator<Node> rootIterator = Nodes.convertToIterable(root).iterator();

        Iterator<Node> child1Iterator = Nodes.convertToIterable(rootIterator.next()).iterator();
        Iterator<Node> child11Iterator = Nodes.convertToIterable(child1Iterator.next()).iterator();
        Iterator<Node> child12Iterator = Nodes.convertToIterable(child1Iterator.next()).iterator();

        Iterator<Node> child2Iterator = Nodes.convertToIterable(rootIterator.next()).iterator();
        Iterator<Node> child21Iterator = Nodes.convertToIterable(child2Iterator.next()).iterator();
        Iterator<Node> child22Iterator = Nodes.convertToIterable(child2Iterator.next()).iterator();


        assertThat(rootIterator.hasNext(), is(equalTo(false)));
        assertThat(child2Iterator.hasNext(), is(equalTo(false)));
        assertThat(child11Iterator.hasNext(), is(equalTo(false)));

    }

    @Test
    public void shouldConvertToFlatStructure() {
        // given
        NodeImpl child11 = new NodeImpl("child11", null);
        NodeImpl child12 = new NodeImpl("child12", null);
        Node[] child1Array = new NodeImpl[2];
        child1Array[0] = child11;
        child1Array[1] = child12;
        NodeImpl child1 = new NodeImpl("child1", child1Array);
        NodeImpl child21 = new NodeImpl("child21", null);
        NodeImpl child22 = new NodeImpl("child22", null);
        Node[] child2Array = new NodeImpl[2];
        child2Array[0] = child21;
        child2Array[1] = child22;
        NodeImpl child2 = new NodeImpl("child2", child2Array);
        Node[] rootArray = new NodeImpl[2];
        rootArray[0] = child1;
        rootArray[1] = child2;
        NodeImpl root = new NodeImpl("root", rootArray);

        // when
        Iterable<Node> iterable = Nodes.convertToIterable(root);
        Iterator<Node> iterator = iterable.iterator();

        //then
        assertThat(iterator).containsOnly(child1,child11,child12,child2,child21,child22);
    }
}