package org.gregory;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

class SimpleTreeTest {

    @Test
    void evenTrees_NoEdgeForDelete() {
        SimpleTree<Integer> tree = new SimpleTree<>(5);
        tree.AddVertex(new TreeNode<Integer>(0));
        tree.AddVertex(new TreeNode<Integer>(1));
        tree.AddVertex(new TreeNode<Integer>(2));
        tree.AddEdge(0, 1);
        tree.AddEdge(0, 2);
        assertThat(tree.EvenTrees(), is(empty()));
    }

    @Test
    void evenTrees_1EdgeForDelete() {
        SimpleTree<Integer> tree = new SimpleTree<>(5);
        tree.AddVertex(new TreeNode<Integer>(0));
        tree.AddVertex(new TreeNode<Integer>(1));
        tree.AddVertex(new TreeNode<Integer>(2));
        tree.AddVertex(new TreeNode<Integer>(3));
        tree.AddEdge(0, 1);
        tree.AddEdge(1, 2);
        tree.AddEdge(3, 2);
        assertThat(tree.EvenTrees(), is(List.of(1, 2)));
    }

    @Test
    void evenTrees_NoEdgeForDelete_4Nodes() {
        SimpleTree<Integer> tree = new SimpleTree<>(5);
        tree.AddVertex(new TreeNode<Integer>(0));
        tree.AddVertex(new TreeNode<Integer>(1));
        tree.AddVertex(new TreeNode<Integer>(2));
        tree.AddVertex(new TreeNode<Integer>(3));
        tree.AddEdge(0, 1);
        tree.AddEdge(1, 2);
        tree.AddEdge(3, 1);
        assertThat(tree.EvenTrees(), is(empty()));
    }

    @Test
    void evenTrees_2EdgeForDelete_10Nodes() {
        SimpleTree<Integer> tree = new SimpleTree<>(12);
        tree.AddVertex(new TreeNode<Integer>(1));
        tree.AddVertex(new TreeNode<Integer>(2));
        tree.AddVertex(new TreeNode<Integer>(3));
        tree.AddVertex(new TreeNode<Integer>(4));
        tree.AddVertex(new TreeNode<Integer>(5));
        tree.AddVertex(new TreeNode<Integer>(6));
        tree.AddVertex(new TreeNode<Integer>(7));
        tree.AddVertex(new TreeNode<Integer>(8));
        tree.AddVertex(new TreeNode<Integer>(9));
        tree.AddVertex(new TreeNode<Integer>(10));
        tree.AddEdge(2, 0);
        tree.AddEdge(0, 1);
        tree.AddEdge(5, 0);
        tree.AddEdge(1, 6);
        tree.AddEdge(1, 4);
        tree.AddEdge(2, 3);
        tree.AddEdge(5, 7);
        tree.AddEdge(7, 8);
        tree.AddEdge(7, 9);
        assertThat(tree.EvenTrees(), is(List.of(1, 3, 1, 6)));
    }

    @Test
    void counterVertex_4Vertex() {
        SimpleTree<Integer> tree = new SimpleTree<>(5);
        tree.AddVertex(new TreeNode<Integer>(0));
        tree.AddVertex(new TreeNode<Integer>(1));
        tree.AddVertex(new TreeNode<Integer>(2));
        tree.AddVertex(new TreeNode<Integer>(3));
        tree.AddEdge(0, 1);
        tree.AddEdge(1, 2);
        tree.AddEdge(3, 1);
        try {
            Method counterVertexMethod = tree.getClass().getDeclaredMethod("counterVertex", int.class);
            counterVertexMethod.setAccessible(true);
            counterVertexMethod.invoke(tree, 0);
            assertThat(counterVertexMethod.invoke(tree, 0), is(4));
            assertThat(counterVertexMethod.invoke(tree, 1), is(3));
            assertThat(counterVertexMethod.invoke(tree, 2), is(1));
            assertThat(counterVertexMethod.invoke(tree, 3), is(1));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    void counterVertex_10Vertex() {
        SimpleTree<Integer> tree = new SimpleTree<>(12);
        tree.AddVertex(new TreeNode<Integer>(1));
        tree.AddVertex(new TreeNode<Integer>(2));
        tree.AddVertex(new TreeNode<Integer>(3));
        tree.AddVertex(new TreeNode<Integer>(4));
        tree.AddVertex(new TreeNode<Integer>(5));
        tree.AddVertex(new TreeNode<Integer>(6));
        tree.AddVertex(new TreeNode<Integer>(7));
        tree.AddVertex(new TreeNode<Integer>(8));
        tree.AddVertex(new TreeNode<Integer>(9));
        tree.AddVertex(new TreeNode<Integer>(10));
        tree.AddEdge(2, 0);
        tree.AddEdge(0, 1);
        tree.AddEdge(5, 0);
        tree.AddEdge(1, 6);
        tree.AddEdge(1, 4);
        tree.AddEdge(2, 3);
        tree.AddEdge(5, 7);
        tree.AddEdge(7, 8);
        tree.AddEdge(7, 9);
        try {
            Method counterVertexMethod = tree.getClass().getDeclaredMethod("counterVertex", int.class);
            counterVertexMethod.setAccessible(true);

            assertThat(counterVertexMethod.invoke(tree, 0), is(10));
            assertThat(counterVertexMethod.invoke(tree, 1), is(3));
            assertThat(counterVertexMethod.invoke(tree, 2), is(2));
            assertThat(counterVertexMethod.invoke(tree, 3), is(1));
            assertThat(counterVertexMethod.invoke(tree, 4), is(1));
            assertThat(counterVertexMethod.invoke(tree, 5), is(4));
            assertThat(counterVertexMethod.invoke(tree, 6), is(1));
            assertThat(counterVertexMethod.invoke(tree, 7), is(3));
            assertThat(counterVertexMethod.invoke(tree, 8), is(1));
            assertThat(counterVertexMethod.invoke(tree, 9), is(1));

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}