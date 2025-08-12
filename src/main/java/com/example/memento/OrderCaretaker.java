// File: OrderCaretaker.java
package com.example.memento;

import java.util.LinkedList;

public class OrderCaretaker {
    private static final int MAX_DRAFTS = 10;
    private final LinkedList<OrderMemento> drafts = new LinkedList<>();

    private static OrderCaretaker instance;

    private OrderCaretaker() {} // private constructor (Singleton)

    public static OrderCaretaker getInstance() {
        if (instance == null) {
            instance = new OrderCaretaker();
        }
        return instance;
    }

    public void saveDraft(OrderMemento draft) {
        if (drafts.size() == MAX_DRAFTS) {
            drafts.removeFirst(); // drop oldest
        }
        drafts.addLast(draft);
    }

    public OrderMemento getLatestDraft() {
        if (drafts.isEmpty()) return null;
        return drafts.getLast();
    }

    public LinkedList<OrderMemento> getAllDrafts() {
        return new LinkedList<>(drafts); // return copy
    }

    public int size() {
        return drafts.size();
    }
}
