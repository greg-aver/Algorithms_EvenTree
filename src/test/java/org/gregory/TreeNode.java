package org.gregory;

public class TreeNode<T> {
    public T Value;
    public TreeNode(T val)
    {
        Value = val;
    }

    public T getValue() {
        return Value;
    }
}
