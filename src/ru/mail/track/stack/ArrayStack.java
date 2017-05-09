package ru.mail.track.stack;

import java.util.Collection;

/**
 *
 */
public class ArrayStack<E> implements Stack<E> {
    private Object[] elements;
    private int capacity;
    private int size;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        size = 0;
        elements = new Object[capacity];
    }

    @Override
    public void push(E element) throws StackException {
        if (isFull()) {
            throw new StackException("Stack overflow");
        }
        elements[size] = element;
        size++;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public E pop() throws StackException {
        if ( size == 0){
            throw new StackException("Stack is empty!");
        }
        return (E) elements[size--];
    }

    @Override
    public E peek() {
        try{
            return this.pop();
        }catch (StackException e) {
            return null;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void pushAll(Collection<E> src) throws StackException {
        for ( E element : src){
            this.push(element);
        }
    }

    @Override
    public void popAll(Collection<E> dst) throws StackException {
        E tmp;
        while( (tmp = peek())!=null ){
            dst.add(tmp);
        }
    }
}
